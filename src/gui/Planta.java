package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.CardLayout;

public class Planta extends JFrame {
	
	int level;
	
	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	static int height = screenSize.height * 2 / 3;
	static int width = screenSize.width * 2 / 3;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CardLayout cardlayout;
	private JPanel contentPane;
	//private JPanel help;
	private JLabel high;
	private JLabel mid;
	private JLabel low;
	GradientLabel statusLabel;
	ImageLabel imageLabel;
	
	//private JLabel text;
	//private JLabel textlow;
	//private JLabel textdead;
	//private JLabel textmid;

	protected String ultimaValoare;

	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Planta frame = new Planta();
					frame.setTitle("MyPlant");
					frame.setSize(new Dimension(width, height));
					frame.setVisible(true);
					frame.setLayout(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

  
 
	/**
	 * Create the frame.
	 */
	

    
 
	
	public Planta() {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 564, 384);
		
		cardlayout=new CardLayout();
		contentPane = new JPanel(cardlayout);
		this.add(contentPane);
		this.setVisible(true);
		
		
		Help help=new Help();
		Meniuprincipal meniuprincipal=new Meniuprincipal();
		
		contentPane.add(meniuprincipal,"MainMenu");
		contentPane.add(help,"Help");
		
		CircleButton btnHelp =new CircleButton("?");
		btnHelp.setBounds(10, 10, 85, 21);
		btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("buton help apasat");
				cardlayout.show(contentPane,"Help");
			}
		});
		meniuprincipal.add(btnHelp);
		
		CircleButton btnBack =new CircleButton("<-");
		btnBack.setBounds(10, 10, 85, 21);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("buton back apasat");
				cardlayout.show(contentPane,"MainMenu");
			}
		});
		help.add(btnBack);
		
//		//labeluri de forma pentru testare
//		
//		//high pentru sol umed
//		high=new JLabel();
//		ImageIcon happy=new ImageIcon("src/gui/images/happy.png");
//		high.setOpaque(true);
//		high.setBounds(250, 75, 300, 300);
//		Image imagineRedimensionata = happy.getImage().getScaledInstance(
//			   high.getWidth(), high.getHeight(), Image.SCALE_SMOOTH);
//		ImageIcon iconRedimensionat = new ImageIcon(imagineRedimensionata);
//		high.setIcon(iconRedimensionat);
//		meniuprincipal.add(high);
//		high.setVisible(false);
//		
//		
//		
//		
//		
//		//low pt sol uscat
//		low=new JLabel();
//		ImageIcon sad=new ImageIcon("src/gui/images/sad.png");
//		low.setOpaque(true);
//		low.setBounds(250, 75, 300, 300);
//		Image imagineRedimensionata2 = sad.getImage().getScaledInstance(
//			   low.getWidth(), low.getHeight(), Image.SCALE_SMOOTH);
//		ImageIcon iconRedimensionat2 = new ImageIcon(imagineRedimensionata2);
//		low.setIcon(iconRedimensionat2);
//		meniuprincipal.add(low);
//		low.setVisible(false);
		
//		text=new JLabel();
//		text.setFont(new Font("Segoe UI", Font.BOLD, 20));
//		text.setForeground(Color.WHITE);
//		text.setOpaque(true);
//		text.setBorder(BorderFactory.createCompoundBorder(
//		    new LineBorder(Color.BLACK, 2, true),
//		    BorderFactory.createEmptyBorder(10, 20, 10, 20)
//		));
//		text.setBounds(150,400,525,70);
//		text.setVisible(true);
//		text.setBackground(Color.PINK);
//		text.setText("test");
//		text.paintComponents(getGraphics())
//		meniuprincipal.add(text);
		
		imageLabel=new ImageLabel(null);
		statusLabel = new GradientLabel("Planta este sanatoasa");
		statusLabel.setBounds(150,400,525,70);
		meniuprincipal.add(statusLabel);
		meniuprincipal.add(imageLabel);
		
		
		
		
		startMonitoring(); // functia defiita mai jos
		
		
		
	}
	
//	protected void paintComponent(Graphics g) {
//	    Graphics2D g2d = (Graphics2D) g;
//	    GradientPaint gp = new GradientPaint(0, 0, Color.PINK, getWidth(), getHeight(), Color.ORANGE);
//	    g2d.setPaint(gp);
//	    g2d.fillRect(0, 0, getWidth(), getHeight());
//	    //super.paintComponent(g);
//	}
//	
	private void startMonitoring() {
        Thread monitor = new Thread(new Runnable() { //thread nou pt citit  constant
            public void run() {
                while (true) {
                	//bucla infinita 
                    try {
                        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\AIDUL\\git\\PIPpr\\src\\gui\\umiditate.txt"))) {
							String valoare = null;
							String linie;

							while ((linie = reader.readLine()) != null) {
							    valoare = linie; // pastreaza ultima linie citita
							}
							//cititor din fisier cu pathul de la mmn din calculator, am incercat si cu pathul de la package explorer dat nu e bun

							if (valoare != null) {
								//deci compara valoarea citita cu cea anterioara si numara de cate ori a fost consecutiv
							    ultimaValoare = valoare;

							    if (valoare.equals("1")) {
							       
							        statusLabel.setStatusValue(valoare);
							        imageLabel.setStatusValue(valoare);
							    } else if (valoare.equals("0")) {
							        
							        statusLabel.setStatusValue(valoare);
							        imageLabel.setStatusValue(valoare);
							        
							    }
							}
						}
                        Thread.sleep(500); //citeste de doua ori pe min
                    } catch (Exception e) {
                        System.err.println("Eroare: " + e.getMessage());
                    }
                }
            }
        });

        monitor.start();
    }

//    private void updateStatus(final int valoare) {
//        
//        SwingUtilities.invokeLater(new Runnable() { //sincer nu stiu ce e asta mi a sugerat chat sa fol invokelater SwingUtilities.invokeLater() este o metoda statica din Java Swing care iti permite sa rulezi cod pe firul grafic (EDT - Event Dispatch Thread).
//
//
//            public void run() { //daca citeste 1 o data planta ok
//                if (valoare == 1) {
//                    if (countOne <= 20) {
//                    		
//                       
//                       
//                       countZero=0;
//                       low.setVisible(false);
//                       high.setVisible(true);
//                       
//                        
//                    } else { //daca citeste de mai multe ori prea uda
//                    	
//                    	
//                    	high.setVisible(false);
//                    	low.setVisible(true);
//                       
//                    }
//                } else {
//                    if (countZero <= 20) { //daca citeste 0 o data planta uscata
//                    	
//                    	statusLabel.setStatusValue("0");
//                    	countOne=0;
//                    	low.setVisible(false);
//                    	high.setVisible(true);
//                    	
//                        
//                    } else { //daca citeste de mai multe ori etc
//                    	
//                    	
//                    	high.setVisible(false);
//                    	low.setVisible(true);
//                        
//                    }//aici voiam sa pun mai multe optiuni, asta e doar de forma , gen sa numere un anumit nr sa fie mai multe variante
//                }
//            }
//        });
  //  }

		
		
		
	}

