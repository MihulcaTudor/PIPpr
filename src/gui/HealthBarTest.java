package gui;

import static org.junit.jupiter.api.Assertions.*;

import javax.swing.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Teste pentru clasa HealthBar
 */
public class HealthBarTest {

    private GradientLabel gradientLabel;
    private HealthBar healthBar;

    /**
     * Se apeleaza inainte de fiecare test
     * Creeaza obiectele necesare
     */
    @BeforeEach
    public void setUp() {
        gradientLabel = new GradientLabel("Test");
        healthBar = new HealthBar(gradientLabel);
    }

    /**
     * Test cand countZero este 0
     * Asteptam progres 100%
     */
    @Test
    public void testProgressFullHealth() {
        gradientLabel.setCountZero(0);
        healthBar.updateProgress();

        JProgressBar progressBar = (JProgressBar) healthBar.getComponent(0);
        assertEquals(100, progressBar.getValue());
        assertEquals("Sanatate planta: 100%", progressBar.getString());
    }

    /**
     * Test cand countZero este 50
     * Asteptam progres 50%
     */
    @Test
    public void testProgressHalfHealth() {
        gradientLabel.setCountZero(50);
        healthBar.updateProgress();

        JProgressBar progressBar = (JProgressBar) healthBar.getComponent(0);
        assertEquals(50, progressBar.getValue());
        assertEquals("Sanatate planta: 50%", progressBar.getString());
    }

    /**
     * Test cand countZero este 100
     * Asteptam progres 0%
     */
    @Test
    public void testProgressZeroHealth() {
        gradientLabel.setCountZero(100);
        healthBar.updateProgress();

        JProgressBar progressBar = (JProgressBar) healthBar.getComponent(0);
        assertEquals(0, progressBar.getValue());
        assertEquals("Sanatate planta: 0%", progressBar.getString());
    }

    /**
     * Test cand countZero este mai mare decat 100
     * Progresul trebuie sa ramana minim 0%
     */
    @Test
    public void testProgressNegativeProtection() {
        gradientLabel.setCountZero(120);
        healthBar.updateProgress();

        JProgressBar progressBar = (JProgressBar) healthBar.getComponent(0);
        assertEquals(0, progressBar.getValue());
        assertEquals("Sanatate planta: 0%", progressBar.getString());
    }
}

