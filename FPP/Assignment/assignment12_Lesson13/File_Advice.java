package assignment12_Lesson13;

import java.io.FileInputStream;
import java.util.Scanner;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;

public class File_Advice {
 
   public static void main(String[] args){
 
      try {
	        Scanner inputStream = new Scanner(new FileInputStream("advice.txt"));
	 
	        String line;
	        while (inputStream.hasNextLine())
	        {
	            line = inputStream.nextLine();
	            System.out.println(line);
	        }
	        inputStream.close();
	 
	        Scanner keyboard = new Scanner(System.in);
	        System.out.println("Enter advice (hit return twice to quit):");
	        line = keyboard.nextLine();
	 
	        PrintWriter outputStream = new PrintWriter(new FileOutputStream("advice.txt"));
	        while (!line.equals(""))
	        {
	            outputStream.println(line);
	            line = keyboard.nextLine();
	        }
	        outputStream.close();
      }
      catch (Exception e)
      {
    	  System.out.println("Error reading or writing files.");
      }
   }
}