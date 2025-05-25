package gui;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Teste pentru clasa GradientLabel
 */
public class GradientLabelTest {

    private GradientLabel label;

    /**
     * Se apeleaza inainte de fiecare test
     * Creeaza un obiect nou pentru test
     */
    @BeforeEach
    public void setUp() {
        label = new GradientLabel("Test");
        label.setCountOne(0);
        label.setCountZero(0);
        label.setCountDead(0);;
    }

    /**
     * Test pentru valoarea "1" cand countOne este sub 20
     * Verificam textul afisat si contorii
     */
    @Test
    public void testSetStatusValueOne_Under20() {
        label.setStatusValue("1");
        assertEquals(1, label.getCountOne());
        assertEquals(0, label.getCountZero());
        assertEquals("Planta este bine udata ", label.getText());
    }

    /**
     * Test pentru valoarea "1" cand countOne este peste 20
     * Verificam daca mesajul afisat este diferit
     */
    @Test
    public void testSetStatusValueOne_Over20() {
        label.setCountOne(20);
        label.setStatusValue("1");
        assertEquals("Planta este bine udata", label.getText());
    }

    /**
     * Test pentru valoarea "0" cand countZero este sub 20
     */
    @Test
    public void testSetStatusValueZero_Under20() {
        label.setStatusValue("0");
        assertEquals(1, label.getCountZero());
        assertEquals("Planta are nevoie de apa ", label.getText());
    }

    /**
     * Test pentru valoarea "0" cand countZero este peste 20
     */
    @Test
    public void testSetStatusValueZero_Over20() {
        label.setCountZero(20);
        label.setStatusValue("0");
        assertEquals("Planta este uscata ", label.getText());
    }

    /**
     * Test pentru valoarea "0" cand countZero este peste 100
     * Planta este considerata moarta
     */
    @Test
    public void testSetStatusValueZero_Over100() {
        label.setCountZero(100);
        label.setStatusValue("0");
        assertEquals("Planta este moarta ", label.getText());
        assertEquals(1, label.getCountdead());
    }

    /**
     * Test pentru valoare necunoscuta (nu este 0 sau 1)
     */
    @Test
    public void testSetStatusValueInvalid() {
        label.setStatusValue("x");
        assertEquals("Status necunoscut", label.getText());
        assertEquals(0, label.getCountOne());
        assertEquals(0, label.getCountZero());
    }
}
