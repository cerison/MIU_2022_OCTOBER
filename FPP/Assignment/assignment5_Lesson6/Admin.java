package assignment5_Lesson6;
import java.util.ArrayList;
import java.util.List;

public class Admin {
    public static double computeTotalRent(Property[] properties) {
        double totalRent = 0;
        for (Property p : properties) {
            totalRent += p.computeRent();
        }
        return totalRent;
    }

    public static Property[] getPropertysByCity(Property[] properties, String cityName) {
        ArrayList<Property> cities = new ArrayList<Property>();
        for (Property p : properties) {
            String city = p.getAddress().getCity();
            if (city != null && city.equals(cityName))
                cities.add(p);
        }
        return cities.toArray(new Property[cities.size()]);
    }
}
