public interface CameraSwitch {
    void turnCameraOn();
    void turnCameraOff();
}
public interface EngineSwitch {
    void startEngine();
    void shutDownEngine();
}
public interface RadioSwitch {
    void turnRadioOn();
    void turnRadioOff();
}
public abstract class Vehicle implements EngineSwitch {
    private boolean engineRunning;
    public boolean isEngineRunning() {
        return engineRunning;
    }
    public void startEngine() {
        engineRunning = true;
    }
    public void shutDownEngine() {
        engineRunning = false;
    }

}
public class Drone extends Vehicle implements CameraSwitch {
    private boolean cameraOn;
    public boolean isCameraOn() {
        return cameraOn;
    }
    public void turnCameraOn() {
        cameraOn = true;
    }
    public void turnCameraOff() {
        cameraOn = false;
    }
}
public class Car extends Vehicle implements RadioSwitch {
    private boolean radioOn;
    public boolean isRadioOn() {
        return radioOn;
    }
    public void turnRadioOn() {
        radioOn = true;
    }
    public void turnRadioOff() {
        radioOn = false;
    }

}