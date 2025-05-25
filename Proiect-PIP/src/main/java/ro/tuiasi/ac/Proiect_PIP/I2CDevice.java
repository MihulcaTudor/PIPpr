package ro.tuiasi.ac.Proiect_PIP;


import java.time.Duration;

import com.pi4j.context.Context;
import com.pi4j.io.i2c.I2C;

//Clasa abstracta care reprezinta un dispozitiv conectat prin I2C
public abstract class I2CDevice extends Component{

    /**
     * Bus-ul I2C implicit. Pe Raspberry Pi, de obicei este 1 (hex: 0x01)
     */
    protected static final int DEFAULT_BUS = 0x01;

    /**
     * Obiectul PI4J care reprezinta dispozitivul I2C
     */
    private final I2C i2c;

    /**
     * Constructorul clasei, primeste contextul PI4J, adresa dispozitivului si un nume
     * 
     * @param pi4j
     * @param device
     * @param name
     */
    protected I2CDevice(Context pi4j, int device, String name){
    	// Creeaza configuratia pentru dispozitivul I2C si il initializeaza
        i2c = pi4j.create(I2C.newConfigBuilder(pi4j)
                .id("I2C-" + DEFAULT_BUS + "@" + device)// Identificator unic
                .name(name+ "@" + device)// Numele dispozitivului
                .bus(DEFAULT_BUS)// Bus-ul I2C (de obicei 1)
                .device(device)// Adresa dispozitivului
                .build());
        // Apeleaza metoda abstracta pentru initializarea dispozitivului
        init(i2c);
        // Afiseaza un mesaj in consola
        System.out.println(" Initialized I2C device " + name);
    }


    /**
     * Trimite o comanda simpla catre dispozitiv
     * @throws InterruptedException daca este intrerupt firul de executie
     */
    protected void sendCommand(byte cmd) throws InterruptedException {
        i2c.write(cmd);
        Thread.sleep(1000);
    }
    /**
     * Citeste un registru de 16 biti de la dispozitiv
     * @param register adresa registrului
     * @return valoarea citita
     */
    protected int readRegister(int register) {
        return i2c.readRegisterWord(register);
    }

    /**
     * Scrie o valoare de 16 biti intr-un registru al dispozitivului
     * @param register adresa registrului
     * @param config valoarea de scris
     */
    protected void writeRegister(int register, int config) {
        i2c.writeRegisterWord(register, config);
    }

    /**
     * Trimite un singur byte de date catre dispozitiv
     * @param data byte-ul de trimis
     */
    protected void write(byte data){
        i2c.write(data);
    }

    /**
     * Trimite o comanda compusa dintr-un cod si un byte de date
     * @param command comanda de baza
     * @param data informatii suplimentare
     * @throws InterruptedException daca este intrerupt firul de executie
     */
    protected void sendCommand(byte command, byte data) throws InterruptedException {
        sendCommand((byte) (command | data));
    }
    /**
     * Metoda abstracta care va fi implementata de clasele derivate
     * pentru a realiza initializarea specifica a dispozitivului
     */
    protected abstract void init(I2C i2c);


}
