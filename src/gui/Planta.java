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

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import javax.swing.JTextField;
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
		
		
		
		
		
		


		
		
		
	}
}
