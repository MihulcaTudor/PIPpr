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

public class DateLabel extends JLabel  {
	protected Date ultimaUdare;
	private int countZero=0;
	private int countOne=0;
	private boolean wasWatered = false;
	public DateLabel(String text) {
		
		setOpaque(false);
		setForeground(Color.BLACK);
		String loadedDate = loadLastWateredDate();
	    setText(text+loadedDate);
	}
	
	 public void setStatusValue(String valoare) {
	    	
 	    if ("1".equals(valoare)) {
 	        countOne++;
 	        if (countZero>0 && countOne ==1)
 	        {
 	        	ultimaUdare= new Date();
 	        	SimpleDateFormat sdf = new SimpleDateFormat("E HH:mm:ss");
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
	 public Date getUltimaUdare() {
		 return ultimaUdare;
	 }
	 
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
