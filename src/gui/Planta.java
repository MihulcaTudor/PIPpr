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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
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
	GradientLabel statusLabel;
	HealthBar healthbar;
	ImageLabel imageLabel;
	InfoLabel infolabel;
	PlantInfoPannel infoPanel;
	
	

	protected String ultimaValoare="";

	

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
		
		addWindowListener(new WindowAdapter() {
		    public void windowClosing(WindowEvent e) {
		        // Aici apelezi metoda saveCounters() de pe obiectul GradientLabel
		        statusLabel.saveCounters();

		        // Inchide aplicatia
		        System.exit(0);
		    }
		});
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
		
		JButton btnRefresh=new JButton("Refresh") ;
		btnRefresh.setBounds(100, 10, 85, 21);
		btnRefresh.setBackground(new Color(170,220,190));
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				imageLabel.countOne=0;
				imageLabel.countZero=0;
				imageLabel.countdead=0;
				infolabel.countOne=0;
				infolabel.countZero=0;
				infolabel.countdead=0;
				statusLabel.countOne=0;
				statusLabel.countZero=0;
				statusLabel.countdead=0;
			}
		});
		meniuprincipal.add(btnRefresh);
		
		statusLabel = new GradientLabel("Planta este sanatoasa");

		imageLabel=new ImageLabel(null,statusLabel);
		infolabel=new InfoLabel(null,statusLabel);
		statusLabel.setBounds(150,400,525,70);
		healthbar=new HealthBar(statusLabel);
		
		DateLabel dateLabel = new DateLabel(" ");

		
	    infoPanel = new PlantInfoPannel(dateLabel);
		infoPanel.setBounds(620, 80, 300, 300);
		infoPanel.setSensorConnected(true);
		
		
		meniuprincipal.add(infoPanel);
		meniuprincipal.add(statusLabel);
		meniuprincipal.add(imageLabel);
		meniuprincipal.add(infolabel);
		meniuprincipal.add(healthbar);
		
		
		
		
		
		
		startMonitoring(); // functia defiita mai jos
		
		
		
	}
	

	private void startMonitoring() {
        Thread monitor = new Thread(new Runnable() { //thread nou pt citit  constant
            public void run() {
                while (true) {
                	//bucla infinita 
                	
                    try {
                    	try (BufferedReader reader = new BufferedReader(new FileReader("src\\gui\\umiditate.txt"))) {
							String valoare = null;
							String linie;

							while ((linie = reader.readLine()) != null) {
								 {
								            valoare = linie; 						
								  }
								 if (valoare!=null) {
							//deci compara valoarea citita cu cea anterioara si numara de cate ori a fost consecutiv
							ultimaValoare = valoare;
							System.out.println("Valoare citita: " + valoare);  // DEBUG
							
							

							if (valoare.equals("1")) {

							    statusLabel.setStatusValue(valoare);
							    imageLabel.setStatusValue(valoare);
							    infolabel.setStatusValue(valoare);
							    infoPanel.getDateLabel().setStatusValue(valoare);
							    healthbar.updateProgress();

							} 
							else if (valoare.equals("0"))
							{
								    statusLabel.setStatusValue(valoare);
							        imageLabel.setStatusValue(valoare);
							        infolabel.setStatusValue(valoare);
							        infoPanel.getDateLabel().setStatusValue(valoare);
							        healthbar.updateProgress();
							}
							else {
								System.out.println("Valoare invalida: " + valoare);
								}
							}
								 }
						}
                        Thread.sleep(800); //citeste de doua ori pe min
                    } catch (Exception e) {
                        System.err.println("Eroare: " + e.getMessage());
                    }
                }
            }
        });

        monitor.start();
    }



		
		
		
	}

