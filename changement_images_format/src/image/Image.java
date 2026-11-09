package image;

import image.color.*;

/**
 * The class Image
 */
public class Image implements ImageInterface {
    private int width;
    private int height;
    private Pixel[][] pixels;

    /**
     * Constructor image
     * @param width the width
     * @param height the height
     */
    public Image(int width, int height) {
        this.width = width;
        this.height = height;
        this.pixels = new Pixel[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixels[y][x] = new Pixel(GrayColor.WHITE);
            }
        }
    }

    /**
     * get the width
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * get the height
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * get the Pixel
     * 
     * @param x the coordonate x
     * @param y the coordonate y
     * @throws UnknownPixelException throw the exception if the pixels are outside
     *                               the table boundary
     */
    public Pixel getPixel(int x, int y) throws UnknownPixelException {
        if (x < 0 || x >= this.width || y < 0 || y >= this.height) {
            throw new UnknownPixelException("The pixels are outside the table boundary");
        }
        return pixels[y][x];
    }

    /**
     * Change the color of the Pixel
     * 
     * @param x     the coordonate x
     * @param y     the coordonate y
     * @param color the color
     * @throws UnknownPixelException throw the exception if the pixels are outside
     *                               the table boundary
     */
    public void changeColorPixel(int x, int y, GrayColor color) throws UnknownPixelException {
        if (x < 0 || x >= this.width || y < 0 || y >= this.height) {
            throw new UnknownPixelException("The pixels are outside the table boundary");
        }
        pixels[y][x].setColor(color);
    }

    /**
     * Fill a rectangle at a position with the width/heigth and the color
     * 
     * @param x      the coordinate x
     * @param y      the coordinate y
     * @param width  the width
     * @param height the heigth
     * @param color  the color
     */
    public void fillRectangle(int x, int y, int width, int height, GrayColor color) {
        for (int j = y; j < y + height && j < this.height; j++) {
            for (int i = x; i < x + width && i < this.width; i++) {
                if (i >= 0 && j >= 0) {
                    try {
                        changeColorPixel(i, j, color);
                    } catch (UnknownPixelException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
    }

    /**
     * The negative image
     * 
     * @return the negative image
     */
    public Image negative() {
        Image result = new Image(width, height);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int l = pixels[y][x].getColor().getGrayLevel();
                result.pixels[y][x].setColor(new GrayColor(255 - l));
            }
        }
        return result;
    }

    /**
     * Extract the contour of the image
     * @param threshold the threshold
     * @return the new image with the extraction on the edge
     */
    public Image edgeExtraction(int threshold){
        Image result = new Image(width, height);
        for (int y = 0; y < height - 1; y ++){
            for (int x = 0; x < width - 1; x++){
                Pixel current = pixels[y][x];
                Pixel rigth = pixels[y][x + 1];
                Pixel down = pixels[y+1][x];
                int diffR = current.colorLevelDifference(rigth);
                int diffD = current.colorLevelDifference(down);

                if (diffR > threshold || diffD > threshold){
                    try {
                        result.changeColorPixel(x, y, GrayColor.BLACK);
                    } catch (UnknownPixelException e){
                        System.out.println("Error pixel : " + e.getMessage());
                    }
                }
            }

        }
        return result;  
    }
}
