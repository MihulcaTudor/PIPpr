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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Meniuprincipal extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	GradientLabel gradientLabel;

	
	public  Meniuprincipal(GradientLabel gradientLabel)
	{
		this.gradientLabel=gradientLabel;
		this.setLayout(null);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setBackground(new Color(173,235,179));
		
		PlantInfoPannel infoPanel = new PlantInfoPannel(gradientLabel);
		infoPanel.setBounds(620, 80, 300, 300);
		add(infoPanel);
		infoPanel.setSensorConnected(true);
		infoPanel.setLastWateredNow();
	
		
	}

}
