package Actions;

import Buildings.BasicFarm;
import Essential.Player;
import Ressources.*;
import Tiles.*;
import utils.Island;
import utils.listchooser.ListChooser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Builds a farm
 */
public class BuildFarm extends CostAction {

    /* the ressources we need in order to build a farm */
    private static final Ressource WOOD = new Wood();
    private static final Ressource ORE = new Ore();

    /* Inform us if the action is the beginning action or not */
    private boolean beginAction;

    /** Initialize The map which contains the cost in order to build a farm */
    public static final Map<String, Integer> COST_MAP = Map.of(ORE.getName(), 1, WOOD.getName(), 1);

    /**
     * Builds BuildFarm with a given player
     * 
     * @param player the player
     * @param begin true if the action is a beginning action, false otherwise
     * @param listChooser the listChooser
     */
    public BuildFarm(Player player, boolean begin, ListChooser<Object> listChooser) {
        super(player, listChooser);
        this.beginAction = begin;
    }

    /** 
     * return true if the player has enough ressources
     * 
     * @return true if the player has enough ressources, false otherwise 
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
        if((tile instanceof Sea) || (tile.getBuilding() != null)) {
            return false;
        } else if (this.player.getOccupiedIslands().contains(tileIsland)) {
            return true;
        } else if (this.beginAction) {
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
     * Remove the ressources in order to build a farm
     */
    public void execute() throws Exception{
        Tile tileChosen = (Tile)this.lc.choose("Sur quelle tuile du plateau voulez-vous construire votre ferme ? \n", this.allCorrectPlacement());

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
     * Do the process in order to build the farm, comes after the execution
     * 
     * @param tile the tile where we want to build a farm
     */
    public void process(Tile tile) {
        if (canExecute()) {
            player.removeResources(COST_MAP);

            BasicFarm farm = new BasicFarm(tile, player);
            player.addBuilding(farm);
            tile.setBuilding(farm);
            System.out.println(player.getName() + " a construit une ferme sur "+ tile.toString());
        } else {
            throw new IllegalStateException("Ressources insuffisantes pour construire une ferme.");
        }
    }

    /**
     * returns the ressources we need 
     * 
     * @return string the ressources we need
     */
    public String toString() {
        return "Construction d'une ferme: (1 bois, 1 minerais requis)";
    }

	/**
     * return the cost in order to build a farm
     */
	public int cost() {
		return COST_MAP.values().stream().mapToInt(Integer::intValue).sum();
	}
}
