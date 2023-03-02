package usecase;

public class Car implements Vehicle{

    Car() {
        System.out.println("Vehicle::Car");
    }

    @Override
    public void startEngine() {
        System.out.println("Car Engine Starting...");
        System.out.println("-----------------------");
    }
}
