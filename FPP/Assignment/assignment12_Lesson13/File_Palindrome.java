package assignment12_Lesson13;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

public class File_Palindrome {
	
static boolean isPalindrome(String str)
{
    String rev = "";
    for (int i = str.length() - 1; i >= 0; i--) {
        rev = rev + str.charAt(i);
    }
    return (str.equals(rev));
}

static void readfile() {
	try {
		FileInputStream bis = new FileInputStream("WordBuff.txt");
		BufferedInputStream bin = new BufferedInputStream(bis);    
	    String str = "";
		int i = 0,count = 0;
		while((i = bin.read()) != -1) {
			str += (char)i;
		}
		String val[] = str.split(",");
        for(int j = 0; j < val.length; j++) {
        	if(isPalindrome(val[j])) count++;
        	System.out.print(val[j]+", ");
        }
        System.out.println(); 
        System.out.println("Total palindrome : " + count);
		bis.close();
		bin.close();
	}
	catch(Exception e) 
	{
         System.out.println(e); 
	}
}
public static void main(String[] args) {
	File_Palindrome pn = new File_Palindrome();
	pn.readfile();
}
}