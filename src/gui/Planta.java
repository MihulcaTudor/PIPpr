package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FontMetrics;
import java.awt.Graphics;
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



import javax.swing.JTextField;
import javax.swing.SwingUtilities;
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

	protected String ultimaValoare;

	protected int countOne;

	protected int countZero;

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
	
//	void startReadingFilePeriodically(String filePath) {
//        
//
//       
//            try {
//                @SuppressWarnings("resource")
//				String valueStr = new BufferedReader(new FileReader(filePath)).readLine();
//                if (valueStr != null) {
//                    int value = Integer.parseInt(valueStr.trim());
//
//                    
//                        if (value == 1) {
//                        	low.setVisible(false);
//                            high.setVisible(true);
//                            
//                        } else if (value == 0) {
//                            high.setVisible(false);
//                            low.setVisible(true);
//                        } else {
//                            System.out.println("val necunoscuta");
//                        }
//                   
//                }
//            } catch (IOException | NumberFormatException e) {
//                System.err.println("Eroare la citirea fisierului: " + e.getMessage());
//            }
//        }
    
 
	
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
		
		
		JLabel high=new JLabel();
		ImageIcon happy=new ImageIcon("src/gui/images/happy.png");
		high.setOpaque(true);
		high.setBounds(250, 75, 300, 300);
		Image imagineRedimensionata = happy.getImage().getScaledInstance(
			   high.getWidth(), high.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon iconRedimensionat = new ImageIcon(imagineRedimensionata);
		high.setIcon(iconRedimensionat);
		meniuprincipal.add(high);
		high.setVisible(false);
		
		
		
		
		
		
		JLabel low=new JLabel();
		ImageIcon sad=new ImageIcon("src/gui/images/sad.png");
		high.setOpaque(true);
		low.setBounds(250, 75, 300, 300);
		Image imagineRedimensionata2 = sad.getImage().getScaledInstance(
			   low.getWidth(), low.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon iconRedimensionat2 = new ImageIcon(imagineRedimensionata2);
		low.setIcon(iconRedimensionat2);
		meniuprincipal.add(low);
		low.setVisible(false);
		
		
		startMonitoring();
		
		
		
	}
	private void startMonitoring() {
        Thread monitor = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\AIDUL\\git\\PIPpr\\src\\gui\\umiditate.txt"));
                        String valoare = reader.readLine();
                        reader.close();

                        if (valoare != null && !valoare.equals(ultimaValoare)) {
                            ultimaValoare = valoare;

                            if (valoare.equals("1")) {
                                countOne++;
                                updateStatus(1);
                            } else if (valoare.equals("0")) {
                                countZero++;
                                updateStatus(0);
                            }
                        }

                        Thread.sleep(500);
                    } catch (Exception e) {
                        System.err.println("Eroare: " + e.getMessage());
                    }
                }
            }
        });

        monitor.start();
    }

    private void updateStatus(final int valoare) {
        
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                if (valoare == 1) {
                    if (countOne == 1) {
                    		
                       System.out.println("Sol umed - Planta este fericita ");
                        
                    } else {
                    	System.out.println("Prea multa apa! (" + countOne + " valori de 1) ");
                       
                    }
                } else {
                    if (countZero == 1) {
                    	System.out.println("Sol uscat - Poate e timpul sa udam planta curand ");
                        
                    } else {
                    	System.out.println("Sol prea uscat! (" + countZero + " valori de 0)");
                        
                    }
                }
            }
        });
    }

		
		
		
		  
		
		
		
		
		


		
		
		
	}

