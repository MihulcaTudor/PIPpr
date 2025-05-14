package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
	JLabel sensorStatusLabel;
     DateLabel dateLabel;
     JLabel plantTypeLabel;
     JLabel messageLabel;
     String plantType ="Nespecificat";
     String lastWateredDate = "-";
     boolean sensorConnected = false;
    
     

    public PlantInfoPannel(DateLabel dateLabel) {
    	this.dateLabel=dateLabel;
    	loadPlantTypeFromFile();
    	
        setLayout(null);
        setBackground(new Color(255, 199, 44));
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
        add(sensorStatusLabel); 
      
        
        
        dateLabel.setBounds(10, 90, 200, 50);
        dateLabel.setVisible(true);
        add(dateLabel);
       
        
        
        plantTypeLabel = new JLabel("Tip planta: " + plantType);
        plantTypeLabel.setBounds(10, 100, 200, 200);
        plantTypeLabel.setVisible(true);
        add(plantTypeLabel);

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
                    savePlantTypeToFile() ;
                    updateLabels();
                }
            }
        });

        changePlantButton.setBounds(20, 250, 190, 30);
        changePlantButton.setBackground(new Color(255, 227, 80));
        changePlantButton.setVisible(true);

 
        
        add(changePlantButton);
    }
    

    public void setSensorConnected(boolean connected) {
        sensorConnected = connected;
        sensorStatusLabel.setText("Senzor conectat: " + (connected ? "Da" : "Nu"));
    }

    public DateLabel getDateLabel() {
        return dateLabel;
    }
    

    private void updateLabels() {
        sensorStatusLabel.setText("Senzor conectat: " + (sensorConnected ? "Da" : "Nu"));
        plantTypeLabel.setText("Tip planta: " + plantType);
        repaint();
        revalidate();
        }
    
    private void savePlantTypeToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src\\gui\\plant_type.txt"))) {
            writer.write(plantType);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void loadPlantTypeFromFile() {
        File file = new File("src\\gui\\plant_type.txt");
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line = reader.readLine();
                if (line != null && !line.trim().isEmpty()) {
                    plantType = line.trim();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
} 