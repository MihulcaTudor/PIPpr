package ro.tuiasi.ac.Proiect_PIP;

import com.pi4j.Pi4J;
import com.pi4j.context.Context;
import com.pi4j.io.gpio.digital.*;
import com.pi4j.util.Console;

public class senzor {
    public static void main(String[] args) throws InterruptedException {
//!!!!!!
        // Inițializează Pi4J
        
        Context pi4j = Pi4J.newAutoContext();

        // Configurează pinul GPIO17 ca input digital
        DigitalInputConfig config = DigitalInput.newConfigBuilder(pi4j)
            .address(17)                        // GPIO17 = pin fizic 11
            .pull(PullResistance.PULL_DOWN)    // default LOW
            .build();

        DigitalInput sensor = pi4j.create(config);

        // Mesaj de start
        System.out.println("Monitorizare senzor de umiditate...");

        // Citim senzorul la fiecare secundă
        while (true) {
            if (sensor.state().isHigh()) {
                System.out.println("sol uscat");
            } else {
                System.out.println("solul este umed!");
            }
            Thread.sleep(1000);
        }
        
    }
}
