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
	/**
	 * Constructor pentru ImageLabel:
	 * initializeaza imaginile folosite si
	 * seteaza vizibilitatea TRUE
	 * @param text
	 * implicit pentru ImageLabel: NULL
	 */
	public ImageLabel(String text) {
		super(text);
		initializarepoze();
		setVisible(true);
		// TODO Auto-generated constructor stub
	}

	/**
	 * icon pentru JLabel : umiditatea solului buna
	 */
	ImageIcon icnhappy;
	/**
	 * icon pentru JLabel: umiditatea solului scazuta
	 */
	ImageIcon icnsad;
	
	
	
	/**
	 * Variabila pentru contorizarea valorilor de 0 - sol uscat - transmise
	 */
	 int countOne = 0;
	 /**
	  *  Variabila pentru contorizarea valorilor de 1 - sol umed - transmise
	  */
	 int countZero = 0;
	 
	 /**
	  * Functie pentru initializarea imaginilor respective nivelurilor de umiditate
	  */
	 public void initializarepoze()
	    { 
	    	ImageIcon sad=new ImageIcon("src/gui/images/sad.png");
	    	setOpaque(true);
			setBounds(250, 75, 300, 300);
			Image imgsad = sad.getImage().getScaledInstance(
				   getWidth(), getHeight(), Image.SCALE_SMOOTH);
			 icnsad = new ImageIcon(imgsad);
			
			ImageIcon happy=new ImageIcon("src/gui/images/happy.png");
			setOpaque(true);
			Image imghappy = happy.getImage().getScaledInstance(
					   getWidth(), getHeight(), Image.SCALE_SMOOTH);
			 icnhappy = new ImageIcon(imghappy);
			
			
			
			
	    }
	 /**
	  * Functie pentru comportarea interfatei in functie de umiditatea plantei:
	  * Pentru 20 de valori de 0 consecutive, solul este uscat insa nu necesita atentie imediata
	  * Pentru mai mult de 20 de valori de 0, solul necesita apa 
	  * Pentru mai mult de 100 de valori de 0, planta este complet neglijata
	  * 
	  * pentru valori de 1, planta este OK, solul este umed
	  * pentru prea multe valori de 1 se atentioneaza utilizatorul asupra udarii excesive
	  * 
	  * @param valoare
	  *  valoarea citita de pe fisierul in care se incarca nivelul de umiditate - 0/1
	  */
	 @Override
	
	 public void setStatusValue(String valoare) {
	    	
 	  
		if ("1".equals(valoare)) {
 	        countOne++;
 	        countZero = 0;

 	   
 	            setIcon(icnhappy);
 	            repaint();
 	        

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
