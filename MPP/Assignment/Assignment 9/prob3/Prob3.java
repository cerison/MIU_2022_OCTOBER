package MPP.Assigment9.prob3;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class Prob3 {
	public int countWords(List<String> words, char c, char d, int len) {
		return
			     words.stream()   //convert list to stream
	         	     .filter(name -> name.contains(""+c)) 
	         	     .filter(name -> !name.contains(""+d))
	         	     .filter(name -> name.length()==len)
	         	     .collect(Collectors.toList())
	         	     .size();
	}
	
	
	public static void main(String[] args) {
		Good wcf = new Good();
		List<String> words = Arrays.asList("Bob", "Alice", "Tim",
				"Joe", "Anthony", "Caleb", "Tom", "Bee", "Bot", 
				"Tea", "ATT");
				
		//should output number of words in the list that are of length 3, 
		//that include 'T' and exclude 'B'
		//Those are: ""Tim", "Tea", "ATT" and "Tom", so output is 4
		System.out.println(wcf.countWords(words, 'T', 'B', 3));
		

	}
}
