package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class PlantInfoPannel extends JPanel {
     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private static final LayoutManager FlowLayout = null;
	JLabel sensorStatusLabel;
     JLabel lastWateredLabel;
     JLabel plantTypeLabel;
     String plantType = "Nespecificat";
     String lastWateredDate = "-";
     boolean sensorConnected = false;
     Date date=new Date();
      GradientLabel gradientLabel;

    public PlantInfoPannel(GradientLabel gradientLabel) {
    	this.gradientLabel = gradientLabel;
        setLayout(null);
        setBackground(new Color(230, 255, 230));
        setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                "Detalii planta",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 14)
        ));

        sensorStatusLabel = new JLabel("Senzor conectat: Nu");
        sensorStatusLabel.setBounds(10, 10, 200, 100);
        sensorStatusLabel.setVisible(true);
        lastWateredLabel = new JLabel("Ultima udare: -");
        lastWateredLabel.setBounds(10, 50, 200, 150);
        lastWateredLabel.setVisible(true);
        plantTypeLabel = new JLabel("Tip planta: " + plantType);
        plantTypeLabel.setBounds(10, 100, 200, 200);
        plantTypeLabel.setVisible(true);

        JButton changePlantButton = new JButton("Schimba tipul plantei");
        changePlantButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newPlant = JOptionPane.showInputDialog(
                        PlantInfoPannel.this,
                        "Introdu numele plantei:",
                        "Schimba tipul plantei",
                        JOptionPane.PLAIN_MESSAGE
                );
                if (newPlant != null && !newPlant.trim().isEmpty()) {
                    plantType = newPlant.trim();
                    updateLabels();
                }
            }
        });

        changePlantButton.setBounds(20, 250, 190, 30);
        changePlantButton.setVisible(true);

        add(sensorStatusLabel);
        repaint();
        revalidate();
        
        add(lastWateredLabel);
       
        add(plantTypeLabel);
        
        add(changePlantButton);
    }
    public void setdate()
    {
    	if (gradientLabel.countZero==0 && gradientLabel.countOne==1)
    	{
    		date=new Date();
    	}
    }

    public void setSensorConnected(boolean connected) {
        sensorConnected = connected;
        sensorStatusLabel.setText("Senzor conectat: " + (connected ? "Da" : "Nu"));
    }

    public void setLastWateredNow() {
    	setdate();
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        lastWateredDate = formatter.format(date);
        updateLabels();
    }

    private void updateLabels() {
        sensorStatusLabel.setText("Senzor conectat: " + (sensorConnected ? "Da" : "Nu"));
        lastWateredLabel.setText("Ultima udare: " + lastWateredDate);
        plantTypeLabel.setText("Tip planta: " + plantType);
        repaint();
        revalidate();
        }
} 