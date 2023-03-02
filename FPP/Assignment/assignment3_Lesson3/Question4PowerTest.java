package assignment3_Lesson3;
import static org.junit.Assert.*;
import org.junit.Test;

public class Question4PowerTest {
	
	//test number to the power of positive number
	@Test
	public void test_NumberToPowerOfPositive() {
		Exponential ex = new Exponential();
		double actual = ex.power(2,10);
		double expected = 1024.0;
		assertTrue(actual == expected);
	}

	//test number to the power of negative number
	@Test
	public void test_NumberToPowerOfNegative() {
		Exponential ex = new Exponential();
		double actual = ex.power(2,-1);
		double expected = 0.5;
		assertTrue(actual == expected);
	}

	//test number to the power of 0
	@Test
	public void test_NumberToPowerOfZero() {
		Exponential ex = new Exponential();
		double actual = ex.power(2,0);
		double expected = 1.0;
		assertTrue(actual == expected);
	}
}