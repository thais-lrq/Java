package Buildings;

import Essential.Player;
import Tiles.Tile;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents an Army, a type of ProducingBuilding that consists of warriors.
 * An Army can be upgraded into a Camp.
 */
public class Army extends ProducingBuilding {
    
    /**
     * Constructs an Army with a specified number of warriors.
     *
     * @param position The tile where the army is located.
     * @param owner The player who owns the army.
     * @param dimension The number of warriors in the army.
     */
    public Army(Tile position, Player owner, int dimension) {
        super(position, owner, dimension);
    }

    /**
     * Retrieves the resource cost required to create an Army.
     *
     * @return A map containing the resource costs:
     *         - 1 Sheep
     *         - 1 Wood
     */
    public Map<String, Integer> getCost() {
        Map<String, Integer> cost = new HashMap<>();
        cost.put("Sheep", 1);
        cost.put("Wood", 1);
        return cost;
    }
    
    /**
     * Upgrades the Army to a Camp, keeping the same position, owner, and number of warriors.
     *
     * @return A new Camp instance with the same attributes as the current Army.
     */
    public Camp upgrade() {
        return new Camp(this.position, this.owner, this.dimension);
    }
    
    /**
     * Produces the resource associated with the tile the Army is on
     * and adds it to the stock.
     */
    public void produce() {
        this.stock.add(this.position.produce());
    }

    /**
     * Increases the dimension by the specified number.
     *
     * @param nb the number to add to the dimension.
     */
    public void addToDimension(int nb) {
        this.dimension = this.dimension + nb;
    }

    /**
     * Decreases the dimension by the specified number.
     *
     * @param nb the number to subtract from the dimension.
     */
    public void removeToDimension(int nb) {
        this.dimension = this.dimension - nb;
    }

    /**
     * Returns a string representation of the Army, displaying the number of warriors.
     *
     * @return A string in the format "a(n)" where n is the number of warriors.
     */
    public String toStringForDisplay() {
        return "a[" + this.dimension + "]";
    }

    /**
     * Returns a string representation of the Army object, including the position.
     *
     * @return A string representing the Army and its position.
     */
    public String toString() {
        return "Army - Tile : "+this.position.toString()+" contains "+this.dimension+" warriors";
    }
}
