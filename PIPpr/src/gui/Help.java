package gui;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Help extends JPanel{
	JLabel lbHelp=new JLabel();
	
	
	 public Help()
	 {
		lbHelp.setOpaque(true);
		lbHelp.setText("test");
		lbHelp.setVisible(true);
		setLayout(null);
		lbHelp.setBounds(50,50,900,400);
		lbHelp.setBackground(Color.GREEN);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setBackground(new Color(173,235,179));
		add(lbHelp);
		
		
	 }

}
