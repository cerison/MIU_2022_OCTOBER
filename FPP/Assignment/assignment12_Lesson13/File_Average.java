package assignment12_Lesson13;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.Scanner;

public class File_Average {
	static void readfile() {
		try {
			FileInputStream bis = new FileInputStream("average.txt");
			BufferedInputStream bin = new BufferedInputStream(bis);    
		    String str = "";
		    double total = 0;
			int i = 0,count = 0;
			while((i = bin.read()) != -1) {
				str += (char)i;
			}
			String val[] = str.split("[\r\n]+");
			for(int j = 0; j < val.length; j++) {
				total += Integer.parseInt(val[j]);
				System.out.println(Integer.parseInt(val[j]));
			}
	        System.out.println("Average : " +total/val.length); 
			bis.close();
			bin.close();
		}
		catch(Exception e) 
		{
	         System.out.println(e); 
		}
	}
	public static void main(String[] args) {
		File_Average pn = new File_Average();
		pn.readfile();
	}
}