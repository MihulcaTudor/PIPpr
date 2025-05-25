package gui;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import javax.swing.*;
import java.io.*;

/**
 * Teste de baza pentru clasa PlantInfoPannel
 * Verificam afisarea starii senzorului si salvarea/recuperarea tipului de planta
 */
public class PlantInfoPannelTest {

    private DateLabel dummyDateLabel;
    private PlantInfoPannel panel;

    /**
     * Initializam obiectele inainte de fiecare test
     */
    @BeforeEach
    public void setUp() {
        dummyDateLabel = new DateLabel(null);
        panel = new PlantInfoPannel(dummyDateLabel);
    }

    /**
     * Stergem fisierul salvat dupa fiecare test
     */
    @AfterEach
    public void tearDown() {
        File file = new File("src\\gui\\plant_type.txt");
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * Test cand senzorul este conectat
     */
    @Test
    public void testSensorConnectionTrue() {
        panel.setSensorConnected(true);
        JLabel label = getLabelWithTextContaining("Senzor conectat", panel);
        assertNotNull(label);
        assertEquals("Senzor conectat: Da", label.getText());
    }

    /**
     * Test cand senzorul nu este conectat
     */
    @Test
    public void testSensorConnectionFalse() {
        panel.setSensorConnected(false);
        JLabel label = getLabelWithTextContaining("Senzor conectat", panel);
        assertNotNull(label);
        assertEquals("Senzor conectat: Nu", label.getText());
    }

    /**
     * Test pentru incarcarea tipului de planta din fisier
     */
    @Test
    public void testPlantTypePersistence() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("src\\gui\\plant_type.txt"));
        writer.write("Cactus");
        writer.close();

        PlantInfoPannel newPanel = new PlantInfoPannel(dummyDateLabel);
        assertEquals("Cactus", newPanel.getPlantType());
    }

    /**
     * Test ca obiectul DateLabel este setat corect
     */
    @Test
    public void testDateLabelReference() {
        assertSame(dummyDateLabel, panel.getDateLabel());
    }

    /**
     * Functie auxiliara pentru a gasi un JLabel dupa continutul textului
     */
    private JLabel getLabelWithTextContaining(String text, JPanel panel) {
        for (java.awt.Component comp : panel.getComponents()) {
            if (comp instanceof JLabel label && label.getText() != null && label.getText().contains(text)) {
                return label;
            }
        }
        return null;
    }
}

