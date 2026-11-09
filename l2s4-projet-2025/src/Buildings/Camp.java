package Buildings;

import Essential.Player;
import Tiles.Tile;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a camp building that belongs to an army.
 * A camp produces resources and can increase the number of warriors.
 * This class extends the Army class and provides additional functionality
 * such as producing resources and adding warriors.
 */
public class Camp extends Army{

  /**
   * Creates a Camp at the specified position with an owner and dimension.
   * 
   * @param position The position of the camp on the map (Tiles).
   * @param owner The player who owns the camp (Player).
   * @param dimension The dimension or size of the camp (int).
   */
    public Camp(Tile position, Player owner, int dimension) {
        super(position, owner, dimension);
    }

    /**
     * Produces resources for the camp by calling the produce method twice.
     */
    public void produce() {
        super.produce();
        super.produce();
    }

    /**
     * Retrieves the resource cost required to construct a Camp.
     *
     * @return A map containing the required resources:
     *         - 3 Ore
     *         - 2 Wood
     */
    public Map<String, Integer> getCost() {
        Map<String, Integer> cost = new HashMap<>();
        cost.put("Ore", 3);
        cost.put("Wood", 2);
        return cost;
    }

    /**
     * Increases the number of warriors in the camp.
     *
     * @param amount The number of warriors to add to the camp.
     */
    public void addWarriors(int amount) {
        this.dimension += amount;
    }

    /**
     * Increases the dimension by the specified number.
     *
     * @param nb The number to add to the dimension.
     */
    public void addToDimesion(int nb) {
        super.addToDimension(nb);
    }

    /**
     * Returns a string representation of the Camp, including the number of warriors.
     *
     * @return A string representation of the Camp, in the format "A(numberOfWarriors)".
     */
    public String toStringForDisplay() {
        return "A(" + this.dimension + ")";
    }

    /**
     * Returns a string representation of the Camp object, including the position and number of warriors.
     *
     * @return A string representing the Camp and its position.
     */
    public String toString() {
        return "Camp - Tile : "+this.position.toString()+" contains "+this.dimension+" warriors";
    }
}
