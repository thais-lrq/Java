package utils;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

import Essential.Player;
import Buildings.*;
import Tiles.Tile;

/**
 * Represents an island containing tiles where buildings can be placed.
 */
public class Island {

    /** The list which contains all of the island's tiles */
    private List<Tile> island;

    /** 
     * Default constructor for the Island class.
     * Initializes the island with an empty list of tiles.
     */
    public Island() {
        this.island = new ArrayList<Tile>();
    }

    /**
     * Adds a given tile to the island.
     * The tile will only be added if it is not already in the island.
     *
     * @param tile The tile to be added to the island.
     */
    public void addTile(Tile tile) {
        if (!this.island.contains(tile)) {
            this.island.add(tile);
        }
    }

    /**
     * Gets the list of tiles on the island.
     *
     * @return The list of tiles on the island.
     */
    public List<Tile> getIsland() {
        return this.island;
    }

    /**
     * Checks if the island contains the specified tile.
     *
     * @param tile The tile to check for.
     * @return True if the tile is on the island, otherwise false.
     */
    public boolean containTile(Tile tile) {
        return this.island.contains(tile);
    }

    /**
     * Gets a list of buildings on the island.
     * It checks all tiles and adds buildings to the list if they exist on the tile.
     *
     * @return A list of buildings on the island.
     */
    public List<Building> getBuildingList() {
        List<Building> buildings = new ArrayList<Building>();
        for (Tile tile : this.island) {
            if (tile.getBuilding() != null) {
                buildings.add(tile.getBuilding());
            }
        }
        return buildings;
    }

    /**
     * Gets a map of players and the number of buildings they own on the island.
     *
     * @return A map with players as keys and the count of their buildings as values.
     */
    public Map<Player, Integer> numberOfBuildingsForAllPlayers() {
        Map<Player, Integer> map = new HashMap<Player, Integer>();
        for (Building building : this.getBuildingList()) {
            map.put(building.getOwner(), map.getOrDefault(building.getOwner(), 0) + 1);
        }
        return map;
    }

    /**
     * Gets the number of buildings on the island owned by a specific player.
     *
     * @param player The player whose building count is to be retrieved.
     * @return The number of buildings owned by the player on the island.
     */
    public int numberOfBuildings(Player player) {
        int result = 0;
        for (Tile tile : this.island) {
            if (tile.getBuilding() != null && tile.getBuilding().getOwner() == player) {
                result++;
            }
        }
        return result;
    }

    /**
     * Gets the dominant player on the island.
     * The dominant player is the one who owns the most buildings.
     * If there is a tie, no player is returned.
     *
     * @return The dominant player, or null if there is a tie.
     */
    public Player getDominantPlayer() {
        Player dominantPlayer = null;
        int max = 0;
        boolean equal = false;

        Map<Player, Integer> map = this.numberOfBuildingsForAllPlayers();

        for (Map.Entry<Player, Integer> entry : map.entrySet()) {
            int value = entry.getValue();
            if (value > max) {
                max = value;
                dominantPlayer = entry.getKey();
                equal = false;
            } else if (value == max) {
                equal = true;
            }
        }

        if (equal) {
            return null;
        } else {
            return dominantPlayer;
        }
    }
}
