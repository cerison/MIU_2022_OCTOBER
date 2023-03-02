package assignment2_Lesson2;
import java.util.Scanner;

//Answer for 5th question
public class Question5 {
	static boolean checkArray(int arr1[], int arr2[]) {
		if(arr1.length != arr2.length) return false;
		for(int i=0;i<arr1.length;i++) {
			if(arr1[i] != arr2[i]) return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		
		//Input for 5th question
		Scanner sc = new Scanner(System.in);
		int totNum1;//total number of elements in 1st array
		int totNum2;//total number of elements in 2nd array
		System.out.print("Please enter total numbers of element in 1st array: ");
		totNum1 = sc.nextInt();
		int [] myArr1 = new int[totNum1];
		for(int i=0; i<totNum1; i++)
		{
			Scanner ar = new Scanner(System.in);
			System.out.print("Please enter "+(i+1)+" elements of array : ");
			myArr1[i] = ar.nextInt();
		}

		System.out.println("");
		System.out.print("Please enter total numbers of element in 2nd array: ");
		totNum2 = sc.nextInt();
		int [] myArr2 = new int[totNum2];
		for(int i=0; i<totNum2; i++)
		{
			Scanner ar = new Scanner(System.in);
			System.out.print("Please enter "+(i+1)+" elements of array : ");
			myArr2[i] = ar.nextInt();
		}
		System.out.print("The result is : "+checkArray(myArr1,myArr2));
	}
}
