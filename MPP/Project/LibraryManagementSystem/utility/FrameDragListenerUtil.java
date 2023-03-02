/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

import java.awt.Point;
/*
 * This is a custom MouseAdapter class named FrameDragListenerUtil that allows a user to drag and move a JFrame window by clicking and holding
 *  the mouse button on the frame. The class has three methods: mouseReleased, mousePressed, and mouseDragged. 
 *  The mouseReleased method sets the value of mouseDownCompCoords to null when the mouse button is released, 
 *  the mousePressed method sets the value of mouseDownCompCoords to the current mouse position when the mouse button is pressed, 
 *  and the mouseDragged method updates the position of the frame on the screen based on the current mouse position and the position of the mouse when the button 
 *  was first pressed.
 *  It also has a JFrame object as a member variable which is passed during the initialization of the class.
 *  It's used to move the frame to the new location on the screen by calling setLocation method with the new x,y coordinates.
 *  You can use this class by creating an instance of it and adding it as a mouse listener to the JFrame.
 */
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;

public class FrameDragListenerUtil extends MouseAdapter {

    private final JFrame frame;
    private Point mouseDownCompCoords = null;

    public FrameDragListenerUtil(JFrame frame) {
        this.frame = frame;
    }

    public void mouseReleased(MouseEvent e) {
        mouseDownCompCoords = null;
    }

    public void mousePressed(MouseEvent e) {
        mouseDownCompCoords = e.getPoint();
    }

    public void mouseDragged(MouseEvent e) {
        Point currCoords = e.getLocationOnScreen();
        frame.setLocation(currCoords.x - mouseDownCompCoords.x, currCoords.y - mouseDownCompCoords.y);
    }
}
