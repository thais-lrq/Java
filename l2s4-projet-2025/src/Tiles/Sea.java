package Tiles;

import Ressources.*;

/**
 * Represents a sea tile in the game.
 * The sea tile does not produce any resources and serves as a non-resource-producing tile in the game.
 */
public class Sea extends Tile {

    /**
     * Default constructor for the Sea tile.
     * 
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public Sea(int x, int y) {
        super(x, y);
    }

    /**
     * Produces and returns the tile's resource. 
     * Since Sea tiles do not produce resources, this method returns null.
     * 
     * @return null, as Sea does not produce any resources.
     */
    public Ressource produce() {
        return null;
    }

    /**
     * Returns a string representation of the sea.
     * 
     * @return A string describing the sea ("~").
     */
    public String toStringForDisplay() {
        return "~~";
    }

    public String toString() {
        return "Sea("+this.getX()+","+this.getY()+")";
    }
}
