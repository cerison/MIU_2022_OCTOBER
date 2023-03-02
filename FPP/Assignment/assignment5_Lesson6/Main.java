package assignment5_Lesson6;
public class Main {

    public static void main(String[] args) {

        Property[] objects = { new House(new Address("Iowa","Fairfield", "NorthForth st", "45"), 7000),
                new Condo(new Address("Iowa","Fairfield", "NorthForth st", "235"), 2),
                new Trailer(new Address("Iowa","Fairfield", "NorthForth st", "357")) };
        double totalRent = Admin.computeTotalRent(objects);
        System.out.println("Total rent: "+totalRent);

        // List all properties
        for(Property p : Admin.getPropertysByCity(objects, "Fairfield")) {
            Address address = p.getAddress();
            System.out.println("Property: " + address.getStreet() + ", " + address.getNumber() );
        }
    }
}
