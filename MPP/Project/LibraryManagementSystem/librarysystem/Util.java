package librarysystem;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JLabel;

/*
 * This code defines a class named "Util" which contains several static methods and variables. Some of the methods include:
 * makeSmallFont(Font f): Takes in a Font object and returns a new Font object with 2 points smaller size.
 * adjustLabelFont(JLabel label, Color color, boolean bigger): Takes in a JLabel object, a Color object, and a boolean value. If the boolean value is true, 
 * the method increases the font size of the label by 2 and sets the label's color to the input color. If the boolean value is false, it does the opposite.
 * numericSort(List<String> list): Sorts a list of numeric strings in natural number order, and returns the sorted list.
 * isNumeric(String s): Takes in a string and returns true if the string can be parsed to an Integer, false otherwise.
 * centerFrameOnDesktop(Component f): Centers a component on the desktop.
 * It also defines several final static variables such as DARK_BLUE, ERROR_MESSAGE_COLOR, INFO_MESSAGE_COLOR, etc. which are used to set the color of 
 * different components.


Regenerate response
 */
public class Util {
    public static final Color DARK_BLUE = Color.BLUE.darker();
    public static final Color ERROR_MESSAGE_COLOR = Color.RED.darker(); //dark red
    public static final Color INFO_MESSAGE_COLOR = new Color(24, 98, 19); //dark green
    public static final Color LINK_AVAILABLE = DARK_BLUE;
    public static final Color LINK_NOT_AVAILABLE = Color.gray;

    public static Font makeSmallFont(Font f) {
        return new Font(f.getName(), f.getStyle(), (f.getSize() - 2));
    }

    public static void adjustLabelFont(JLabel label, Color color, boolean bigger) {
        if (bigger) {
            Font f = new Font(label.getFont().getName(),
                    label.getFont().getStyle(), (label.getFont().getSize() + 2));
            label.setFont(f);
        } else {
            Font f = new Font(label.getFont().getName(),
                    label.getFont().getStyle(), (label.getFont().getSize() - 2));
            label.setFont(f);
        }
        label.setForeground(color);

    }

    /**
     * Sorts a list of numeric strings in natural number order
     */
    public static List<String> numericSort(List<String> list) {
        Collections.sort(list, new NumericSortComparator());
        return list;
    }

    static class NumericSortComparator implements Comparator<String> {

        @Override
        public int compare(String s, String t) {
            if (!isNumeric(s) || !isNumeric(t)) {
                throw new IllegalArgumentException("Input list has non-numeric characters");
            }
            int sInt = Integer.parseInt(s);
            int tInt = Integer.parseInt(t);
            if (sInt < tInt) {
                return -1;
            } else if (sInt == tInt) {
                return 0;
            } else {
                return 1;
            }
        }
    }

    public static boolean isNumeric(String s) {
        if (s == null) {
            return false;
        }
        try {
            Integer.parseInt(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void centerFrameOnDesktop(Component f) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        int height = toolkit.getScreenSize().height;
        int width = toolkit.getScreenSize().width;
        int frameHeight = f.getSize().height;
        int frameWidth = f.getSize().width;
        f.setLocation(((width - frameWidth) / 2), (height - frameHeight) / 3);
    }
}
