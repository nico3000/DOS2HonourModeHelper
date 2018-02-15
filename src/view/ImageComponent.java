/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JComponent;

/**
 *
 * @author nicot
 */
public class ImageComponent extends JComponent {
    private BufferedImage image;

    public void setImage(URL imageUri) {
        try {
            this.image = imageUri == null ? null : ImageIO.read(imageUri);
        } catch (IOException ex) {
            Logger.getLogger(ImageComponent.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(this.image != null) {
            this.setPreferredSize(new Dimension(this.image.getWidth(), this.image.getHeight()));
        }
        this.repaint();
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(this.image == null ? Color.GRAY : Color.BLACK);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        if(this.image != null) {
            int x = 0;
            int y = 0;
            int w = this.getWidth();
            int h = this.getHeight();
            int iw = this.image.getWidth();
            int ih = this.image.getHeight();
            if((float)w / (float)h > (float)iw / (float)ih) {
                w = iw * h / ih;
                x = (this.getWidth() - w) / 2;
            } else {
                h = ih * w / iw;
                y = (this.getHeight() - h) / 2;
            }
            g.drawImage(this.image, x, y, w, h, this);
        } else {
            g.setColor(Color.DARK_GRAY);
            int x = 16;
            while(x - this.getHeight() < this.getWidth()) {
                int xPoints[] = { x, x + 16, x - this.getHeight() + 16, x - this.getHeight(), };
                int yPoints[] = { 0, 0, this.getHeight(), this.getHeight(), };
                g.fillPolygon(xPoints, yPoints, 4);
                x += 32;
            }
        }
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, this.getWidth() - 1, this.getHeight() - 1);
    }
}
