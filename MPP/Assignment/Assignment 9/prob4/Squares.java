package MPP.Assignment9.prob4;

import java.util.stream.IntStream;

// Lab - 9 - Soultion - Prob-4

public class Squares {
	public static void main(String[] args) {	
		printSquares(4);
		printSquares1(4);
	}
	// Method - 1
	public static void printSquares(int limit) {
		IntStream squares = IntStream.iterate(1, n -> nextSquare(n)).limit(limit);
		squares.forEach(System.out::println);

		
	}
	
	private static int nextSquare(int n) {
		return (int) Math.pow((int)Math.sqrt(n) + 1, 2);
	}
	// Method - 2
	public static void printSquares1(int limit) {
		IntStream ints= IntStream.iterate(1,f->f+1)
                .map(f->f*f).limit(limit);
		ints.forEach( n->System.out.print(n+" "));	
	}
	
	}
