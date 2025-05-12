package gui;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Teste unitare pentru clasa GradientLabel.
 * Se testeaza comportamentul metodei setStatusValue(String valoare)
 * in functie de numarul de citiri consecutive "0" sau "1".
 */
class GradientLabel_test {

    /**
     * Testeaza daca mesajul este cel corect dupa 21 de citiri consecutive de "1".
     * Ar trebui sa indice ca planta este bine udata.
     */
    @Test
    void testUdatExcesiv() {
        GradientLabel label = new GradientLabel("");

        for (int i = 0; i < 21; i++) {
            label.setStatusValue("1");
        }

        String textAfisat = label.getText();
        assertTrue(textAfisat.contains("bine udata"), "Mesajul trebuie sa spuna ca planta este bine udata");
    }

    /**
     * Testeaza daca mesajul este cel corect dupa 101 citiri consecutive de "0".
     * Ar trebui sa indice ca planta este moarta.
     */
    @Test
    void testPlantaMoarta() {
        GradientLabel label = new GradientLabel("");

        for (int i = 0; i < 101; i++) {
            label.setStatusValue("0");
        }

        String textAfisat = label.getText();
        assertTrue(textAfisat.contains("moarta"), "Mesajul trebuie sa spuna ca planta este moarta");
    }

    /**
     * Testeaza daca mesajul este cel corect dupa 25 de citiri consecutive de "0".
     * Ar trebui sa indice ca planta este uscata.
     */
    @Test
    void testPlantaUscata() {
        GradientLabel label = new GradientLabel("");

        for (int i = 0; i < 25; i++) {
            label.setStatusValue("0");
        }

        String textAfisat = label.getText();
        assertTrue(textAfisat.contains("uscata"), "Mesajul trebuie sa spuna ca planta este uscata");
    }
}
