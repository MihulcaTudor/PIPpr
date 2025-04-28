package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;

public class Planta extends JFrame {
	
	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	static int height = screenSize.height * 2 / 3;
	static int width = screenSize.width * 2 / 3;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
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
/*!!
	/**
	 * Create the frame.
	 */
	public Planta() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 564, 384);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(173,235,179));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(162, 231, 270, 73);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setBackground(new Color (128,239,128));
		
	}
}
