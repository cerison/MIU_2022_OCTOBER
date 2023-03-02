package assignment1_Lesson1;
import java.util.Scanner;

public class Myclass {
	
	//method for 1st questions
	static float myAverage(int [] arr)
	{
		float sum = 0;
		for(int i = 0; i < arr.length; i++)
		{
			sum += arr[i];
		}
		return sum / arr.length;
	}

	//method for 2nd questions
	static int[] myCount(int [] arr)
	{
		int evencount = 0,oddcount = 0;
		for(int i = 0; i < arr.length; i++)
		{
			if(arr[i]%2 == 0) {
				evencount++;
			}
			else {
				oddcount++;
			}
		}
		int allcount[] = new int[2];
		allcount[0] = evencount;
		allcount[1] = oddcount;
		return allcount;
	}

	//method for 3rd questions
	static int myEven(int [] arr)
	{
		int evencount = 0;
		for(int i = 0; i < arr.length; i++)
		{
			if(arr[i]%2 == 0)
				evencount++;
		}
		return evencount;
	}
	
	//method for 4th questions
	static boolean findString(String slist[], String skey)
	{
		for(int i=0; i<slist.length; i++) {
			if(slist[i].equals(skey)) {
				return true;
			}
		}
		return false;
	}
	
	//method for 5th questions
	static int maxNumber(int num1, int num2, int num3)
	{
		int maxnumber = num1;
		if(num2 > maxnumber) {
			maxnumber = num2;
		}
		if(num3 > maxnumber){
			maxnumber = num3;
		}
		return maxnumber;
	}
	
	//method for 6th questions
	static String[] reverseString(String slist[])
	{
		for(int i=0; i<slist.length; i++) {
			String temp = "";
			for(int j=slist[i].length()-1; j>=0; --j) {
				temp += slist[i].charAt(j);
			}
			slist[i] = temp;
			temp = "";
		}
		return slist;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
	
		//Input for 1st question
		int num;//total number of array element
		System.out.println("#1...............................");
		System.out.print("Please enter numbers of array : ");
		num = sc.nextInt();
		int myArr[] = new int[num];
		for(int i=0;i<num;i++)
		{
			System.out.print("Please enter "+(i+1)+" elements of array : ");
			myArr[i] = sc.nextInt();
		}
		System.out.println("Average is : "+myAverage(myArr));
		System.out.println("");
		
		//Input for 2nd question
		int oddeven[] = new int[2];
		oddeven = myCount(myArr);
		System.out.println("#2...............................");
		System.out.println("Number of Even : "+ oddeven[0]);
		System.out.println("Number of Odd : "+ oddeven[1]);
		System.out.println("");

		//Input for 3rd question
		int myeven = myEven(myArr);
		System.out.println("#3...............................");
		System.out.println("Number of Even : "+ myeven);
		System.out.println("Number of Odd : "+ (myArr.length - myeven));
		System.out.println("");
		
		//Input for 4th question
		int str;//total number of elements in array of string
		System.out.println("#4...............................");
		System.out.print("Please enter numbers of elements in array of string: ");
		str = sc.nextInt();
		sc.nextLine();
		String [] myStr = new String[str];
		for(int i=0; i<str; i++)
		{
			System.out.print("Please enter "+(i+1)+" elements in array of String : ");
			myStr[i] = sc.nextLine();
		}
		String myKey;
		System.out.print("Please enter key to search in array of string: ");
		myKey = sc.nextLine();
		System.out.println("The result is : "+findString(myStr,myKey));
		System.out.println("");
		
		//Input for 5th question
		int num1,num2,num3;
		System.out.println("#5...............................");
		System.out.print("Please enter num1 :");
		num1 = sc.nextInt();
		System.out.print("Please enter num2 :");
		num2 = sc.nextInt();
		System.out.print("Please enter num3 :");
		num3 = sc.nextInt();
		System.out.println("Maximum of ("+ num1+","+num2+","+num3+") is :"+maxNumber(num1,num2,num3));
		System.out.println("");
		
		//Input for 6th question
		int str2;//total number of elements in array of string
		System.out.println("#6...............................");
		System.out.print("Please enter numbers of array of string: ");
		str2 = sc.nextInt();
		sc.nextLine();
		String [] myStr2 = new String[str2];
		for(int i=0; i<str2; i++)
		{
			System.out.print("Please enter "+(i+1)+" elements of array of String : ");
			myStr2[i] = sc.nextLine();
		}
		System.out.print("List before reverse : ");
		for(String me:myStr2) {

			System.out.print(me+" ");
		}
		System.out.println("");
		System.out.print("List after reverse : ");
		myStr2 = reverseString(myStr2);
		for(String me:myStr2) {
			System.out.print(me+" ");
		}
		System.out.println("");
		
	}
}
