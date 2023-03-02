package MPP.Assigment9.prob1;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// Lab-9 - Problem -1 Solution
public class Prob1 {
	public static void main(String[] args) {
		System.out.println(Arrays.asList("Bill", "Thomas", "Mary")
				                 .stream()
				                 .collect(Collectors.joining(", ")));
	}
}
