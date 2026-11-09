package Tiles;

import Ressources.*;

/**
 * Represents a forest tile in the game.
 * A forest tile produces Wood as its resource.
 */
public class Forest extends Tile {

    /**
     * Default constructor for the Forest class
     * 
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public Forest(int x, int y) {
        super(x, y);
    }

    /**
     * Produce and return the tile's ressources. Here the ressources are Wood.
     * 
     * @return the Wood ressource.
     */
    public Ressource produce() {
        return new Wood();
    }
    
    /**
     * Returns a string representation of the tile.
     * 
     * @return A string describing the tile.
     */
    
     public String toStringForDisplay(){
        if(this.building != null) {
            return "T" + this.building.toStringForDisplay();
        } else {
            return "T/";
        }
    }

    public String toString() {
        return "Forest("+this.getX()+","+this.getY()+")";
    }
}
