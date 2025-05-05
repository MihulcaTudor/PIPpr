package pipProiect;

import com.pi4j.Pi4J;
import com.pi4j.context.Context;
import com.pi4j.io.gpio.digital.*;


public class SenzorUmiditate implements SenzorUmiditateInterface {

    private final DigitalInput sensor;

    public SenzorUmiditate() {
        Context pi4j = Pi4J.newAutoContext();

        DigitalInputConfig config = DigitalInput.newConfigBuilder(pi4j)
            .id("umiditate")
            .address(17) // GPIO 17
            .pull(PullResistance.PULL_DOWN)
            .build();

        sensor = pi4j.create(config);
    }

    @Override
    public boolean esteUmed() {
        return sensor.state().isHigh();
    }
}
