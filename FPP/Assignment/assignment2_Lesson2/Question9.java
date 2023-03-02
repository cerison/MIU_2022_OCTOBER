package assignment2_Lesson2;
import java.util.ArrayList;
import java.util.Scanner;

//Answer for 9th question
public class Question9 {
	
	public static void main(String[] args) {
        String input = "";
        ArrayList<String> codon=new ArrayList<>();
        while(true){
            System.out.print("Please enter your RNA/DNA input");
            Scanner sc=new Scanner(System.in);
            input = sc.nextLine();
            if (input.equalsIgnoreCase("")||(input.equalsIgnoreCase(" "))){
                System.out.println("Exit");
            }
            else{
                String temp="";
                for (int i=0;i<input.length();i++){
                    temp = temp+input.charAt(i);
                    if (temp.length()==3) {
                        codon.add(temp);
                        temp="";
                    }
                }
                System.out.println("Your codon is: ");
                for (String list: codon){
                    System.out.print(list+" ");
                }
            }
            codon.clear();
            System.out.println("");
            System.out.println("");
        }

	}

}
