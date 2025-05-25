package gui;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Teste de baza pentru clasa InfoLabel
 */
public class InfoLabelTest {

    private GradientLabel source;
    private InfoLabel infoLabel;

    /**
     * Se apeleaza inainte de fiecare test
     * Initializam obiectele necesare
     */
    @BeforeEach
    public void setUp() {
        source = new GradientLabel("Test");
        infoLabel = new InfoLabel("", source);
    }

    /**
     * Test pentru valoarea "1" cand countOne este sub 20
     */
    @Test
    public void testSetStatusValueOne_Under20() {
        infoLabel.setStatusValue("1");
        assertEquals("Bine ati venit in aplicatia MyPlant", infoLabel.getText());
    }

    /**
     * Test pentru valoarea "1" cand countOne este peste 20
     */
    @Test
    public void testSetStatusValueOne_Over20() {
        for (int i = 0; i < 21; i++) {
            infoLabel.setStatusValue("1");
        }
        assertEquals("Atentie! Ati inecat planta! Mai putina apa data viitoare :)", infoLabel.getText());
    }

    /**
     * Test pentru valoarea "0" cand countZero este sub 20
     */
    @Test
    public void testSetStatusValueZero_Under20() {
        infoLabel.setStatusValue("0");
        assertEquals("Solul este proaspat udat! :)", infoLabel.getText());
    }

    /**
     * Test pentru valoarea "0" cand countZero este peste 20
     */
    @Test
    public void testSetStatusValueZero_Over20() {
        for (int i = 0; i < 21; i++) {
            infoLabel.setStatusValue("0");
        }
        assertEquals("Grabiti-va! Solul este uscat! ", infoLabel.getText());
    }

    /**
     * Test pentru valoarea "0" cand countZero este peste 100
     */
    @Test
    public void testSetStatusValueZero_Over100() {
        for (int i = 0; i < 101; i++) {
            infoLabel.setStatusValue("0");
        }
        assertEquals("Poate e timpul pentru o noua planta :(", infoLabel.getText());
    }

    /**
     * Test pentru valoare invalida
     */
    @Test
    public void testSetStatusValueInvalid() {
        infoLabel.setStatusValue("x");
        assertEquals("Verificati senzorul", infoLabel.getText());
    }
}
