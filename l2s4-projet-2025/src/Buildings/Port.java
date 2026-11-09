package Buildings;

import Essential.Player;
import Tiles.Tile;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a port building in the game.
 * A port is a basic building that has a position and an owner.
 */
public class Port extends Building{

  /**
   * Creates a Port at the specified position with an owner.
   *
   * @param position The position of the port on the map (Tiles).
   * @param owner The player who owns the port (Player).
   */
    public Port(Tile position, Player owner) {
        super(position, owner);
    }

    /**
     * Retrieves the resource cost required to construct a Port.
     *
     * @return A map containing the required resources:
     *         - 2 Sheep
     *         - 1 Wood
     */
    public Map<String, Integer> getCost() {
        Map<String, Integer> cost = new HashMap<>();
        cost.put("Sheep", 2);
        cost.put("Wood", 1);
        return cost;
    }

    /**
     * Upgrade the building.
     * Since a Port has no upgrade in this implementation, it returns itself.
     *
     * @return The current Port instance.
     */
    public Building upgrade() {
        return this;
    }

    /**
     * Adds a specified number to the dimension of the building.
     * This method does not modify anything as it's not implemented in the Port class.
     *
     * @param nb The number to add to the dimension (no effect in Port class).
     */
    public void addToDimension(int nb) {
    }

    /**
     * Returns a string representation of the Port.
     *
     * @return A string representing the Port ("P").
     */
    public String toStringForDisplay() {
        return "P";
    }

    /**
     * Returns a string representation of the Port object, including the position.
     *
     * @return A string representing the Port and its position.
     */
    public String toString() {
        return "Port - Tile : "+this.position.toString();
    }
}
