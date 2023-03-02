/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * This is a utility class called "MouseListenerUtil" that contains a single static method called "removeMouseListener". 
 * This method takes in three parameters: a JPanel, and two JLabels.* 
 * The method iterates through all MouseListeners of the JPanel, JLabel 1 and JLabel 2, and removes them using the removeMouseListener method. 
 * This would be used to remove any previously added MouseListeners to these components, allowing new MouseListeners to be added or for the components 
 * to no longer respond to mouse events.
 * 
 */
public class MouseListenerUtil {
    public static void removeMouseListener(JPanel panel, JLabel label1, JLabel label2) {
        for (MouseListener ml : panel.getMouseListeners()) {
            panel.removeMouseListener(ml);
        }
        for (MouseListener ml : label1.getMouseListeners()) {
            label1.removeMouseListener(ml);
        }
        for (MouseListener ml : label2.getMouseListeners()) {
            label2.removeMouseListener(ml);
        }
    }
}
