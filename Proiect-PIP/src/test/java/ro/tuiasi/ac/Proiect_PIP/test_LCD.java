package ro.tuiasi.ac.Proiect_PIP;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Clasa de test pentru LcdDisplay.
 * Folosim o clasa FakeI2C pentru a simula comportamentul unui dispozitiv I2C real.
 * Astfel putem testa fara sa fie conectat un LCD fizic.
 */
public class test_LCD {

    /**
     * Clasa derivata care extinde LcdDisplay si foloseste FakeI2C in locul unui I2C real.
     * Suprascriem metoda init() pentru a nu apela cod real.
     */
    static class TestLcd extends LcdDisplay {
        public FakeI2C fakeI2C;

        public TestLcd(FakeI2C i2c) {
            super(null, 2, 16, 0x27); // 2 randuri, 16 coloane, adresa standard
            this.fakeI2C = i2c;
            init(i2c); // Initializam cu dispozitivul fals
        }

        @Override
        protected void init(com.pi4j.io.i2c.I2C i2c) {
            // Nu apelam initializarea reala a LCD-ului
        }
    }

    /**
     * Testeaza daca activarea luminii de fundal scrie corect pe I2C.
     */
    @Test
    void testBacklightOn() {
        FakeI2C i2c = new FakeI2C();
        TestLcd lcd = new TestLcd(i2c);

        lcd.setDisplayBacklight(true);

        // Verificam daca bitul 3 (0x08) a fost setat => lumina de fundal activa
        assertTrue((i2c.ultimaScriere & 0x08) != 0, "Backlight ar trebui sa fie activat");
    }

    /**
     * Testeaza daca oprirea luminii de fundal scrie corect pe I2C.
     */
    @Test
    void testBacklightOff() {
        FakeI2C i2c = new FakeI2C();
        TestLcd lcd = new TestLcd(i2c);

        lcd.setDisplayBacklight(false);

        // Verificam daca bitul 3 NU este setat => lumina de fundal oprita
        assertEquals(0, i2c.ultimaScriere & 0x08, "Backlight ar trebui sa fie oprit");
    }

    /**
     * Testeaza daca metoda clearLine arunca exceptie pentru linii invalide.
     */
    @Test
    void testLinieGresita() {
        TestLcd lcd = new TestLcd(new FakeI2C());

        // clearLine(0) si clearLine(10) sunt invalide si trebuie sa arunce exceptie
        assertThrows(IllegalArgumentException.class, () -> lcd.clearLine(0));
        assertThrows(IllegalArgumentException.class, () -> lcd.clearLine(10));
    }

    /**
     * Testeaza daca textul lung este tratat corect si nu arunca exceptii.
     */
    @Test
    void testTextPreaLung() {
        TestLcd lcd = new TestLcd(new FakeI2C());

        // Textul este mai lung de 16 caractere, dar nu trebuie sa arunce exceptie
        assertDoesNotThrow(() -> lcd.displayLineOfText("Text foarte lung care nu incape", 1));
    }
}
