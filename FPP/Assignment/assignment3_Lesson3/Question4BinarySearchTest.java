package assignment3_Lesson3;
import static org.junit.Assert.*;
import org.junit.Test;

public class Question4BinarySearchTest {

	int arr[] = {1,2,3,4,5,6,7,8,9,45,67,89,99};
	int arr2[] = {};
	
	//test search out of index to left
	@Test
	public void test_BinarySearchOutOfIndexToLeft() {
		int actual = Question3.bSearch(arr,0,arr.length,-9);
		int expected = -1;
		assertTrue(actual == expected);
	}
	
	//test search out of index to right
	@Test
	public void test_BinarySearchOutOfIndexToRight() {
		int actual = Question3.bSearch(arr,0,arr.length,100);
		int expected = -1;
		assertTrue(actual == expected);
	}
	
	//test search not found between first and last
	@Test
	public void test_BinarySearchNotFound() {
		int actual = Question3.bSearch(arr,0,arr.length,11);
		int expected = -1;
		assertTrue(actual == expected);
	}

	//test search found
	@Test
	public void test_BinarySearchFound() {
		int actual = Question3.bSearch(arr,0,arr.length,9);
		int expected = 8;
		assertTrue(actual == expected);
	}
	
	//test search empty array
	@Test
	public void test_BinarySearchEmptyArray() {
		int actual = Question3.bSearch(arr2,0,arr2.length,9);
		int expected = -1;
		assertTrue(actual == expected);
	}
}
