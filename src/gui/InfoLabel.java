package gui;

import java.awt.Color;

 
import java.awt.Font;

import javax.swing.SwingConstants;
/**
 * Clasa pentru un label ce afiseaza un mesaj. mosteneste clasa gradientlabel pentru ca ii foloseste metoda setStatusValue si isi
 * schimba aspectul proportional cu obiectul respectiv
 * @author AIDA
 *
 */
public class InfoLabel extends GradientLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Initializeaza label ul, ii pune marginile si culorile necesare
	 * @param text
	 * implicit NULL deoarece se schimba in functia setStatusValue
	 * @param source
	 * referinta la obiect de tip GradientLabel pentru a avea acces la countere
	 */

	public InfoLabel(String text,GradientLabel source) {
		
		super(text);
		setBounds(600,10,400,40);
		setVisible(true);
		setOpaque(false); // 
	    setForeground(Color.WHITE);
	    setFont(new Font("Arial", Font.BOLD, 11));
	    setHorizontalAlignment(SwingConstants.CENTER);
	    setVerticalAlignment(SwingConstants.CENTER);
	    colorStart=Color.ORANGE;
	    colorEnd=Color.PINK;
	    this.countZero = source.getCountZero();
	    this.countOne = source.getCountOne();
	    this.countdead=source.getCountdead();
		// TODO Auto-generated constructor stub
	}
	/**
	 * In functie de nivelul de umiditate citit si de numarul de repetari, 
	 * labelul isi schimba aspectul dupa cum urmeaza:
	 * pentru sol normal, afiseaza un mesaj de bun venit
	 * pentru supraudare afiseaza mesaj de atentionare
	 * pentru sol uscat afiseaza mesaj de atentionare catre udare
	 * pentru planta moarta afiseaza un mesaj trist
	 * pentru alta varianta afiseaza un mesaj de atentionare spre verificarea senzorului
	 */
	public void setStatusValue(String valoare) {
		if ("1".equals(valoare)) {
	        countOne++;
	        countZero = 0;

	        if (countOne > 20) {
	            
	            setText("Atentie! Ati inecat planta! Mai putina apa data viitoare :)");
	            
	        } else {
	        	setText("Bine ati venit in aplicatia MyPlant");
	          
	            
	            
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
