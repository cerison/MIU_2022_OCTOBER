package assignment6;

import java.util.ArrayList;
import java.util.Collections;

//Student Class ===============================
class Student implements Comparable<Student> {
	private String lastName;
	private int id;

	public Student(String lastName, int id) {
		this.lastName = lastName;
		this.id = id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	public void setLastName(String name) {
		lastName = name;
	}

	public String getLastname() {
		return this.lastName;
	}

	public static void studentSort(ArrayList<Student> arr) {
		Collections.sort(arr);
		for (Student st : arr) {
			System.out.println("Last Name : " + st.getLastname() + " " + " " + "Id : " + st.getId());

		}
	}
// ================================== ComapreTo() to sort ID wise)======================================================== 
//	@Override
//	public int compareTo(Student compare) {
//		Student other = (Student) compare;
//		if (id < other.id)
//			return -1;
//		else if (id > other.id)
//			return 1;
//		else
//			return 0;
//	}

//================================== ComapreTo() to sort Last name wise)======================================================== 	
	@Override
	public int compareTo(Student compare) {
		Student other = (Student) compare;
		if(this.getLastname().compareTo(other.getLastname()) < 0)
			return -1;
		else if (this.getLastname().compareTo(other.getLastname()) > 0)
			return 1;
		else
			return 0;
	}

}

public class StudentSortDemo {

	public static void main(String[] args) {
		Student std1 = new Student("asad", 10);
		Student std2 = new Student("bds", 5);
		Student std3 = new Student("adsa", 7);
		Student std4 = new Student("fd", 1);
		Student std5 = new Student("as", 10);
		Student std6 = new Student("bds", 17);

		ArrayList<Student> arr = new ArrayList<>();
		arr.add(std1);
		arr.add(std2);
		arr.add(std3);
		arr.add(std4);
		arr.add(std5);
		arr.add(std6);
		
		for (Student st : arr) {
			System.out.println("Last Name : " + st.getLastname() + " " + " " + "Id : " + st.getId());

		}
		System.out.println("===========================After sort=============================");
		Student.studentSort(arr);

	}

}
