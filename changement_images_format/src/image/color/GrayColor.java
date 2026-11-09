package image.color;

/**
 * The class GrayColor
 */
public class GrayColor {
    public static final GrayColor BLACK = new GrayColor(0);
    public static final GrayColor WHITE = new GrayColor(255);

    private final int grayLevel;

    /**
     * the gray color
     * 
     * @param level the level color
     */
    public GrayColor(int level) {
        if (level < 0 || level > 255) {
            throw new IllegalArgumentException("Gray level must be between 0 and 255");
        }
        this.grayLevel = level;
    }

    /**
     * get the gray level
     * 
     * @return the gray level
     */
    public int getGrayLevel() {
        return grayLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof GrayColor))
            return false;
        GrayColor c = (GrayColor) o;
        return this.grayLevel == c.grayLevel;
    }
}
