package assignment12_Lesson13;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.Scanner;

public class File_Occurrence {
	static void readfile() {
		try {
			FileInputStream bis = new FileInputStream("10.txt");
			BufferedInputStream bin = new BufferedInputStream(bis);    
		    String str = "";
			int i = 0;
			while((i = bin.read()) != -1) {
				str += (char)i;
			}
			String val[] = str.split(" ");
			int count = 0;
			for(int j = 0; j < 11; j++) {
				if(Integer.parseInt(val[j]) == 10) ++count;
		        System.out.print(val[j]+" ");
			}
	        System.out.println();
			System.out.print("Occurrence of 10 : "+count);
			bis.close();
			bin.close();
		}
		catch(Exception e) 
		{
	         System.out.println(e); 
		}
	}
	public static void main(String[] args) {
		File_Occurrence pn = new File_Occurrence();
		pn.readfile();
	}
}