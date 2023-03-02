package assignment5_Lesson6;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

class Pets {
	private String name;
	private char type;

	public String getName() {
		return name;
	}

	public char getType() {
		return type;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(char type) {
		this.type = type;
	}
}

public class PetsDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ArrayList<Pets> petsList = new ArrayList<>();
		// int i=0;
		String name;
		char t;
		while (true) {
			Scanner sc = new Scanner(System.in);
			System.out.println("Please Enter The Name and type of pets  (type Stop to Exit)");
			System.out.println("Name = ");

			name = sc.nextLine();
			if (name.toLowerCase().equals("stop"))break;

			System.out.println("Type ('c' for cat 'd' for dog) = ");
			t = sc.next().charAt(0);

			Pets p = new Pets();
			p.setName(name);
			
			p.setType(t);
			petsList.add(p);

		}

		ListIterator<Pets> itr = petsList.listIterator();
		// traversing elements of ArrayList object
		while (itr.hasNext()) {
			Pets p = (Pets) itr.next();
			System.out.println("Name :  " + p.getName() + "   " + "   " + "Type : "+p.getType());
		}

	}

}
