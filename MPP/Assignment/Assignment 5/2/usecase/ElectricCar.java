package usecase;

public class ElectricCar implements Vehicle{

    ElectricCar() {
        System.out.println("Vehicle::ElectricCar");
    }

    @Override
    public void startEngine() {
        System.out.println("Electric Car Engine Starting...");
        System.out.println("--------------------------------");
    }
}
