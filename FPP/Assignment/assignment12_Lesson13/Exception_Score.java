package assignment12_Lesson13;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Exception_Score {
	
	static boolean isNumeric(String str)
	{
	    return str.matches("[+-]?\\d*(\\.\\d+)?");
	}
	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		String score; 
		int value;
		System.out.println("Enter 'end' to stop program ");

		System.out.println();
		System.out.println("---------------------------");
		System.out.print("Enter your score, range from 0 - 100 : ");
		score = sc.nextLine();
		
		while(!score.equals("end")) {
			try {
				if(isNumeric(score)) {value = Integer.parseInt(score);}
				else throw new InputMismatchException();
				
				if(value < 0 || value > 100) throw new UnsupportedOperationException();
			}
			catch(InputMismatchException e) {
				System.out.println("Please enter number only!");
			}
			catch(UnsupportedOperationException e) {
				System.out.println("Please enter number range of 0 - 100!");
			}
			
			System.out.println();
			System.out.println("---------------------------");
			System.out.print("Enter your score, range from 0 - 100 : ");
			Scanner n = new Scanner(System.in);
			score = n.nextLine();
		}
	}

}
