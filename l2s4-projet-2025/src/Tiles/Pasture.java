package Tiles;

import Ressources.*;

/**
 * Represents a pasture tile in the game.
 * A pasture tile produces Sheep as its resource.
 */
public class Pasture extends Tile{

    /**
     * Default constructor for the Pasture class
     * 
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public Pasture(int x, int y) {
        super(x, y);
    }

    /**
     * Produces and return the tile's ressources. Here the ressources are Sheep.
     * 
     * @return the Sheep ressource.
     */
    public Ressource produce(){

        return new Sheep();
    }
    
    /**
     * Returns a string representation of the tile.
     * 
     * @return A string describing the tile.
     */
    
    public String toStringForDisplay(){
        if(this.building != null) {
            return "P" + this.building.toStringForDisplay();
        } else {
            return "P/";
        }
    }

    public String toString() {
        return "Pasture("+this.getX()+","+this.getY()+")";
    }
}
