package assignment4_Lab3;
import java.util.Arrays;

public class MyStringList {
	private final int INITIAL_LENGTH = 2;
	private String[] strArray; 
	private int size;
	
	public MyStringList() {
		strArray = new String[INITIAL_LENGTH];
		size = 0;
	}
	
	public void add(String s){
		if(size >= strArray.length)
			resize();
		strArray[size] = s;
		size++;
	}
	
	public String get(int i){
		return strArray[i];
	}
	
	public boolean find(String s){
		for (int i = 0; i < size; i++) {
		    if (strArray[i].equals(s))
		        return true;
		}
		return false;
	}
	
	public boolean remove(String s){
		if(find(s)) {
	    	String[] newArray = new String[size-1];
			for (int i = 0; i < size; i++) {
			    if (strArray[i].equals(s)) {
			        System.arraycopy(strArray,0, newArray, 0, i);
			        System.arraycopy(strArray,i+1, newArray, i, (size-i-1));
			        strArray = newArray;
			        size--;
			        return true;
			    }
			    }
			}
		return false;
	}

	public String toString(){
        String[] newArray = new String[size];
        System.arraycopy(strArray, 0, newArray, 0, size);
		return Arrays.toString(newArray);
	}
	
	private void resize(){
		System.out.println("Resizing...");
        String[] newArray = new String[size*2];
        System.arraycopy(strArray, 0, newArray, 0, size);
        strArray = newArray;
	}
	public int size() {
		return size;
	}

	public static void main(String[] args){
		MyStringList l = new MyStringList();
		l.add("Bob");
		System.out.println("The list of size " + l.size() + " is " + l);
		l.add("Steve");
		System.out.println("The list of size " + l.size() + " is " + l);
		l.add("Susan");
		System.out.println("The list of size " + l.size() + " is " + l);
		l.add("Mark");
		System.out.println("The list of size " + l.size() + " is " + l);
		l.add("Dave");
		System.out.println("The list of size " + l.size() + " is " + l);
		l.remove("Mark");
		System.out.println("The list of size " + l.size() + " is " + l);
		l.remove("Bob");
		System.out.println("The list of size " + l.size() + " is " + l);
	}
}