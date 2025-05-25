package ro.tuiasi.ac.Proiect_PIP;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Clasa de test pentru logica de afisare a starii solului.
 * Testeaza daca mesajul generat este corect in functie de valoarea senzorului.
 */
public class test_senzor {

    /**
     * Testeaza mesajul pentru sol uscat.
     */
    @Test
    void testMesajUscat() {
        SenzorApp app = new SenzorApp();
        String rezultat = app.getMesajAfisaj(true);
        assertEquals("Solul este uscat", rezultat, "Mesajul pentru sol uscat trebuie sa fie corect");
    }

    /**
     * Testeaza mesajul pentru sol umed.
     */
    @Test
    void testMesajUmed() {
        SenzorApp app = new SenzorApp();
        String rezultat = app.getMesajAfisaj(false);
        assertEquals("Solul este umed", rezultat, "Mesajul pentru sol umed trebuie sa fie corect");
    }
}

