package utils;

/**
 * The Direction enum represents the different possibilities
 */
public enum Direction {

    /** represents the moves for accessing the north */
    NORTH(1, 0), 
    /** represents the moves for acessing the east */
    EAST(0, 1), 
    /** represents the moves for accessing the south */
    SOUTH(-1, 0),
    /** represents the moves for accessing the west */
    WEST(0, -1);

    private int dx;
    private int dy;

    /**
     * Constructs the direction
    *
    * @param dx    the row
     * @param dy  the column
    */
    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Gets the horizontal offset of the direction.
     * @return the x-axis offset
     */
    public int getDx() {
        return dx;
    }

    /**
     * Gets the vertical offset of the direction.
     * @return the y-axis offset
     */
    public int getDy() {
        return dy;
    }
}
