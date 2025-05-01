package pipProiect;

import com.pi4j.context.Context;
import com.pi4j.io.gpio.digital.*;
import com.pi4j.Pi4J;

public class SenzorUmiditate {

    public static void main(String[] args) throws Exception {
     
        Context pi4j = Pi4J.newAutoContext();

      
        var config = DigitalInput.newConfigBuilder(pi4j)
                .id("senzor-umiditate")
                .name("Senzor Umiditate")
                .address(17)
                .pull(PullResistance.PULL_DOWN)
                .debounce(3000L)
                .build();

        DigitalInput senzor = pi4j.create(config);

      
        var valoare = senzor.state();
        System.out.println("Valoarea senzorului de umiditate: " + valoare);

        
        pi4j.shutdown();
    }
}

