package Tiles;

import Buildings.*;
import Ressources.*;


/**
 * Represents a generic tile in the game.
 * A tile has a type (field, forest, mountain, etc.) and produces a specific resource.
 * It can also provide a string representation of itself.
 */
public abstract class Tile {
    /**
     * The horizontal position of the tile on the map.
     * This is the X coordinate of the tile.
     */
    protected int x;
    /**
     * The vertical position of the tile on the map.
     * This is the Y coordinate of the tile.
     */
    protected int y;
    /**
     * The building associated with this tile.
     * This can be null if no building is on the tile.
     */
    protected Building building;

    /**
     * Creates a tile with a given position (x, y).
     * The tile starts without a building.
     * 
     * @param x The x-coordinate of the tile.
     * @param y The y-coordinate of the tile.
     */
    public Tile(int x, int y){
        this.x = x;
        this.y = y;
        this.building = null;
    }

    /**
     * Produces and returns the resource of the tile.
     * Each tile type will produce a specific resource.
     * 
     * @return The resource produced by the tile.
     */
    public abstract Ressource produce();
    
    /**
     * Returns a string representation of the tile.
     * This method should be implemented by subclasses to provide
     * a description of the tile's type.
     * 
     * @return A string describing the tile.
     */
    public abstract String toStringForDisplay();

    /**
     * Returns a string representation of the tile.
     * This method should provide more detailed information about the tile.
     * 
     * @return A detailed string representation of the tile.
     */
    public abstract String toString();

    /**
     * Gets the x-coordinate of the tile.
     * 
     * @return The x-coordinate of the tile.
     */
    public int getX() {
        return this.x;
    }

    /**
     * Gets the y-coordinate of the tile.
     * 
     * @return The y-coordinate of the tile.
     */
    public int getY() {
        return this.y;
    }

    /**
     * Gets the building currently on the tile, if any.
     * 
     * @return The building on the tile, or null if there is no building.
     */
    public Building getBuilding() {
        return this.building;
    }

    /**
     * Removes the building from the tile.
     * After calling this method, the tile will no longer have a building.
     */
    public void removeBuilding() {
        this.building = null;
    }

    /**
     * Sets a building on the tile.
     * 
     * @param building The building to place on the tile.
     */
    public void setBuilding(Building building) {
        this.building = building;
    }
}
