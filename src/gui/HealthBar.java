package gui;



import javax.swing.*;
import java.awt.*;
/**
 * HealthBar este o componenta personalizata bazata pe JProgressBar
 * care indica starea de sanatate a plantei in functie de nivelul de umiditate.
 * Valorile sunt exprimate procentual: 100% inseamna sol umed, 0% inseamna ca planta este aproape moarta.
 */
public class HealthBar extends JPanel {
    private final JProgressBar progressBar;
    private final GradientLabel gradientLabel;
    /**
     * Constructorul clasei HealthBar.
     * Creeaza un JProgressBar personalizat si il configureaza pentru afisare estetica.
     *
     * @param gradientLabel referinta catre GradientLabel, folosit pentru a accesa nivelul actual
     */
    public HealthBar(GradientLabel gradientLabel) {
        this.gradientLabel = gradientLabel;

        setLayout(new BorderLayout());
        setOpaque(false);
        setVisible(true);
        setBounds(280, 480, 250, 30);

        progressBar = new JProgressBar(0, 100) {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int width = getWidth();
                int height = getHeight();
                int arc = 20;

            
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, width, height, arc, arc);

         
                int progressWidth = (int) (((double) getValue() / getMaximum()) * width);
                g2.setColor(getForeground());
                g2.fillRoundRect(0, 0, progressWidth, height, arc, arc);

          
                g2.setColor(Color.BLACK);
                g2.setFont(getFont());
                String text = getString();
                FontMetrics fm = g2.getFontMetrics();
                int textWidth = fm.stringWidth(text);
                int textHeight = fm.getAscent();
                g2.drawString(text, (width - textWidth) / 2, (height + textHeight) / 2 - 2);

                g2.dispose();
            }
        };

        progressBar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        progressBar.setForeground(new Color(76, 175, 80)); 
        progressBar.setBackground(new Color(230, 230, 230));
        progressBar.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        progressBar.setStringPainted(true);

        add(progressBar, BorderLayout.CENTER);

        updateProgress();
    }
    /**
     * Actualizeaza valoarea progress bar-ului in functie de nivelul curent din GradientLabel.
     * Formula de calcul: nivel = 100 - countZero (ex: 0 citiri = 100%, 100 citiri = 0%)
     */
    public void updateProgress() {
        int countZero = gradientLabel.countZero;
        

      
        int progress = Math.max(0, 100 - countZero); // 100 citiri de 0 => 0%
        progressBar.setValue(progress);
        progressBar.setString("Sanatate planta: " + progress + "%");
        repaint();
    }
}