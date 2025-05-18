package gui;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
/**
 * Label pentru afisarea ultimei date cand a fost udata planta. Ultima citire de la 0 la 1 
 * se memoreaza si se afiseaza pana la urmatoarea trecere, indiferent daca aplicatia a fost inchisa.
 * Data se salveaza intr un fisier txr care se suprascrie la fiecare udare
 * @author AIDUL
 *
 */

public class DateLabel extends JLabel  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Date ultimaUdare;
	private int countZero=0;
	private int countOne=0;
	public DateLabel(String text) {
		
		setOpaque(false);
		setForeground(Color.BLACK);
		String loadedDate = loadLastWateredDate();
	    setText(text+loadedDate);
	}
	/**
	 * Pentru trecerea de la 0 la 1 se creaza un obiect de tip data cu ziua si ora curenta
	 * si se apeleaza functia de suprascriere in fisier
	 * @param valoare
	 */
	 public void setStatusValue(String valoare) {
	    	
 	    if ("1".equals(valoare)) {
 	        countOne++;
 	        if (countZero>0 && countOne ==1)
 	        {
 	        	ultimaUdare= new Date(); // data curenta
 	        	SimpleDateFormat sdf = new SimpleDateFormat("E HH:mm:ss"); //ziua saptamani ora:min:sec
                setText("Ultima udare: " + sdf.format(ultimaUdare));
                repaint();
                revalidate();
                saveLastWateredDate("Ultima udare: " + sdf.format(ultimaUdare)); 
               
 	        }
 	        countZero = 0;
 	        
 	    

 	    } else if ("0".equals(valoare)) {
 	        countZero++;
 	        countOne = 0;

 	    } 
 	    System.out.println("setStatusValue apelat cu: " + valoare);
 	  

 	    repaint();
 	}
	 /**
	  * returneaza data ultimei udari
	  * @return
	  */
	 public Date getUltimaUdare() {
		 return ultimaUdare;
	 }
	 /**
	  * Se citeste din fisier ultima data valabila pentru afisarea la deschiderea aplicatiei
	  * pentru fisier inexistent/gol se afiseaza "No data available"
	  * @return
	  */
	 public String loadLastWateredDate() {
		    File file = new File("src\\gui\\last_watered.txt");
		    if (!file.exists()) {
		        return "No data available";
		    }
		    try {
		        BufferedReader reader = new BufferedReader(new FileReader(file));
		        String dateLine = reader.readLine();
		        reader.close();
		        return dateLine != null ? dateLine : "No data available";
		    } catch (IOException e) {
		        e.printStackTrace();
		        return "Error reading file";
		    }
		}
	 /**
	  * se suprascrie fisierul cu data data ca parametru
	  * @param dateText
	  * data curenta transformata in String pentru scrierea in fisier
	  */
	 public void saveLastWateredDate(String dateText) {
		    try {
		        BufferedWriter writer = new BufferedWriter(new FileWriter("src\\gui\\last_watered.txt"));
		        writer.write(dateText);
		        writer.close();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		}
	 
}
