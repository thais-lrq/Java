package image.util;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

import javax.swing.JFrame;
import javax.swing.JPanel;

import image.ImageInterface;
import image.Pixel;

/**
 * Class for images display.
 * 
 * @author jc
 */
public class ImageDisplayer {

    /**
     * calls display(img, title, 100, 100)
     * 
     * @param img   the displaid image
     * @param title the window's title
     * @see #display(ImageInterface, String, int, int)
     */
    public void display(ImageInterface img, String title) {
        this.display(img, title, 100, 100);
    }

    /**
     * display image img in a GUI window
     * 
     * @param img   the displaid image
     * @param title the window's title
     * @param x     the x position of the top left corner of window
     * @param y     the y position of the top left corner of window
     */
    public void display(ImageInterface img, String title, int x, int y) {
        BufferedImage bfImg = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
        WritableRaster raster = bfImg.getRaster();
        Pixel pixel;
        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {
                pixel = img.getPixel(i, j);
                int[] tabInt = { pixel.getColor().getGrayLevel(), pixel.getColor().getGrayLevel(),
                        pixel.getColor().getGrayLevel() };
                raster.setPixel(i, j, tabInt);
            }
        }
        JFrame frame = new JFrame(title);
        frame.setLocation(x, y);
        frame.addWindowListener(new CloseWindowEvent());
        frame.setSize(new Dimension(img.getWidth() + 15, img.getHeight() + 38));
        MyPanel thePanel = new MyPanel(bfImg);
        frame.add(thePanel);
        frame.setVisible(true);
    }

    private class MyPanel extends JPanel {
        private BufferedImage img;

        public MyPanel(BufferedImage img) {
            this.img = img;
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(this.img, 0, 0, this.img.getWidth(), this.img.getHeight(), null);
        }
    }

    private class CloseWindowEvent extends WindowAdapter {
        public void windowClosing(java.awt.event.WindowEvent e) {
            System.exit(0);
        }
    }
}
