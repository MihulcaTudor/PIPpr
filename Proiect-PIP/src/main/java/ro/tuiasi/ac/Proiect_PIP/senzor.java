package ro.tuiasi.ac.Proiect_PIP;

import com.pi4j.Pi4J;
import com.pi4j.context.Context;
import com.pi4j.io.gpio.digital.*;

import com.pi4j.plugin.gpiod.provider.gpio.digital.GpioDDigitalInputProvider;
import com.pi4j.plugin.gpiod.provider.gpio.digital.GpioDDigitalOutputProvider;

import com.pi4j.plugin.linuxfs.provider.i2c.LinuxFsI2CProvider;
import com.pi4j.plugin.raspberrypi.platform.RaspberryPiPlatform;

import ro.tuiasi.ac.Proiect_PIP.LcdDisplay;

public class senzor {
    public static void main(String[] args) throws InterruptedException {

        // Inițializează Pi4J cu GPIOD pentru GPIO și LinuxFS pentru I2C
    	Context pi4j = Pi4J.newContextBuilder()
    		    .noAutoDetect()
    		    .add(new RaspberryPiPlatform() {
    		        @Override
    		        protected String[] getProviders() {
    		            return new String[]{};
    		        }
    		    })
    		    .add(
    		        GpioDDigitalInputProvider.newInstance(),
    		        GpioDDigitalOutputProvider.newInstance(),
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

            if (sensor.state().isHigh()) {
                System.out.println("solul este uscat!");
            } else {
                System.out.println("solul este umed!");
            }

            if (currentState != previousState) {
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
