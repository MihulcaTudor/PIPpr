package ro.tuiasi.ac.Proiect_PIP;

import ro.tuiasi.ac.Proiect_PIP.FakeI2C;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Clasa de test pentru I2CDevice.
 * Verifica scrierea in registru si trimiterea de date prin I2C folosind clasa FakeI2C.
 */
public class test_I2C {

    /**
     * Clasa derivata din I2CDevice care foloseste FakeI2C pentru testare.
     * Nu se face nicio configurare reala in init().
     */
    static class TestI2CDevice extends I2CDevice {
        public FakeI2C fakeI2C;

        public TestI2CDevice(FakeI2C i2c) {
            super(null, 0x27, "FakeDevice");
            this.fakeI2C = i2c;
            init(i2c); // initializam cu dispozitivul nostru fals
        }

        @Override
        protected void init(com.pi4j.io.i2c.I2C i2c) {
            // Nu facem nimic pentru ca nu avem hardware real
        }
    }

    /**
     * Testeaza metoda writeRegister pentru a verifica daca scrie in registrul corect.
     */
    @Test
    void testWriteRegister() {
        FakeI2C fake = new FakeI2C();
        TestI2CDevice device = new TestI2CDevice(fake);

        device.writeRegister(0x01, 123);

        assertEquals(0x01, fake.ultimaAdresa, "Registrul scris trebuie sa fie 0x01");
        assertEquals(123, fake.ultimaValoare, "Valoarea scrisa trebuie sa fie 123");
    }

    /**
     * Testeaza metoda write pentru a verifica daca trimite byte-ul corect.
     */
    @Test
    void testWriteByte() {
        FakeI2C fake = new FakeI2C();
        TestI2CDevice device = new TestI2CDevice(fake);

        device.write((byte) 0x5A);

        assertEquals((byte) 0x5A, fake.ultimaScriere, "Byte-ul trimis trebuie sa fie 0x5A");
    }

    /**
     * Testeaza metoda readRegister pentru a verifica daca returneaza valoarea corecta.
     */
    @Test
    void testReadRegister() {
        FakeI2C fake = new FakeI2C();
        TestI2CDevice device = new TestI2CDevice(fake);

        int valoare = device.readRegister(0x10);

        assertEquals(1234, valoare, "Valoarea returnata trebuie sa fie 1234 (valoare simulata)");
    }
}
