package Buildings;

import java.util.LinkedList;
import java.util.Queue;

import Essential.Player;
import Ressources.Ressource;
import Tiles.Tile;

/**
 * Represents a building that produces resources in the game.
 * This class is extended by buildings that can produce resources.
 */
public abstract class ProducingBuilding extends Building {

    /**
     * The dimension of the building. This could represent the size, capacity, or number of warriors.
     * The dimension is used for various building-related calculations, like determining the capacity to house units.
     */
    protected int dimension;
    
    /**
     * A list that holds the resources produced by the building.
     * This stock accumulates resources as the building produces them.
     */
    protected Queue<Ressource> stock;

    /**
     * Creates a ProducingBuilding at the specified position with an owner and dimension.
     * Initializes the stock as an empty list.
     *
     * @param position The position of the building on the map (Tiles).
     * @param owner The player who owns the building (Player).
     * @param dimension The dimension or size of the building (int).
     */
    public ProducingBuilding(Tile position, Player owner, int dimension) {
        super(position, owner);
        this.dimension = dimension;
        this.stock = new LinkedList<Ressource>();
    }

    /**
     * Gets the dimension of the building.
     *
     * @return The dimension of the building.
     */
    public int getDimension() {
        return this.dimension;
    }

    /**
     * Adds a specified number to the dimension of the building.
     * This method is abstract and must be implemented by subclasses.
     *
     * @param nb The number to add to the dimension.
     */
    public abstract void addToDimension(int nb);

    /**
     * Gets the stock of resources produced by the building.
     *
     * @return The list of resources stored in the building's stock.
     */
    public Queue<Ressource> getStock() {
        return this.stock;
    }

    /**
     * Produces resources. This method must be implemented by subclasses.
     * The specific logic for producing resources is defined in the subclasses.
     */
    public abstract void produce();
}
