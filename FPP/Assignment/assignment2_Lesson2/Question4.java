package assignment2_Lesson2;
import java.util.Scanner;

public class Question4 {

	//method for 4th questions
	 static String maxmin(int myarr[] )
	{
		int max = myarr[0];
		int min = myarr[0];
		for(int i=0; i<myarr.length; i++) {
			if(myarr[i]>max) max =myarr[i];
			if(myarr[i]<min) min = myarr[i];
		}
		return max + " is the maximum and the minimum is "+min;
	}
	
	
	public static void main(String[] args) {
		
		//Input for 4th question
		Scanner sc = new Scanner(System.in);
		int totNum;//total number of elements in array
		System.out.print("Please enter numbers of array: ");
		totNum = sc.nextInt();
		
		int [] myArr = new int[totNum];
		for(int i=0; i<totNum; i++)
		{
			Scanner ar = new Scanner(System.in);
			System.out.print("Please enter "+(i+1)+" elements of array : ");
			myArr[i] = ar.nextInt();
		}
		System.out.print(maxmin(myArr));
	}

}
