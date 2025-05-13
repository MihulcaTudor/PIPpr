package gui;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateLabel extends GradientLabel {
	protected Date date=new Date();
	
	public DateLabel(String text) {
		super(text);
		// TODO Auto-generated constructor stub
		setOpaque(false);
		
	}
	
	public Date getdate() {
		return date;
		
	}
	 public void setStatusValue(String valoare) {
	    	
 	    if ("1".equals(valoare)) {
 	        countOne++;
 	        countZero = 0;
 	        
 	    

 	    } else if ("0".equals(valoare)) {
 	        countZero++;
 	        countOne = 0;

 	    } else {
 	        countZero = 0;
 	        countOne = 0;
 	        
 	    }
 	    if (countZero==0 && countOne==1)
 	    {
 	    	date=new Date();
 	    	 SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
 	         String text = "Last watered: " + sdf.format(date);
 	         this.setText(text);
 	    }

 	    repaint();
 	}

}
