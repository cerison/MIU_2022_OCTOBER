package assignment11_Lesson12;

import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

public class Admin {
	public static HashMap<Key, Student> processStudents(List<Student> students) {
		HashMap<Key, Student> hashValues = new HashMap<Key, Student>();
		Student s;
		for(ListIterator<Student> itr = students.listIterator(); itr.hasNext(); ) {
			s = itr.next();
			hashValues.put(new Key(s.getFirstName(), s.getLastName()), s); // put key which is generated by hash code;
		}
		return hashValues;
	}
	
	public static double computeAverageGPA(HashMap<Key,Student> maps){
		double total = 0;
		int count = 0;
		for(Key k: maps.keySet()) {
			total += maps.get(k).getGpa();
			++count;
		}
		return total / count;	 		  
	}
}
