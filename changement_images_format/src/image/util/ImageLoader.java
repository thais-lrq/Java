package image.util;

import java.util.Scanner;

import image.Image;
import image.color.GrayColor;

/**
 * Tool class to build an image from a PGM file.
 * 
 * @author JC
 *
 */
public class ImageLoader {
    /**
     * Reads a PGM image from file.
     * It is assumed that file respect the following PGM file syntax:
     * <ul>
     * <li>first line with string "P2"</li>
     * <li>second line : a comment</li>
     * <li>one int for width <code>w</code>, one int for height<code>h</code></li>
     * <li>one int for max gray level (not used here, we consider this level to be
     * 255 in our images)</li>
     * <li><code>w</code> x <code>h</code> integers between 0 and max (for us
     * max=255) for each pixel</li>
     * </ul>
     * 
     * @param fileName the name of the file with the image
     * @return the image built from the file
     */
    public static Image loadPGM(String fileName) {
        Scanner scan = new Scanner(Image.class.getResourceAsStream(fileName));

        scan.nextLine();
        scan.nextLine();
        int width = scan.nextInt();
        int height = scan.nextInt();
        scan.nextInt();
        Image result = new Image(width, height);
        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {
                result.changeColorPixel(y, x, new GrayColor(scan.nextInt()));
            }
        }
        return result;
    }

}
