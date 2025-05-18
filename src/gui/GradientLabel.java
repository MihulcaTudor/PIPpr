package gui;

import java.awt.Color;
/**
 */
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
/**
 * Label cu culoare in degrade si colturi rotunjite
 * care isi schimba aparenta in functie de valorile citite din fisier, respectiv nivelul de umiditate al solului
 * 
 * 
 * @author AIDA
 */
public class GradientLabel extends JLabel {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Color colorStart = Color.LIGHT_GRAY;
	 protected Color colorEnd = Color.DARK_GRAY;
	 protected int countOne = 0;
	 protected int countZero = 0;
	 protected int countdead=0;
	 
		
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
        loadCounters();
    }
    
    /**
     * 
     * @return
     * De cate ori a fost citita valoarea 0- sol uscat
     */
    public int getCountZero() {
    	return countZero;
    }
    /**
     * 
     * @return
     * De cate ori a fost citita valoarea 1- sol umed
     */
    public int getCountOne() {
    	return countOne;
    }
    /**
     * 
     * @return
     * Starea plantei : 0- vie/ 1- moarta
     */
    public int getCountdead() {
    	return countdead;
    }
    /**
     * Seteaza numarul de cate ori a fost citita precedent valoarea 0- la lansarea aplicatiei
     * @param countZero
     */
    public void setCountZero(int countZero) {
    	this.countZero=countZero;
    }
    /**
     * Seteaza numarul de cate ori a fost citita precedent valoarea 1 - la lansarea aplicatiei

     * @param countOne
     */
    public void setCountOne(int countOne) {
    	this.countOne=countOne;
    }
    /**
     *Seteaza valoarea logica pentru starea plantei - la lansarea aplicatiei

     * @param countdead
     * 1=moarta
     * 0=vie
     */
    public void setCountDead(int countdead)
    {
    	this.countdead=countdead;
    }
    /**
     * Functie aplelata la inciderea ferestrei, care salveaza intr un fisier text toate counterele spre 
     * a fi incarcate la lansarea din nou a aplicatiei
     */
    public void saveCounters() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("src\\gui\\counters.txt"));
            writer.write(countZero + "\n");
            writer.write(countOne + "\n");
            writer.write(countdead+"\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Functie pentru citirea din fisier a valorilor salvate precedent ale counterelor ce indica
     * umiditatea/starea plantei
     */
    public void loadCounters() {
        File file = new File("src\\gui\\counters.txt");
        if (!file.exists()) {
            return;
        }
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String zeroLine = reader.readLine();
            String oneLine = reader.readLine();
            String deadLine=reader.readLine();
            if (zeroLine != null && oneLine != null&&deadLine!=null) {
                countZero = Integer.parseInt(zeroLine);
                countOne = Integer.parseInt(oneLine);
                countdead=Integer.parseInt(deadLine);
            }
            reader.close();
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }


    /**
     * Functie pentru afisarea textului respectiv nivelului de umiditate: pentru valori de 1, solul este OK, textul afisat corespunzator.-VERDE 
     * Pentru prea multe valori de 1 consecutive Labelul isi va schimba culoarea datorita udarii excesive.- GALBEN
     * Pentru mai putin de 20 de valori consecutive de 0, solul este uscat insa nu este o urgenta . GALBEN
     * Pentru mai mult de 20 de valori de 0, solul trebuia udat imediat- ROSU
     * pentru mai mult de 100 de valori de 0, plata este complet neglijata - GRI
     * @param valoare
     * valoarea citita din fisier: 0 sau 1
     */
    public void setStatusValue(String valoare) {
    	
    	    if ("1".equals(valoare)) {
    	        countOne++;
    	        countZero = 0;
    	        
    	        
    	        
    	        if (countOne > 20 && countdead==0) {
    	            colorStart = new Color(255, 193, 7); // galben
    	            colorEnd = new Color(255, 160, 0);
    	            setText("Planta este bine udata");
    	            
    	        } else if (countOne<=20 && countdead==0) {
    	            colorStart = new Color(0, 200, 83); // verde
    	            colorEnd = new Color(0, 150, 136);
    	            setText("Planta este bine udata ");
    	            
    	        }
    	        else {
    	        	colorStart = new Color(97, 97, 97); // gri inchis
    	            colorEnd = new Color(33, 33, 33);
    	            setText("Planta este moarta");
    	        	
    	        }

    	    } else if ("0".equals(valoare)) {
    	        countZero++;
    	        countOne = 0;

    	        if (countZero > 100) {
    	            colorStart = new Color(97, 97, 97); // gri inchis
    	            colorEnd = new Color(33, 33, 33);
    	            countdead=1;
    	            setText("Planta este moarta ");
    	            
    	        } else if (countZero > 20 && countdead==0) {
    	            colorStart = new Color(244, 67, 54); // rosu
    	            colorEnd = new Color(183, 28, 28);
    	            setText("Planta este uscata ");
    	            
    	        } else if(countZero<=20&&countdead==0){
    	            colorStart = new Color(255, 235, 59); // galben deschis
    	            colorEnd = new Color(255, 193, 7);
    	            setText("Planta are nevoie de apa ");
    	            
    	        }
    	        else {
    	        	 colorStart = new Color(97, 97, 97); // gri inchis
     	            colorEnd = new Color(33, 33, 33);
     	           setText("Planta este moarta ");
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
