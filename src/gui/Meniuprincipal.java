package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Meniuprincipal extends JPanel{
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	

	
	public  Meniuprincipal()
	{
		
		setLayout(null);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setBackground(new Color(255, 227, 80));
		
		JLabel helplbl=new JLabel("Help");
		helplbl.setBounds(40,15,80,50);
		helplbl.setOpaque(false);
		helplbl.setVisible(true);
		add(helplbl);
		
		JLabel info=new JLabel();
		info.setBounds(30,50,200,200);
		info.setVisible(true);
		info.setOpaque(false);
		info.setText("<html>Pentru<br/> informatii despre aplicatie<br/> apasa Help ^<br/> <br/> <br/> Pentru a reseta<br/> istoricul senzorului<br/> apasa Refresh^</html>");
		add(info);
		
		
		
	
		
	}

}
