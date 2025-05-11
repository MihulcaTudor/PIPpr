package gui;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ImageLabel extends GradientLabel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ImageLabel(String text) {
		super(text);
		initializarepoze();
		// TODO Auto-generated constructor stub
	}

	ImageIcon icnhappy;
	ImageIcon icnsad;
	  int countOne = 0;
	  int countZero = 0;
	 public void initializarepoze()
	    {
	    	ImageIcon sad=new ImageIcon("src/gui/images/sad.png");
	    	
			setOpaque(true);
			setBounds(250, 75, 300, 300);
			Image imgsad = sad.getImage().getScaledInstance(
				   getWidth(), getHeight(), Image.SCALE_SMOOTH);
			 icnsad = new ImageIcon(imgsad);
			
			ImageIcon happy=new ImageIcon("src/gui/images/happy.png");
			Image imghappy = happy.getImage().getScaledInstance(
					   getWidth(), getHeight(), Image.SCALE_SMOOTH);
			 icnhappy = new ImageIcon(imghappy);
			
			setVisible(true);
			
			
	    }
	 
	 @Override
	 public void setStatusValue(String valoare) {
	    	
 	  
		if ("1".equals(valoare)) {
 	        countOne++;
 	        countZero = 0;

 	        if (countOne > 20) {
 	          
 	            setIcon(icnsad);
 	            repaint();
 	        } else {
 	            setIcon(icnhappy);
 	            repaint();
 	        }

 	    } else if ("0".equals(valoare)) {
 	        countZero++;
 	        countOne = 0;

 	        if (countZero > 100) {
 	        	setIcon(icnsad);
 	        	repaint();
 	          
 	        } else if (countZero > 20) {
 	            setIcon(icnsad);
 	            repaint();
 	        } else {
 	            setIcon(icnhappy);
 	            repaint();
 	        }

 	    } else {
 	        countZero = 0;
 	        countOne = 0;
 	      
 	    }

 	    repaint();
 	}

}
