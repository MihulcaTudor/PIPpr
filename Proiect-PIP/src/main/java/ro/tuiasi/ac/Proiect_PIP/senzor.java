package ro.tuiasi.ac.Proiect_PIP;

import com.pi4j.Pi4J;
import com.pi4j.context.Context;
import com.pi4j.io.gpio.digital.*;
import com.pi4j.io.i2c.I2C;
import com.pi4j.io.i2c.I2CConfig;
import com.pi4j.io.i2c.I2CProvider;
import com.pi4j.util.Console;
import java.time.Duration;

import com.pi4j.library.pigpio.PiGpio;
import com.pi4j.library.pigpio.PiGpio_I2C;
import com.pi4j.plugin.linuxfs.provider.i2c.LinuxFsI2CProvider;
import com.pi4j.plugin.pigpio.provider.gpio.digital.PiGpioDigitalInputProvider;
import com.pi4j.plugin.pigpio.provider.gpio.digital.PiGpioDigitalOutputProvider;
import com.pi4j.plugin.pigpio.provider.pwm.PiGpioPwmProvider;
import com.pi4j.plugin.pigpio.provider.serial.PiGpioSerialProvider;
import com.pi4j.plugin.pigpio.provider.spi.PiGpioSpiProvider;
import com.pi4j.plugin.raspberrypi.platform.RaspberryPiPlatform;
import ro.tuiasi.ac.Proiect_PIP.LcdDisplay;




public class senzor {
    public static void main(String[] args) throws InterruptedException {
//!!!!!!
    	 var piGpio = PiGpio.newNativeInstance();
    	// Inițializează Pi4J
        
    	
    	
    	Context pi4j = Pi4J.newContextBuilder()
    			.noAutoDetect()
    			.add(new RaspberryPiPlatform() {
    				@Override
    				protected String[] getProviders() {
    					return new String[]{};
    				}
    			})
    			.add(PiGpioDigitalInputProvider.newInstance(piGpio),
    					PiGpioDigitalOutputProvider.newInstance(piGpio),
    					PiGpioSerialProvider.newInstance(piGpio),
    					PiGpioSpiProvider.newInstance(piGpio),
    					LinuxFsI2CProvider.newInstance()
    			)
    			.build();

    	

        

        // Configurează pinul GPIO17 ca input digital
        DigitalInputConfig config = DigitalInput.newConfigBuilder(pi4j)
            .address(17)                        // GPIO17 = pin fizic 11
            .pull(PullResistance.PULL_DOWN)    // default LOW
            .build();

        DigitalInput sensor = pi4j.create(config);
        
        LcdDisplay lcd = new LcdDisplay(pi4j);
        
        boolean previousState = false; // presupunem că senzorul este LOW la început

        while (true) {
            boolean currentState = sensor.state().isHigh();
            
            if(sensor.state().isHigh())
            {
            	System.out.println("solul este uscat!");
            }
            else
            {
            	System.out.println("solul este umed!");
            }
            
            if (currentState != previousState) {
                // Starea s-a schimbat, actualizăm LCD-ul
                if (currentState) {
                    
                    lcd.displayText("Solul este uscat");
                } else {
                    
                   lcd.displayText("Solul este umed");
                }
                previousState = currentState;
            }
            Thread.sleep(1000);
        }

        
    }
}