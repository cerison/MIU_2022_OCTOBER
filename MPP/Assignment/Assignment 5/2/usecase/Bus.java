package usecase;

public class Bus implements Vehicle {

    Bus() {
        System.out.println("Vehicle::Bus");
    }

    @Override
    public void startEngine() {
        System.out.println("Bus Engine Starting...");
        System.out.println("-----------------------");
    }
}
