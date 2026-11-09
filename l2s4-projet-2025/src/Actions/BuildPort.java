package Actions;

import Buildings.Port;
import Essential.Player;
import Ressources.*;
import Tiles.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import utils.Island;
import utils.listchooser.ListChooser;


/**
 * Build a port
 */
public class BuildPort extends CostAction {
    /** Initialize the resource Wood */
    private static final Ressource WOOD = new Wood();
    /** Initialize the resource Sheep */
    private static final Ressource SHEEP = new Sheep();
    /** Builds a map which contains the cost in order to build a port */
    public static final Map<String, Integer> COST_MAP = Map.of(SHEEP.getName(), 2, WOOD.getName(), 1);

    /**
     * Builds BuildPort with a given player
     * 
     * @param player the player
     * @param listChooser the listChooser
     */
    public BuildPort(Player player, ListChooser<Object> listChooser) {
        super(player, listChooser);
    }

    /**
     * returns true if the player has enough ressources
     * 
     * @return true if the player has enough ressources fale otherwise 
     */
    public boolean canExecute() {
        return player.hasEnoughRessources(COST_MAP);
    }

     /**
     * Checks if the building can be placed on the specified tile.
     *
     * @param tile the tile
     * @return true if the placement is valid according to the game rules, false otherwise
     */
    public boolean isCorrectPlacement(Tile tile) {
        Island tileIsland = this.player.getGame().getBoard().getTilesIsland(tile);
        if((tile instanceof Sea) && (tile.getBuilding() != null) || !(this.player.getGame().getBoard().isNextToSea(tile))) {
            return false;
        } else if (this.player.getOccupiedIslands().contains(tileIsland)) {
            return true;
        } else {
            return this.player.hasPort() && this.player.hasBuildingCountPerOccupiedIsland(2);
        }
    }

    /**
     * Returns a list of all tiles where a building can be correctly placed.
     * It checks all land tiles on the player's game board and adds those
     * where placement is correct according to the rules defined by `isCorrectPlacement()`.
     *
     * @return A list of tiles where the building can be placed correctly.
     */

    public List<Tile> allCorrectPlacement() {
        List<Tile> allTiles = new ArrayList<Tile>();
        for (Tile tile : this.player.getGame().getBoard().allLandTiles()) {
            if (isCorrectPlacement(tile)) {
                allTiles.add(tile);
            }
        }
        return allTiles;
    }

    /**
     * execution in order to build a port
     * 
     * @throws Exception if the placement is incorrect
     */
    public void execute() throws Exception{
        Tile tileChosen = (Tile)this.lc.choose("Sur quelle tuile du plateau voulez-vous construire votre port? \\n", this.allCorrectPlacement());

        try {;
            if (tileChosen != null){
                process(tileChosen);
            } else {
                return;
            }
        } catch (Exception e) {
            System.out.println("Placement incorrect, veuillez choisir une nouvelle tuile");
            this.execute();
        }
    }
    
    /**
     * remove the ressource of the player in order to build a port
     * 
     * @param tile the tile
     */
    public void process(Tile tile) {
        if (canExecute()) {
            player.removeResources(COST_MAP);
            Port port = new Port(tile, player);
            player.addBuilding(port);
            tile.setBuilding(port);
            System.out.println(player.getName() + " a construit un port sur " + tile.toString());
        } else {
            throw new IllegalStateException("Ressources insuffisantes pour construire un port.");
        }
    }

    /**
     * fonction toString which tells us the ressources we need 
     * 
     * @return string the ressources 
     */
    public String toString() {
        return "BuildPort (1 Wood, 2 Sheep required)";
    }

	/**
     * return the cost in order to build a port 
     */
	public int cost() {
		// TODO Auto-generated method stub
		return 0;
	}
}
