import usecase.Vehicle;
import usecase.VehicleFactory;

public class Main {
    public static void main(String[] args) {
        System.out.println("************ GROUP # 11 - LAB # 5 (Problem # 2)****************");

        Vehicle vehicle = VehicleFactory.getVehicle("Truck");
        vehicle.startEngine();

        vehicle = VehicleFactory.getVehicle("car");
        vehicle.startEngine();

        vehicle = VehicleFactory.getVehicle("bus");
        vehicle.startEngine();

        vehicle = VehicleFactory.getVehicle("Electric Car");
        vehicle.startEngine();
    }
}