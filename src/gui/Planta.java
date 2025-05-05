package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gui.Meniuprincipal.CircleButton;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.CardLayout;

public class Planta extends JFrame {
	
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
	private JTextField textField;

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
		setContentPane(contentPane);
		
		Help help=new Help();
		Meniuprincipal meniuprincipal=new Meniuprincipal();
		
		contentPane.add(meniuprincipal,"Main Menu");
		contentPane.add(help,"Help");
		
		CircleButton btnHelp =meniuprincipal.new CircleButton("?");
		btnHelp.setBounds(10, 10, 85, 21);
		btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("buton help apasat");
				cardlayout.show(contentPane,"Help");
			}
		});
		meniuprincipal.add(btnHelp);
		
	
		
		
		
		
		
		
		
		
	}
}
