package lesson3.labsolns.prob2;
import java.util.*;
public class Main {

public static void main(String[] args) {
	Apartment[] apts1 = {
			new Apartment(400),new Apartment(600),
			new Apartment(700)};
	Apartment[] apts2 = {
			new Apartment(900), new Apartment(500), 
			new Apartment(450)
	};
	Apartment[] apts3 = {
			new Apartment(300),new Apartment(1100),
			new Apartment(650)};
	Apartment[] apts4 = {
			new Apartment(775), new Apartment(6750), 
			new Apartment(945)
	};
	Building[] bldgs = {
			new Building(150, Arrays.asList(apts1)),
			new Building(175, Arrays.asList(apts2)),
			new Building(150, Arrays.asList(apts3)),
			new Building(175, Arrays.asList(apts4))
	};
	LandlordInfo landlord = new LandlordInfo(Arrays.asList(bldgs));
	System.out.println(landlord.calcProfits());
			
	}

}


