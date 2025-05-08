package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

public class RoundImage {
    public static ImageIcon getRoundedIcon(Image srcImage, int width, int height, int arcWidth, int arcHeight) {
        BufferedImage output = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = output.createGraphics();

        
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

     
        Shape clipShape = new RoundRectangle2D.Float(0, 0, width, height, arcWidth, arcHeight);
        g2.setClip(clipShape);

  
        g2.drawImage(srcImage.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);
        g2.dispose();

        return new ImageIcon(output);
    }
    }