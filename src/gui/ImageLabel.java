package gui;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Clasa pentru label ce afiseaza o imagine in functie de starea plantei
 * Extinde clasa GradientLabel incat utilizeaza functia setStatusValue, si se schimba proportional cu obiectul respectiv
 * @author AIDA
 *
 */
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
	public ImageLabel(String text,GradientLabel source) {
		super(text);
		setOpaque(true);
		setBounds(250, 75, 300, 300);
		initializarepoze();
		setVisible(true);
		 this.countZero = source.getCountZero();
	     this.countOne = source.getCountOne();
	     this.countdead=source.getCountdead();
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
	ImageIcon icnmid;
	ImageIcon icndead;
	
	
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
	    	ImageIcon sad=new ImageIcon("src/gui/images/sad.jpg");
			Image imgsad = sad.getImage().getScaledInstance(
				   getWidth(), getHeight(), Image.SCALE_SMOOTH);
			 icnsad = new ImageIcon(imgsad);
			
			ImageIcon happy=new ImageIcon("src/gui/images/happy.jpg");
			setOpaque(true);
			Image imghappy = happy.getImage().getScaledInstance(
					   getWidth(), getHeight(), Image.SCALE_SMOOTH);
			 icnhappy = new ImageIcon(imghappy);
			 ImageIcon mid=new ImageIcon("src/gui/images/mid.jpg");
				setOpaque(true);
				Image imgmid = mid.getImage().getScaledInstance(
						   getWidth(), getHeight(), Image.SCALE_SMOOTH);
			icnmid = new ImageIcon(imgmid);
			 ImageIcon dead=new ImageIcon("src/gui/images/dead.jpg");
				setOpaque(true);
				Image imgdead = dead.getImage().getScaledInstance(
						   getWidth(), getHeight(), Image.SCALE_SMOOTH);
			icndead = new ImageIcon(imgdead);
			
			
			
			
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

 	        if (countOne > 20 && countdead==0) {
 	          
 	            setIcon(icnhappy);
 	            repaint();
 	        } else if (countOne <=20 && countdead ==0){
 	            setIcon(icnhappy);
 	            repaint();
 	        }
 	        else {
 	        	setIcon(icndead);
 	        }

 	    } else if ("0".equals(valoare)) {
 	        countZero++;
 	        countOne = 0;

 	        if (countZero > 100) {
 	        	countdead=1;
 	        	setIcon(icndead);
 	        	repaint();
 	          
 	        } else if (countZero > 20 && countdead==0) {
 	            setIcon(icnsad);
 	            repaint();
 	        } else if (countZero<20&& countdead==0){
 	            setIcon(icnmid);
 	            repaint();
 	        }
 	        else {
 	        	setIcon(icnsad);
 	        	repaint();
 	        }

 	    } else {
 	        countZero = 0;
 	        countOne = 0;
 	      
 	    }

 	    repaint();
 	}

}
