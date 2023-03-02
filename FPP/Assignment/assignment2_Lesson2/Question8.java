package assignment2_Lesson2;
import java.util.ArrayList;
import java.util.Scanner;
// answer for 8th question
public class Question8 {

	public static void main(String[] args) {
	     char con;
	     Scanner sc = new Scanner(System.in);
	     Fortune fr = new Fortune();
	     fr.setFortune();
	     System.out.print("Fortune (Y/N)?");
	     con = sc.next().charAt(0);
	     while(con == 'Y'){
	    	 int randomNum = (int)(Math.random() * 10);
		     System.out.println(fr.fortune.get(randomNum));
		     Scanner sn = new Scanner(System.in);
		     System.out.println("");
		     System.out.print("Fortune (Y/N)?");
		     con = sn.next().charAt(0);
	     }
	}
}

class Fortune {
	 
	 ArrayList<String> fortune = new ArrayList<String>();
	 
	 public void setFortune() {
		 fortune.add("You will get 4.0 GPA this semester");
	     fortune.add("Happiness is programming");
	     fortune.add("Satisfaction follows hard work");
	     fortune.add("Patience is virtue");
	     fortune.add("The weather will soon be nice");
	     fortune.add("Snow Day Coming");
	     fortune.add("The weather will soon be nice");
	     fortune.add("You will get a good co-op");
	     fortune.add("Your GPA does not mean anything!");
	     fortune.add("Do Not waste your time");
	     }
	 
	public String getFortune(int i) {
		return fortune.get(i);
		}
}
