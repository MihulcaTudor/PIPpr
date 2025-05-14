package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JWindow;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class Help extends JPanel{
	/**
	 * Creeaza un obiect de tip Jpanel ce contine: un panel cu scrollbar in care se gasesc informatii despre proiect si un  panel cu trei iconuri ce contin fiecare informatii despre ce este in imagini.
	 * informatiile apar atunci cand mouse ul da hover peste imagine. acestea sunt incluse intr un alt panel cu chenar si titlu
	 */
	 public Help()
	 {
		 setLayout(null);
	        setBackground(new Color(220, 255, 220)); // Verde pastelat

	        /**
	         *  Titlu
	         */
	        JLabel title = new JLabel("Ghid de utilizare");
	        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
	        title.setHorizontalAlignment(SwingConstants.CENTER);
	        title.setBounds(100, 10, 400, 30); // Centrat orizontal
	        add(title);

	       
	        JTextArea infoArea = new JTextArea();
	        infoArea.setText(
	        		"Bine ai venit in aplicatia MyPlant - asistentul tau digital pentru ingrijirea plantelor de apartament!\n\n" +
	        			    "Monitorizeaza automat umiditatea solului si ai grija de plantele tale ca un profesionist:\n\n" +
	        			    "Sol umed (conditii ideale):\n" +
	        			    "    - Bara de progres se apropie de 100%\n" +
	        			    "    - Fundal albastru si mesaj: \"Planta este sanatoasa\"\n\n" +
	        			    "Sol uscat (nevoie de udare):\n" +
	        			    "    - Bara de progres scade spre 0%\n" +
	        			    "    - Fundal galben si mesaj: \"Planta este uscata\"\n\n" +
	        			    "Planta moarta (peste 100 de citiri consecutive cu valoarea 0):\n" +
	        			    "    - Progres 0%, fundal rosu si mesaj de avertizare: \"Planta este moarta\"\n\n" +
	        			    "Butonul \"Refresh\":\n" +
	        			    "    - Reseteaza automat numaratoarea valorilor de umiditate (countOne si countZero)\n" +
	        			    "    - Foloseste-l atunci cand schimbi planta, pamantul sau doresti o repornire a monitorizarii\n\n" +
	        			    "Poti reveni oricand la acest ghid apasand butonul 'Help'.\n\n" +
	        			    "Pentru intrebari, sugestii sau suport: support@myplant.app\n\n" +
	        			    "Ingrijirea plantelor nu a fost niciodata mai simpla. MyPlant - sanatate pentru plantele tale!"
	        );
	        infoArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
	        infoArea.setLineWrap(true);
	        infoArea.setWrapStyleWord(true);
	        infoArea.setEditable(false);
	        infoArea.setOpaque(false);

	        JScrollPane scrollPane = new JScrollPane(infoArea);
	        scrollPane.setBounds(50, 60, 500, 300);
	        scrollPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
	        scrollPane.setOpaque(false);
	        scrollPane.getViewport().setOpaque(false);
	        add(scrollPane);
	        
	        JPanel hardwarePanel = new JPanel();
	        //hardwarePanel.setLayout(null); 
	        hardwarePanel.setBorder(BorderFactory.createTitledBorder(
	                BorderFactory.createLineBorder(Color.GRAY),
	                "Componente hardware",
	                TitledBorder.CENTER,
	                TitledBorder.TOP,
	                new Font("Arial", Font.BOLD, 16)
	        ));
	        hardwarePanel.setBackground(new Color(230, 255, 230));
	        hardwarePanel.setBounds(570, 50, 350, 400);
	        add(hardwarePanel);
	        
	        
//	        String path = "src/gui/Images/raspberry.jpeg";
//	        String text = "<html><b>Raspberry Pi Zero</b><br>- Microcontroller compact<br>- Consum redus<br>- Ideal pentru senzori</html>";

//	        HoverImage raspberry = new HoverImage(path, text);
//	       // raspberry.setBounds(650, 150, 120, 120); 
//	       // raspberry.setPlace(650, 150, 120, 120);
//	        add(raspberry);
	        
//	       String path="src/gui/images/raspberry.jpeg";
//	       String text="<html><b>Raspberry Pi Zero</b><br>- Microcontroller compact<br>- Consum redus<br>- Ideal pentru senzori</html>";
//	        HoverImage raspberry=new HoverImage(path,text);
//	        add(raspberry);
//	        raspberry.setVisible(true);
	//        raspberry.setPlace(650, 150, 120, 120);
	        
        JLabel raspberryLabel = new JLabel();
	        raspberryLabel.setBounds(650, 150, 120, 120);
	        ImageIcon icon = new ImageIcon("src/gui/images/raspberry.jpeg");
	        Image img = icon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
	        raspberryLabel.setIcon(new ImageIcon(img));
	        
	        hardwarePanel.add(raspberryLabel);

	        JWindow tooltipWindow = new JWindow();
	        JLabel tooltipLabel = new JLabel("<html><b>Raspberry Pi Zero</b><br>- Microcontroller compact<br>- Consum redus<br>- Ideal pentru senzori</html>");
	        tooltipLabel.setBackground(new Color(255, 255, 210));
	        tooltipLabel.setOpaque(true);
	        tooltipLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
	        tooltipWindow.getContentPane().add(tooltipLabel);
	        tooltipWindow.pack();
	        tooltipWindow.setAlwaysOnTop(true);	        

	       
	        raspberryLabel.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseEntered(MouseEvent e) {
	            	if (raspberryLabel != null  && tooltipWindow != null) 
	            	{
	                Point location = raspberryLabel.getLocationOnScreen();
	        		tooltipWindow.setLocation(location.x + raspberryLabel.getWidth(), location.y);
	                tooltipWindow.setVisible(true);
	                
	            	}else {
	            	    System.out.println("raspberryLabel sau tooltipWindow este null");
	            	}
	            }
            

	            @Override
	            public void mouseExited(MouseEvent e) {
	                tooltipWindow.setVisible(false);
	            }	        });
	        
	        JLabel sensorLabel = new JLabel();
	        sensorLabel.setBounds(800, 150, 120, 120);
	        ImageIcon icons = new ImageIcon("src/gui/images/sensor.jpg");
	        Image imgs = icons.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
	        sensorLabel.setIcon(new ImageIcon(imgs));
	        
	        hardwarePanel.add(sensorLabel);

	        JWindow tooltipWindows = new JWindow();
	        JLabel tooltipLabels = new JLabel("<html><b>Raspberry Pi Zero</b><br>- Microcontroller compact<br>- Consum redus<br>- Ideal pentru senzori</html>");
	        tooltipLabels.setBackground(new Color(255, 255, 210));
	        tooltipLabels.setOpaque(true);
	        tooltipLabels.setBorder(BorderFactory.createLineBorder(Color.GRAY));
	        tooltipWindows.getContentPane().add(tooltipLabels);
	        tooltipWindows.pack();
	        tooltipWindows.setAlwaysOnTop(true);	        

	        // 3. Mouse listener pentru hover logic
	        sensorLabel.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseEntered(MouseEvent e) {
	            	if (sensorLabel != null  && tooltipWindows != null) 
	            	{
	                Point location = sensorLabel.getLocationOnScreen();
	        		tooltipWindows.setLocation(location.x + sensorLabel.getWidth(), location.y);
	                tooltipWindows.setVisible(true);
	                
	            	}else {
	            	    System.out.println("sensorLabel sau tooltipWindows este null");
	            	}
	            }
            

	            @Override
	            public void mouseExited(MouseEvent e) {
	                tooltipWindows.setVisible(false);
	            }	        });
	        
	        JLabel lcdLabel = new JLabel();
	        lcdLabel.setBounds(725, 300, 120, 120);
	        ImageIcon iconl = new ImageIcon("src/gui/images/lcd.jpg");
	        Image imgl = iconl.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
	        lcdLabel.setIcon(new ImageIcon(imgl));
	        
	        hardwarePanel.add(lcdLabel);

	        JWindow tooltipWindowl = new JWindow();
	        JLabel tooltipLabell = new JLabel("<html><b>Raspberry Pi Zero</b><br>- Microcontroller compact<br>- Consum redus<br>- Ideal pentru senzori</html>");
	        tooltipLabell.setBackground(new Color(255, 255, 210));
	        tooltipLabell.setOpaque(true);
	        tooltipLabell.setBorder(BorderFactory.createLineBorder(Color.GRAY));
	        tooltipWindowl.getContentPane().add(tooltipLabell);
	        tooltipWindowl.pack();
	        tooltipWindowl.setAlwaysOnTop(true);	        

	        
	        lcdLabel.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseEntered(MouseEvent e) {
	            	if (lcdLabel != null  && tooltipWindowl != null) 
	            	{
	                Point location = lcdLabel.getLocationOnScreen();
	        		tooltipWindowl.setLocation(location.x + lcdLabel.getWidth(), location.y);
	                tooltipWindowl.setVisible(true);
	                
	            	}else {
	            	    System.out.println("lcdLabel sau tooltipWindowl este null");
	            	}
	            }
            

	            @Override
	            public void mouseExited(MouseEvent e) {
	                tooltipWindowl.setVisible(false);
	            }	        });
	        
	
	 	}
	 }