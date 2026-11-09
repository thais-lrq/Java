package Tiles;

import Ressources.*;

/**
 * Represents a mountain tile in the game.
 * A mountain tile produces Ore as its resource.
 */
public class Mountain extends Tile {
    
    /**
     * Default constructor for the Mountain class
     * 
     * @param x the x coordinate
     * @param y the y coordinate 
     */
    public Mountain(int x, int y) {
        super(x, y);
    }

    /**
     * Produce and return the tile's ressources. Here the ressources are Ore.
     * 
     * @return the Ore ressource.
     */
    public Ressource produce() {
        return new Ore();
    }
    
    /**
     * Returns a string representation of the tile.
     * 
     * @return A string describing the tile.
     */
    
     public String toStringForDisplay(){
        if(this.building != null) {
            return "M" + this.building.toStringForDisplay();
        } else {
            return "M/";
        }
    }

    public String toString() {
        return "Mountain("+this.getX()+","+this.getY()+")";
    }
}
