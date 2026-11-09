package Tiles;

import Ressources.*;

/**
 * Represents a field tile in the game.
 * A field tile produces Wheat as its resource.
 */
public class Field extends Tile {

    /**
     * Default constructor for the Field class
     * 
     * @param x the x coordinate 
     * @param y the y coordinate
     */
    public Field(int x, int y) {
        super(x, y);
    }
    
    /**
     * Produce and return the tile's ressources. Here the ressources are Wheat.
     * 
     * @return the Wheat ressource.
     */
    public Ressource produce(){
        return new Wheat();
    }
    
    /**
     * Returns a string representation of the tile.
     * 
     * @return A string describing the tile.
     */
    
     public String toStringForDisplay(){
        if(this.building != null) {
            return "F" + this.building.toStringForDisplay();
        } else {
            return "F/";
        }
    }

    public String toString() {
        return "Field("+this.getX()+","+this.getY()+")";
    }

}
