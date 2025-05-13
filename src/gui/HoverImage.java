package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JWindow;
/**
 * 
 * @author AIDUL
 *
 *clasa pentru imagine care afiseaja un mesaj atunci cand este dat hover cu mouse ul pe deasupra. 
 *clasa nu a fost folosita in proiect incat nu se afisa corespunzator, obiectele au fost create individual
 */
public class HoverImage extends JPanel{
	JWindow tooltipWindow;
	JLabel tooltipLabel;
	JLabel imgLabel;
	public HoverImage(String path, String text)
	{
		setLayout(null);
        setOpaque(false); 
		
		imgLabel = new JLabel();
		imgLabel.setBounds(650, 150, 120, 120);
		ImageIcon icon = new ImageIcon(path);
		Image img = icon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
		imgLabel.setIcon(new ImageIcon(img));
		
		add(imgLabel);
     
   

     tooltipWindow = new JWindow();
     tooltipLabel = new JLabel(text);
     tooltipLabel.setBackground(new Color(255, 255, 210));
     tooltipLabel.setOpaque(true);
     tooltipLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
     tooltipWindow.getContentPane().add(tooltipLabel);
     tooltipWindow.pack();
     tooltipWindow.setAlwaysOnTop(true);
     

    
     imgLabel.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseEntered(MouseEvent e) {
         	if (imgLabel != null  && tooltipWindow != null) 
         	{
             Point location = imgLabel.getLocationOnScreen();
             tooltipWindow.setLocation(location.x + imgLabel.getWidth(), location.y);
             tooltipWindow.setVisible(true);
             
         	}else {
         	    System.out.println("imgLabel sau tooltipWindow este null");
         	}
         }
         

         @Override
         public void mouseExited(MouseEvent e) {
             tooltipWindow.setVisible(false);
         }
     });


}
public void setPlace(int x,int y,int w,int l) {
	
	imgLabel.setBounds(x,y,w,l);
	Point location = imgLabel.getLocationOnScreen();
	tooltipWindow.setLocation(location.x + imgLabel.getWidth(), location.y);
	
}
}