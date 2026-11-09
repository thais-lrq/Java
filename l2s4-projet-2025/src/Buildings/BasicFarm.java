package Buildings;

import Essential.Player;
import Tiles.Tile;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a basic farm that produces resources in the game.
 * A BasicFarm adds resources to its stock based on its position.
 */
public class BasicFarm extends ProducingBuilding{

    /**
     * Creates a BasicFarm with the given position and owner.
     * The farm starts with a default dimension of 1.
     *
     * @param position The tile where the farm is placed.
     * @param owner The player who owns the farm.
     */
    public BasicFarm(Tile position, Player owner) {
        super(position, owner, 1);
    }

    /**
     * Retrieves the resource cost required to construct a BasicFarm.
     *
     * @return A map containing the required resources:
     *         - 1 Ore
     *         - 1 Wood
     */
    public Map<String, Integer> getCost() {
        Map<String, Integer> cost = new HashMap<>();
        cost.put("Ore", 1);
        cost.put("Wood", 1);
        return cost;
    }

    /**
     * Produces resources for the basic farm.
     * The produced resources are added to the farm's stock.
     */
    public void produce() {
        this.stock.add(this.position.produce());
    }

    /**
     * Upgrades the building to an AdvancedFarm.
     *
     * @return A new AdvancedFarm instance with the same position and owner.
     */
    public Building upgrade() {
        return new AdvancedFarm(position, owner);
    }

    /**
     * Increases the dimension by the specified number (currently empty).
     *
     * @param nb the number to add to the dimension.
     */
    public void addToDimension(int nb) {
    }

    /**
     * Returns a string representation of the BasicFarm.
     *
     * @return A string representing the BasicFarm ("f").
     */
    public String toStringForDisplay() {
        return "f";
    }

    /**
     * Returns a string representation of the BasicFarm object, including the position.
     *
     * @return A string representing the BasicFarm and its position.
     */
    public String toString() {
        return "BasicFarm - Tile : "+this.position.toString();
    }
}
