package lesson3.labsolns.prob4;

public class Driver {

	public static void main(String[] args) {

		Property[] objects = { new House(9000), new Condo(2), 
				new Trailer(new Address("111 Main", "Fairfield", "IA", "52556")) };
		double totalRent = Admin.computeTotalRent(objects);
		System.out.println(totalRent);
	}
}
