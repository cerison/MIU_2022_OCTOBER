package assignment8_Lesson9;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.util.Arrays;

public class TranslaterToLatin {
	public static void main(String[] args) {
		//new Translater();
		new Game();
	}
}

class Translater implements ActionListener{
	  JTextArea area;  
	  JButton b;
	  Translater() {  
	      JFrame f= new JFrame("Translater");  
	      area=new JTextArea();
	      area.setBounds(20,20,300,200);  
	      b=new JButton("Start");  
	      b.setBounds(90,240,120,30);  
	      b.addActionListener(this);  
	      f.add(area);f.add(b);
	      f.setSize(450,450);
	      f.setLayout(null);  
	      f.setVisible(true);  
	  }
	  public void actionPerformed(ActionEvent e){  
	      String text=area.getText();  
	      String words[]=text.split(" ");
	     // l1.setText("Words: "+words.length);  
	      //l2.setText("Characters: "+text.length());
	      for(int i=0;i<words.length;i++) {
	    	 String temp = words[i];
	    	 temp = temp.toLowerCase();
	    	 if(temp.charAt(0) == 'a' || temp.charAt(0) == 'e' || temp.charAt(0) == 'i' || temp.charAt(0) == 'o' || temp.charAt(0) == 'u') {
	    		 words[i] = words[i]+"way";
	    	 }
	    	 else {
	    		 words[i] = words[i].substring(1) + words[i].charAt(0) + "ay";
	    	 }
	      }
	      String newText = "";
	      for(String i : words) {
	    	  newText += i + " ";
	      }
	      String result = text + "\n ---------------------\n" + newText;
	      area.setText(result);
	  }  }

 class Game{
	 JButton jb1,jb2,jb3,jb4,jb5,jb6,jb7,jb8,jb9;
	 JLabel jl1;
	 JTextArea ta;
	 int randNumbers[] = new int[9];
	 Game(){
		 JFrame jf = new JFrame("Game");
		 
		 jb1 = new JButton("1");
		 jb1.setBounds(30,20,50,30);
		 jb2 = new JButton("2");
		 jb2.setBounds(100,20,50,30);
		 jb3 = new JButton("3");
		 jb3.setBounds(170,20,50,30);
		 
		 jb4 = new JButton("4");
		 jb4.setBounds(30,70,50,30);
		 jb5 = new JButton("5");
		 jb5.setBounds(100,70,50,30);
		 jb6 = new JButton("6");
		 jb6.setBounds(170,70,50,30);
		 
		 jb7 = new JButton("7");
		 jb7.setBounds(30,120,50,30);
		 jb8 = new JButton("8");
		 jb8.setBounds(100,120,50,30);
		 jb9 = new JButton("9");
		 jb9.setBounds(170,120,50,30);

		 jl1 = new JLabel("Click any 3 button to win the prize");
		 jl1.setBounds(30, 160, 240, 30);
		 
		 ta = new JTextArea();
		 ta.setBounds(30, 200, 190, 200);
		 jf.add(jb1);
		 jf.add(jb2);
		 jf.add(jb3);
		 jf.add(jb4);
		 jf.add(jb5);
		 jf.add(jb6);
		 jf.add(jb7);
		 jf.add(jb8);
		 jf.add(jb9);
		 jf.add(jl1);
		 jf.add(ta);
		 
		 jf.setSize(270,700);
		 jf.setLayout(null);
		 jf.setVisible(true);
		 
		 setRandomNumber();
	 }
	 public void setRandomNumber() {
		 for(int i = 0; i < 9; i++) {
			 randNumbers[i] = (int)(Math.random() * 10) + 1;
		 }
	 }
 }