package assignment3_Lesson3;
import java.util.Scanner;

public class Question2 {

	static char minChar(String str) 
	{
		char minchar;
		if (str.length() == 1)
		{
            return str.charAt(0);
		}
		else 
		{
			minchar = minChar(str.substring(1));
			if(str.charAt(0) < minchar){
				return str.charAt(0);
			}
			else {
				return minchar;
			}
		}
	}
	
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		System.out.print("Please enter string : ");
		String str = sc.nextLine();
		System.out.println("Minimum character is : "+minChar(str));
		//System.out.println("Minimum character is : "+minChar2(str,str.charAt(0)));//alternative way
	}
	
	//alternative method to find minimum character
	static char minChar2(String str, char minchar) 
	{
		if (str == null || str.equals("")) 
		{
            return minchar;
		}
		else 
		{
			if(str.charAt(0) < minchar) 
			{
				minchar =  str.charAt(0);
			}
			return minChar2(str.substring(1),minchar);
		}
	}
}
