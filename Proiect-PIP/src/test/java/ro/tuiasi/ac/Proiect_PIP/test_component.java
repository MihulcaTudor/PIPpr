package ro.tuiasi.ac.Proiect_PIP;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

/**
 * Clasa de test pentru clasa abstracta Component.
 * Se testeaza metoda delay si reset.
 */
public class test_component {

    /**
     * Subclasa simpla pentru a putea instantia Component.
     */
    static class TestComponent extends Component {}

    /**
     * Testeaza ca metoda reset() nu arunca exceptii.
     */
    @Test
    void testResetDoesNotThrow() {
        TestComponent comp = new TestComponent();
        assertDoesNotThrow(comp::reset, "Metoda reset() nu trebuie sa arunce exceptii");
    }

    /**
     * Testeaza ca metoda delay() asteapta aproximativ cat trebuie.
     */
    @Test
    void testDelay() {
        TestComponent comp = new TestComponent();
        long start = System.currentTimeMillis();
        comp.delay(Duration.ofMillis(200));
        long end = System.currentTimeMillis();

        long durata = end - start;
        assertTrue(durata >= 190 && durata <= 300, "Delay-ul ar trebui sa dureze ~200ms");
    }
}

