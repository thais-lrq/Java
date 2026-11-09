package image;

import image.color.GrayColor;

/**
 * The Pixel Class
 */
public class Pixel {
    private GrayColor color;

    public Pixel(GrayColor color) {
        this.color = color;
    }

    /**
     * Get the color
     * 
     * @return the color
     */
    public GrayColor getColor() {
        return this.color;
    }

    /**
     * Set the color
     * 
     * @param c the color
     */
    public void setColor(GrayColor c) {
        this.color = c;
    }

    /**
     * Equals
     */
    public boolean equals(Object o) {
        if (o instanceof Pixel) {
            Pixel other = (Pixel) o;
            return this.color.equals(other.color);
        } else {
            return false;
        }

    }

    /**
     * Difference values color of this pixel
     * 
     * @param p the pixel
     * @return an interger for the difference color
     */
    public int colorLevelDifference(Pixel p) {
        return Math.abs(this.color.getGrayLevel() - p.color.getGrayLevel());

    }
}
