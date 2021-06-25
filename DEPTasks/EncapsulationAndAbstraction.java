import java.util.*;
public interface MotionSensor {
    String sensorId;
    boolean isMovement();
    boolean noMovementInAMinute();
    //other related methods or variables


}
public class MotionController implements MotionSensor {

    public boolean isMovement() {
    	//implementation
        return false;
    }
    public boolean noMovementInAMinute() {
    	//implementation
    	return false;
    }

}

public class ElectronicBuilder{

    public static final String LIGHT = "Light";
    public static final String AC = "Ac";

    public static Collection<ElectronicEquipment> getSubCorridorDevices() {
        return Arrays.asList(new ElectronicEquipment(LIGHT, 5, false),
                new ElectronicEquipment(AC, 10, true));
    }

    public static Collection<ElectronicEquipment> getCorridorDevices() {
        return Arrays.asList(new ElectronicEquipment(LIGHT, 5, true),
                new ElectronicEquipment(AC, 10, true));
    }
}
public class ElectronicEquipment {
    public String type;
    public int units;
    public boolean on;

    public ElectronicEquipment(String type, int units, boolean on) {
        this.type = type;
        this.units = units;
        this.on = on;
    }

    public int getUnits() {
        return on ? units : 0;
    }

    void switchIt(boolean on) {
        this.on = on;
    }

    public boolean isOn() {
        return on;
    }

    public boolean isOff() {
        return !on;
    }

    public String getType() {
        return type;
    }


   
}

public class Factory {

    private Collection<Hotel>   hotels;
    private MotionController motionController;
    NightShift th[];
    public Factory(Collection<Hotel> hotels, MotionController motionController) {
        this.hotels = hotels;
        this.motionController = motionController;
    }
    public void nightShiftStart(){
    	
    	int i=0;
    	th=new NightShift[hotels.size()];
    	for(Hotel hotel:hotels) {
    		th[i]=new NightShift(hotel,motionController);
    		th[i].start();
    		i++;
    	}
    }
	public void nightShiftEnd() {
    	for(NightShift temp:th) {
    		temp.destroy();
    	}
    }
    

}

class NightShift extends Thread{
	Hotel hotel;
	MotionController motionController;
	NightShift(Hotel hotel,MotionController motionController){
		this.hotel=hotel;
		this.motionController=motionController;
	}
	public void run() {
		while(true) {
		for(Floor floor:hotel.getFloors()) {
    		for(SubCorridor sub:floor.getSubCorridors()) {
    			if(motionController.isMovement()){
    				ElectronicEquipment tempL=sub.electronicEquipmentMap.get("Light");
    				ElectronicEquipment tempAc=sub.electronicEquipmentMap.get("AC");
    				tempL.on=true;
    				sub.electronicEquipmentMap.put("Light",tempL);
    				if(floor.totalPowerConsumption()>=(floor.getCorridors().size()*15+floor.getSubCorridors().size()*10)) {
    					tempAc.on=false;
    				}
    				sub.electronicEquipmentMap.put("AC",tempAc);
    			}
    			if(motionController.noMovementInAMinute()){
    				ElectronicEquipment tempL=sub.electronicEquipmentMap.get("Light");
    				ElectronicEquipment tempAc=sub.electronicEquipmentMap.get("AC");
    				tempL.on=false;
    				sub.electronicEquipmentMap.put("Light",tempL);
    				if(floor.totalPowerConsumption()<=(floor.getCorridors().size()*15+floor.getSubCorridors().size()*10)) {
    					tempAc.on=true;
    				}
    				sub.electronicEquipmentMap.put("AC",tempAc);
    			}
    		}
		}
		try {
			wait(60000);
		}
		catch(InterruptedException e){
			System.out.println(e);
		}
		
	}
}
}

public class Floor {
    private final List<Corridor> corridors;
    private final List<SubCorridor> subCorridors;

    public Floor(int numberOfMainCorridors, int numberOfSubCorridors) {
        corridors = new ArrayList<Corridor>();
        for(int corridorIndex=0;corridorIndex<numberOfMainCorridors;corridorIndex++) {
            corridors.add(new Corridor(ElectronicBuilder.getCorridorDevices()));
        }
        subCorridors = new ArrayList<SubCorridor>();
        for(int subCorridorIndex=0; subCorridorIndex<numberOfSubCorridors;subCorridorIndex++) {
            subCorridors.add(new SubCorridor(ElectronicBuilder.getSubCorridorDevices()));
        }
    }

    public List<Corridor> getCorridors() {
        return corridors;
    }

    public List<SubCorridor> getSubCorridors() {
        return subCorridors;
    }

    public int totalPowerConsumption() {
      
        int totalPower=0;
        for(Corridor temp:corridors) {
        	totalPower+=temp.getTotalPowerConsumption();
        }
        for(SubCorridor temp:subCorridors) {
        	totalPower+=temp.getTotalPowerConsumption();
        }
        return totalPower;
        
    }

}
public class Hotel{

    private List<Floor> floors;

    public Hotel(int numberOfFloors, int numberOfMainCorridors, int numberOfSubCorridors) {
        floors = new ArrayList<Floor>();
        for(int floorIndex=0;floorIndex<numberOfFloors;floorIndex++) {
            floors.add(new Floor(numberOfMainCorridors, numberOfSubCorridors));
        }
    }

    public List<Floor> getFloors() {
        return floors;
    }
}
public class Corridor{

    public Map<String, ElectronicEquipment> electronicEquipmentMap;

    public Corridor(Collection<ElectronicEquipment> equipments) {
    	electronicEquipmentMap=new HashMap<String,ElectronicEquipment>();
        for(ElectronicEquipment temp:equipments) {
        	electronicEquipmentMap.put(temp.getType(), temp);
        }
    }

    public ElectronicEquipment getElectronicEquipment(String name) {
        return electronicEquipmentMap.get(name);
    }

    int getTotalPowerConsumption() {
    	int totalPower=0;
        for(ElectronicEquipment temp:electronicEquipmentMap.values()) {
        	totalPower+=temp.getUnits();
        }
        return totalPower;
    }
}
public class SubCorridor  {
    public Map<String, ElectronicEquipment> electronicEquipmentMap;

    public SubCorridor(Collection<ElectronicEquipment> equipments) {
    	electronicEquipmentMap=new HashMap<String,ElectronicEquipment>();
        for(ElectronicEquipment temp:equipments) {
        	electronicEquipmentMap.put(temp.getType(), temp);
        }    }

    int getTotalPowerConsumption() {
    	int totalPower=0;
        for(ElectronicEquipment temp:electronicEquipmentMap.values()) {
        	totalPower+=temp.getUnits();
        }
        return totalPower;
    }

    public ElectronicEquipment getElectronicEquipment(String name) {
        return electronicEquipmentMap.get(name);
    }
}

public class Task
{
    public static void main( String[] args )
    {
        ArrayList<Hotel>hotelList=new ArrayList<Hotel>();
	    hotelList.add(new Hotel(2,2,2));
	    hotelList.add(new Hotel(5,3,5));
	    Factory fact=new Factory(hotelList,new MotionController());
        fact.nightShiftStart();
    }
}