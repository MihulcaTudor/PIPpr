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
     DateLabel lastWateredLabel;
     JLabel plantTypeLabel;
     JLabel messageLabel;
     String plantType = "Nespecificat";
     String lastWateredDate = "-";
     boolean sensorConnected = false;
    
     // GradientLabel gradientLabel;

    public PlantInfoPannel(DateLabel lastWateredLabel) {
    	this.lastWateredLabel=lastWateredLabel;
    	//this.gradientLabel = gradientLabel;
        setLayout(null);
        setBackground(new Color(230, 255, 230));
        setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                "Detalii planta",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 14)
        ));
        
        messageLabel= new JLabel("Nu uita sa pui planta intr un loc luminos!");
        messageLabel.setBounds(10, 50, 250, 150);
        messageLabel.setVisible(true);

        sensorStatusLabel = new JLabel("Senzor conectat: Nu");
        sensorStatusLabel.setBounds(10, 10, 200, 100);
        sensorStatusLabel.setVisible(true);
        
        
//        lastWateredLabel.setBounds(10, 50, 200, 150);
//        lastWateredLabel.setVisible(true);
        
        
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
        
       // add(lastWateredLabel);
       
        add(messageLabel);
        add(plantTypeLabel);
        
        add(changePlantButton);
    }
    

    public void setSensorConnected(boolean connected) {
        sensorConnected = connected;
        sensorStatusLabel.setText("Senzor conectat: " + (connected ? "Da" : "Nu"));
    }

    public void setLastWatered() {
    	Date date=lastWateredLabel.getdate();
    	SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
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