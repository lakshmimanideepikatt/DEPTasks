public interface DrivingMode {
    int getPower();
    int getSuspensionHeight();
}
public class Vehicle {
    private int power;
    private int suspensionHeight;
    public int getPower() {
        return power;
    }
    public int getSuspensionHeight() {
        return suspensionHeight;
    }
    public void setPower(final int power) {
        this.power = power;
    
    public void setSuspensionHeight(final int suspensionHeight) {
        this.suspensionHeight = suspensionHeight;
    }
}
public class Sport implements DrivingMode {
    private static final int POWER = 500;
    private static final int SUSPENSION_HEIGHT = 10;
    public int getPower() {
        return POWER;
    }
    public int getSuspensionHeight() {
        return SUSPENSION_HEIGHT;
    }
}

public class Economy implements DrivingMode{
    private static final int POWER = 300;
    private static final int SUSPENSION_HEIGHT = 20;
    public int getPower() {
        return POWER;
    }
    public int getSuspensionHeight() {
        return SUSPENSION_HEIGHT;
    }
}
public class Comfort implements DrivingMode {
    private static final int POWER = 400;
    private static final int SUSPENSION_HEIGHT = 20;
    public int getPower() {
        return POWER;
    }
    public int getSuspensionHeight() {
        return SUSPENSION_HEIGHT;
    }
}
public class EventHandler {
    private Vehicle vehicle
    public EventHandler(final Vehicle vehicle) {
        this.vehicle = vehicle;
    }
    public void changeDrivingMode(final DrivingMode drivingMode){
        vehicle.setPower(drivingMode.getPower());
        vehicle.setSuspensionHeight(drivingMode.getSuspensionHeight());
    }
}