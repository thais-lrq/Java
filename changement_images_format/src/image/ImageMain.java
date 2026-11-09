package image;

import image.util.*;
import image.color.*;

public class ImageMain {
    public static void main(String[] args) {
        if (args.length == 0) {
        Image image = new Image(200, 150);
        image.fillRectangle(20, 30, 30, 50, GrayColor.BLACK);
        image.fillRectangle(50, 100, 40, 40, new GrayColor(128));
        image.fillRectangle(90, 20, 70, 50, new GrayColor(200));
        ImageDisplayer displayer = new ImageDisplayer();
        displayer.display(image, "Image with rectangles", 100, 100);


        }else{
            String filename = args[0];
            int threshold = args.length > 1 ? Integer.parseInt(args[1]) : 10;
            System.out.println("Image loading: " + filename);
            System.out.println("Contour extraction threshold: " + threshold);
            Image img = ImageLoader.loadPGM(filename);
            Image neg = img.negative();
            Image edges = img.edgeExtraction(threshold);
            ImageDisplayer displayer = new ImageDisplayer();

            int spacing = 20;
            int currentX = 0;

            displayer.display(img, "Original", currentX, 0);
            currentX += img.getWidth() + spacing;

             displayer.display(neg, "Negative", currentX, 0);
            currentX += img.getWidth() + spacing;
             displayer.display(edges, "Contour (threshold " + threshold + ")", currentX, 0);

        }
    }
}
