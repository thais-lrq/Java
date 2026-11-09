package Buildings;

import Essential.Player;
import Tiles.Tile;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents an advanced farm that extends BasicFarm.
 * An AdvancedFarm produces twice as much as a BasicFarm.
 */
public class AdvancedFarm extends BasicFarm {

    /**
     * Creates an AdvancedFarm with the given position and owner.
     *
     * @param position The position of the farm (Tiles).
     * @param owner The owner of the farm (Player).
     */
    public AdvancedFarm(Tile position, Player owner) {
        super(position, owner);
        this.dimension = this.dimension + 1;
    }

    /**
     * Retrieves the cost required to build an AdvancedFarm.
     *
     * @return A map containing the resource costs:
     *         - 1 Wheat
     *         - 2 Wood
     *         - 1 Sheep
     */
    public Map<String, Integer> getCost() {
        Map<String, Integer> cost = new HashMap<>();
        cost.put("Wheat", 1);
        cost.put("Wood", 2);
        cost.put("Sheep", 1);
        return cost;
    }

    /**
     * Produces resources for the advanced farm.
     * Calls the produce method twice to generate more resources.
     */
    public void produce() {
        super.produce();
        super.produce();
    }

    /**
     * Returns a string representation of the AdvancedFarm.
     *
     * @return A string representing the AdvancedFarm ("F").
     */
    public String toStringForDisplay() {
        return "F";
    }

    /**
     * Returns a string representation of the AdvancedFarm object, including the position.
     *
     * @return A string representing the AdvancedFarm and its position.
     */
    public String toString() {
        return "AdvancedFarm - Tile : "+this.position.toString();
    }
}

