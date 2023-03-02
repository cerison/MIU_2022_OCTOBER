package MPP.Assignment10.prob5;

import java.util.List;

public class SecondSmallestGeneric {

	public static <T extends Comparable<? super T>> T secondSmallest(List<T> list){
		T smallest= list.get(0);
		T secondSmallest= list.get(1);
		if(smallest.compareTo(secondSmallest) > 0){
			T temp = smallest;
			smallest = secondSmallest;
			secondSmallest = temp;
		}
		for(int i = 2; i < list.size(); ++i){
			T next = list.get(i);
			if(next.compareTo(smallest) < 0){
				secondSmallest = smallest;
				smallest = next;
			}
			else if(next.compareTo(secondSmallest) < 0){
				secondSmallest = next;
			}
		}
		return secondSmallest;
	}
	
	public static void main(String[] args) {
		List<Integer> lst1 = List.of(1,1,2,2);
		List<String> str = List.of("a", "t", "x", "w", "d");

		System.out.println(secondSmallest(lst1));
		System.out.println(secondSmallest(str));
	}
}