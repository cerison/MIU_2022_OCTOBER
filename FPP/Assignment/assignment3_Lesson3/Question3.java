package assignment3_Lesson3;
import java.util.Arrays;
public class Question3 {
	static int bSearch(int arr[], int first, int last, int key) 
	{
		if(arr.length == 0 || first == arr.length)
			return -1;
		if(last >= first) 
		{
			int mid = first+(last-first)/2;
			if(arr[mid] == key)
				return mid;
			if(key < arr[mid]) 
				return bSearch(arr,first,mid-1,key);
			else 
				return bSearch(arr,mid+1,last,key);
		}
		return -1;
	}
	public static void main(String[] args) {
		int arr[] = {1,4,6,8,9,10};
		Arrays.sort(arr);
		int key = 9;
		System.out.println("The following are your list");
		for(int i: arr) {
		System.out.print(i+" ");
		}
		System.out.println();
		System.out.println("Your key to search : "+key);
		System.out.println();
		System.out.println("Search : "+bSearch(arr,0,arr.length,key));
	}
}