package usecase;

public class VehicleFactory {

    private VehicleFactory(){}

    public static Vehicle getVehicle(String v){
        return switch (v) {
            case "Bus","bus" -> new Bus();
            case "Car","car" -> new Car();
            case "Electric Car", "electric car" -> new ElectricCar();
            case "Truck", "truck" -> new Truck();
            default -> null;
        };
    }
}
