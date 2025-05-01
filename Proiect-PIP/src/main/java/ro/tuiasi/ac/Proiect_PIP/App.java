package ro.tuiasi.ac.Proiect_PIP;

import com.pi4j.Pi4J;
import com.pi4j.io.gpio.digital.DigitalInput;
import com.pi4j.io.gpio.digital.DigitalState;
import com.pi4j.io.gpio.digital.PullResistance;
import com.pi4j.platform.Platforms;


public class App {
	
    
    public static void main(String[] args) {
    	var pi4j = Pi4J.newAutoContext();
    	
    	Platforms platforms = pi4j.platforms();

    	
    	pi4j.shutdown();
    }
}
