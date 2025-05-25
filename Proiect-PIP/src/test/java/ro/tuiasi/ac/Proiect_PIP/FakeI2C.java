package ro.tuiasi.ac.Proiect_PIP;

import com.pi4j.io.i2c.I2C;
import java.io.IOException;

/**
 * Clasa FakeI2C folosita pentru testare.
 * Simuleaza comportamentul unui dispozitiv I2C fara a accesa hardware real.
 */
public class FakeI2C implements I2C {

	
	
    public byte ultimaScriere = 0;
    public int ultimaAdresa = 0;
    public int ultimaValoare = 0;

    /**
     * Simuleaza metoda de scriere a unui byte simplu.
     */
    @Override
    public void write(byte b) {
        this.ultimaScriere = b;
    }

    /**
     * Simuleaza scrierea unei valori intr-un registru.
     */
    @Override
    public void writeRegisterWord(int register, int value) {
        this.ultimaAdresa = register;
        this.ultimaValoare = value;
    }

    /**
     * Simuleaza citirea unei valori dintr-un registru.
     */
    @Override
    public int readRegisterWord(int register) {
        return 1234; // valoare simulata pentru test
    }

    // ======================
    // Urmatoarele metode sunt NEFOLOSITE in testele noastre, asa ca le lasam goale
    // ======================
    @Override public void write(byte[] buffer, int offset, int size) {}
    public void write(byte b, byte... data) {}
    @Override public void write(byte[] data) {}
    @Override public int read() { return 0; }
    @Override public int read(byte[] buffer, int offset, int size) { return 0; }
    @Override public int read(byte[] buffer) { return 0; }
    @Override public int readRegister(int register) { return 0; }
    @Override public void writeRegister(int register, byte b) {}
    @Override public void writeRegister(int register, byte[] data) {}
    public void writeRegister(int register, byte b, byte... data) {}
    public byte[] readRegister(int register, int length) { return new byte[0]; }
    @Override public void close() {}
}
