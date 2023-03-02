/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package librarysystem;

import business.SystemController;
import java.util.regex.Pattern;
import java.util.List;
import java.util.regex.*;

/*This code defines a class called ValidationUtil that contains several methods for validating different types of input.
 * The isValidZipCode method takes a string as input and checks if it is a valid US zip code by using regular expression pattern matching. 
 * It returns true if the zip code is valid, false otherwise. The isValidPhone method takes a string as input and checks if it is 
 * a valid US phone number by using regular expression pattern matching. It returns true if the phone number is valid, false otherwise.
 * The memberExists method takes a string as input and checks if it exists in a list of member IDs. It returns true if the member ID exists, false otherwise.
 * The isValidNumber method takes a string as input and checks if it is a valid number by using regular expression pattern matching. 
 * It returns true if the number is valid, false otherwise. The isValidAlpha method takes a string as input and checks if it is a valid alphabet by using 
*/
public class ValidationUtil {
    
    public static boolean isValidZipCode(String zipCode) {
        Pattern p = Pattern.compile("^\\d{5}$");
        Matcher m = p.matcher(zipCode);
        return (m.matches());
    }

    public static boolean isValidPhone(String phone) {
        Pattern p = Pattern.compile("^\\d{10}$");
        Matcher m = p.matcher(phone);
        return (m.matches());
    }

    public static boolean memberExists(String id) {
        SystemController sc = new SystemController();
        List<String> memberIds = sc.allMemberIds();
        for (int i = 0; i < memberIds.size(); i++)
            if (memberIds.get(i).equalsIgnoreCase(id))
                return true;
        return false;
    }

    public static boolean isValidNumber(String number) {
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(number);
        return (m.matches());
    }

    public static boolean isValidAlpha(String alphabet) {
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(alphabet);
        return (m.matches());
    }
}
