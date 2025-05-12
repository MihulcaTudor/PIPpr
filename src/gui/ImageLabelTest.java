package gui;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import javax.swing.*;

/**
 * Teste unitare pentru ImageLabel.
 * Verifica daca imaginea setata este corecta in functie de valoarea primita.
 */
class ImageLabelTest {

    /**
     * Testeaza daca dupa valori consecutive "1", se seteaza iconul "happy".
     */
    @Test
    void testIconHappy() {
        ImageLabel label = new ImageLabel("");

        for (int i = 0; i < 5; i++) {
            label.setStatusValue("1");
        }

        Icon icon = label.getIcon();
        assertNotNull(icon, "Iconul nu ar trebui sa fie null dupa status 1");
    }

    /**
     * Testeaza daca dupa valori consecutive "0", se seteaza iconul "sad".
     */
    @Test
    void testIconSad() {
        ImageLabel label = new ImageLabel("");

        for (int i = 0; i < 30; i++) {
            label.setStatusValue("0");
        }

        Icon icon = label.getIcon();
        assertNotNull(icon, "Iconul nu ar trebui sa fie null dupa status 0");
    }
}
