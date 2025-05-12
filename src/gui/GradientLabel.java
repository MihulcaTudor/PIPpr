package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class GradientLabel extends JLabel {
	 private Color colorStart = Color.LIGHT_GRAY;
	 private Color colorEnd = Color.DARK_GRAY;
	 private int countOne = 0;
	 private int countZero = 0;
	 
		
	/**
	 * Constructor pentru GradiendLabel: seteaza textul, culoarea si fontul textului, alinierea
	 * @param text
	 * textul afisat pe label
	 */
    public GradientLabel(String text) {
    	
        super(text);
        setOpaque(false); // 
        setForeground(Color.WHITE);
        setFont(new Font("Arial", Font.BOLD, 16));
        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);
    }

    /**
     * Functie pentru afisarea textului respectiv nivelului de umiditate: pentru valori de 1, solul este OK, textul afisat corespunzator.-VERDE 
     * Pentru prea multe valori de 1 consecutive Labelul isi va schimba culoarea datorita udarii excesive.- GALBEN
     * Pentru mai putin de 20 de valori consecutive de 0, solul este uscat insa nu este o urgenta . GALBEN
     * Pentru mai mult de 20 de valori de 0, soul trebuia udat imediat- ROSU
     * pentru mai mult de 100 de valori de 0, plata este complet neglijata - GRI
     * @param valoare
     * valoarea citita din fisier: 0 sau 1
     */
    public void setStatusValue(String valoare) {
    	
    	    if ("1".equals(valoare)) {
    	        countOne++;
    	        countZero = 0;

    	        if (countOne > 20) {
    	            colorStart = new Color(255, 193, 7); // galben
    	            colorEnd = new Color(255, 160, 0);
    	            setText("Planta este bine udata (" + countOne + " citiri consecutive)");
    	            
    	        } else {
    	            colorStart = new Color(0, 200, 83); // verde
    	            colorEnd = new Color(0, 150, 136);
    	            setText("Planta este bine udata (" + countOne + " citiri consecutive)");
    	            
    	        }

    	    } else if ("0".equals(valoare)) {
    	        countZero++;
    	        countOne = 0;

    	        if (countZero > 100) {
    	            colorStart = new Color(97, 97, 97); // gri inchis
    	            colorEnd = new Color(33, 33, 33);
    	            setText("Planta este moarta (" + countZero + " citiri consecutive)");
    	        } else if (countZero > 20) {
    	            colorStart = new Color(244, 67, 54); // rosu
    	            colorEnd = new Color(183, 28, 28);
    	            setText("Planta este uscata (" + countZero + " citiri consecutive)");
    	            
    	        } else {
    	            colorStart = new Color(255, 235, 59); // galben deschis
    	            colorEnd = new Color(255, 193, 7);
    	            setText("Planta are nevoie de apa (" + countZero + " citiri consecutive)");
    	            
    	        }

    	    } else {
    	        countZero = 0;
    	        countOne = 0;
    	        colorStart = Color.LIGHT_GRAY;
    	        colorEnd = Color.DARK_GRAY;
    	        setText("Status necunoscut");
    	    }

    	    repaint();
    	}
    /**
     * Functie pentru colorarea gradienta a labelului
     */
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
       
        GradientPaint gp = new GradientPaint(0, 0, colorStart, getWidth(), getHeight(), colorEnd);
        g2d.setPaint(gp);
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30); //

        g2d.dispose();
        super.paintComponent(g);
    }
}
