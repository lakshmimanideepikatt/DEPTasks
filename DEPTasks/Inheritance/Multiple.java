public interface Moveable {
    public void move();
}
 
public class Car {
    protected void start() {
        System.out.print("Starting...");
    }
 
    public void stop() {
        System.out.print("Stopping...");       
    }
}
 
public class Truck extends Car implements Moveable {
    public void move() {
        System.out.print("Moving...");
    }
}