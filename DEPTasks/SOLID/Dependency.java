public interface Vehicle {
    void accelerate();
}
public class RacingCar implements Vehicle{
    private final int maxFuel;
    private int remainingFuel;
    private int power;
    public RacingCar(final int maxFuel) {
        this.maxFuel = maxFuel;
        remainingFuel = maxFuel;
    }
    public void accelerate() {
        power++;
        remainingFuel--;
    }
}
public class Driver {
    private Vehicle vehicle;
    public Driver(final Vehicle vehicle){
        this.vehicle = vehicle;
    }
    public void increaseSpeed(){
        vehicle.accelerate();
    }
}