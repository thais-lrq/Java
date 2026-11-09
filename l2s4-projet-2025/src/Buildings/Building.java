package Buildings;

import Essential.Player;
import Tiles.*;
import java.util.Map;

/**
 * Represents a generic building in the game
 * This is an abstract class that is extended by other specific types of buildings
 * A building has a position on the map and an owner
 */
public abstract class Building {

    /**
     *  The position of the tile
     */
    protected Tile position;

    /**
     * The owner of th Building
     */
    protected Player owner;

    /**
     * Creates a Building with the given position and owner
     *
     * @param position The position of the building on the map (Tiles)
     * @param owner The owner of the building (Player)
     */
    public Building(Tile position, Player owner) {
        this.position = position;
        this.owner = owner;
    }

    /**
     * Gets the owner of the building
     *
     * @return The player who owns the building
     */
    public Player getOwner() {
        return this.owner;
    }

    /**
     * Gets the cost of th building
     * 
     * @return A map containing the cost of the building.
     */
    public abstract Map<String, Integer> getCost();
    
    /**
     * Gets the tile where the building is located
     *
     * @return The tile where the building is positioned
     */
    public Tile getTile() {
        return this.position;
    }

    /**
     * Gets the dimension of the building
     * In this base class, the dimension is always 1
     *
     * @return The dimension of the building (1 by default)
     */
    public int getDimension() {
        return 1;
    }

    /**
     * Adds a specific number to the player's dimension value.
     * This method must be implemented by subclasses to define how the dimension is updated.
     *
     * @param nb the number to add to the dimension
     */
    
    public abstract void addToDimension(int nb);


    /**
     * Set a position for the Building
     * 
     * @param tile sets the position on this tile
     */
    public void setPosition(Tile tile){
        this.position = tile;
    }


    /**
     * Retrieves the cost required to construct the building.
     * This method must be implemented by subclasses
     *
     * @return A map containing the resource types and their required quantities.
     */
    public abstract Building upgrade();

    /**
     * Returns a string representation of the building
     * This method is abstract and must be implemented by subclasses
     *
     * @return A string representing the building
     */
    public abstract String toString();

    /** 
     * Returns a string representation of the building for the display
     * This method is abstract and must be implemented by subclasses 
     * 
     * @return a string representing the building for the display 
     */
    public abstract String toStringForDisplay();
}
