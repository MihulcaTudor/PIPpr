package gui;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Help extends JPanel{
	JLabel text=new JLabel();
	
	 public Help()
	 {
		 
		text.setText("tralalerotralala");
		this.setLayout(null);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setBackground(new Color(173,235,179));
		this.add(text);
		
		
	 }

}
