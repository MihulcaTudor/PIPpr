package gui;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class HoverImage {
    public static void main(String[] args) {
     

        
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon("src/gui/images/happy.jpg"); 
                g.drawImage(icon.getImage(), 50, 50, null);
            }
        };

        
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                
                JOptionPane.showMessageDialog(panel, "Aceasta este descrierea obiectului.");
            }
        });

       
    }
}