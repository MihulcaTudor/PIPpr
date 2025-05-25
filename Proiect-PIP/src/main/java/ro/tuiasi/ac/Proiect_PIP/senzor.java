package ro.tuiasi.ac.Proiect_PIP;

import com.pi4j.Pi4J;
import com.pi4j.context.Context;
import com.pi4j.io.gpio.digital.*;

import com.pi4j.plugin.gpiod.provider.gpio.digital.GpioDDigitalInputProvider;
import com.pi4j.plugin.gpiod.provider.gpio.digital.GpioDDigitalOutputProvider;

import com.pi4j.plugin.linuxfs.provider.i2c.LinuxFsI2CProvider;
import com.pi4j.plugin.raspberrypi.platform.RaspberryPiPlatform;

import ro.tuiasi.ac.Proiect_PIP.LcdDisplay;
/**
 * Clasa principala pentru proiectul cu senzor de umiditate.
 * Aceasta initializeaza contextul Pi4J, configureaza un senzor digital pe GPIO17
 * si afiseaza starea solului (umed/uscat) pe un ecran LCD.
 *
 * Functionalitate:
 * - Citeste in mod repetat starea unui senzor digital conectat la pinul GPIO17.
 * - Afiseaza in consola si pe un display LCD mesajul corespunzator starii solului.
 * - Actualizeaza afisajul doar cand starea se schimba.
 *
 * Biblioteci folosite:
 * - Pi4J (cu provider GPIOD pentru GPIO si LinuxFS pentru I2C)
 *
 * @author Tudor
 */
public class senzor {
	/**
     * Punctul de intrare in aplicatie. Creeaza contextul Pi4J, configureaza senzorul
     * si porneste un loop infinit care verifica starea senzorului si actualizeaza afisajul.
     *
     * @throws InterruptedException daca thread-ul este intrerupt in timpul somnului
     */
    public static void main(String[] args) throws InterruptedException {

    	
        /**
         * Creeaza contextul Pi4J cu platforma Raspberry Pi si providerii necesari
         */
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


        /**
         * Configureaza pinul GPIO17 ca input digital cu rezistenta pull-down
         */
        DigitalInputConfig config = DigitalInput.newConfigBuilder(pi4j)
                .address(17)                        // GPIO17 = pin fizic 11
                .pull(PullResistance.PULL_DOWN)    // default LOW
                .build();
        /**
         * Creeaza obiectul senzor pe baza configuratiei
         */
        DigitalInput sensor = pi4j.create(config);
        
        /**
         * Creeaza obiectul LCD pentru afisare
         */
        LcdDisplay lcd = new LcdDisplay(pi4j);

        /**
         * Retine starea anterioara a senzorului pentru a detecta schimbarile
         */
        boolean previousState = false; // presupunem ca senzorul este LOW la inceput

        /**
         * Loop infinit care verifica starea senzorului si actualizeaza LCD-ul
         */
        while (true) {
            boolean currentState = sensor.state().isHigh();

            /**
             * Afiseaza in consola starea curenta
             */
            if (sensor.state().isHigh()) {
                System.out.println("solul este uscat!");
            } else {
                System.out.println("solul este umed!");
            }

            /**
             * Actualizeaza afisajul doar daca s-a schimbat starea
             */
            if (currentState != previousState) {
                if (currentState) {
                    lcd.displayText("Solul este uscat");
                } else {
                    lcd.displayText("Solul este umed");
                }
                previousState = currentState;
            }

            /**
             * Pauza de 1 secunda intre citiri
             */
            Thread.sleep(1000);
        }
    }
}
