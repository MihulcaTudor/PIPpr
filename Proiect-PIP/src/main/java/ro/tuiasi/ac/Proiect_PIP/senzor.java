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
        
        // Inițializare LCD (I2C la adresa 0x27)
        I2CConfig i2cConfig = I2C.newConfigBuilder(pi4j)
            .bus(1)               // bus I2C
            .device(0x27)         // adresa I2C LCD (poate fi 0x3F în unele cazuri)
            .id("lcd")
            .name("LCD Display")
            .build();
        
        var lcd = pi4j.create(i2cConfig);
        
        LCDDisplay lcdisp = new LCDDisplay(lcd);
        // Mesaj de start
        lcdisp.write("Monitorizare", "");
        
        System.out.println("Monitorizare senzor de umiditate...");

        // Citim senzorul la fiecare secundă
        while (true) {
            if (sensor.state().isHigh()) {
                System.out.println("sol uscat");
                lcdisp.write("Sol uscat!", "");
            } else {
                System.out.println("solul este umed!");
                lcdisp.write("Sol umed!", "");
            }
            Thread.sleep(1000);
        }
        
    }
}