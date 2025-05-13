package gui;

import java.awt.Color;

 
import java.awt.Font;

import javax.swing.SwingConstants;
/**
 * clasa pentru un label ce afiseaza un mesaj. mosteneste clasa gradientlabel pentru ca ii foloseste metodele
 * @author AIDA
 *
 */
public class InfoLabel extends GradientLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InfoLabel(String text) {
		
		super(text);
		setBounds(600,10,400,40);
		setVisible(true);
		setOpaque(false); // 
	    setForeground(Color.WHITE);
	    setFont(new Font("Arial", Font.BOLD, 11));
	    setHorizontalAlignment(SwingConstants.CENTER);
	    setVerticalAlignment(SwingConstants.CENTER);
	    colorStart=Color.ORANGE;
	    colorEnd=Color.GREEN;
		// TODO Auto-generated constructor stub
	}
	
	public void setStatusValue(String valoare) {
		if ("1".equals(valoare)) {
	        countOne++;
	        countZero = 0;

	        if (countOne > 20) {
	            
	            setText("Atentie! Ati inecat planta! Mai putina apa data viitoare :)");
	            
	        } else {
	          
	            
	            
	        }

	    } else if ("0".equals(valoare)) {
	        countZero++;
	        countOne = 0;

	        if (countZero > 100) {
	            
	            setText("Poate e timpul pentru o noua planta :(");
	        } else if (countZero > 20) {
	          
	            setText("Grabiti-va! Solul este uscat! ");
	            
	        } else {
	          
	            setText("Solul este proaspat udat! :)");
	            
	        }

	    } else {
	    	countZero = 0;
  	        countOne = 0;
  	       
	        setText("Verificati senzorul");
	    }

	    repaint();
	}

}
