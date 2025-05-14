package gui;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;

public class DateLabel extends JLabel  {
	protected Date ultimaUdare;
	private int countZero=0;
	private int countOne=0;
	
	public DateLabel(String text) {
		
		setOpaque(false);
		setForeground(Color.BLACK);
		setText(text);
		
	}
	
	 public void setStatusValue(String valoare) {
	    	
 	    if ("1".equals(valoare)) {
 	        countOne++;
 	        if (countZero>0 && countOne ==1)
 	        {
 	        	ultimaUdare= new Date();
 	        	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                setText("Ultima udare: " + sdf.format(ultimaUdare));
                repaint();
                revalidate();
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

	 
}
