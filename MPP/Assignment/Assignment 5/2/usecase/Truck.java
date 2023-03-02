package usecase;

public class Truck implements Vehicle{

    Truck() {
        System.out.println("Vehicle::Truck");
    }

    @Override
    public void startEngine() {
        System.out.println("Truck Engine Starting...");
        System.out.println("-----------------------");
    }
}
