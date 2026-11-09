package Actions;




import Essential.*;
import Ressources.*;
import Tiles.*;
import utils.Island;
import utils.listchooser.ListChooser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Buildings.*;

/** 
 * Build an Army (it costs 1 wood, 1 sheep, and 1 wheat)
 * And we need to have at least one warrior in our stock to build an army
 */
public class BuildArmy extends CostAction {

    /** Initialize a resource Sheep */
    private static final Ressource SHEEP = new Sheep();
    /** Initialize a resource Wheat */
    private static final Ressource WHEAT = new Wheat();
    /** Initialize a resource Wood */
    private static final Ressource WOOD = new Wood();

    /* Inform us if the action is the beginning action or not */
    private boolean beginAction;

    /** Initialize the cost in order to build an army */
    public static final Map<String, Integer> COST_MAP = Map.of(SHEEP.getName(), 1, WHEAT.getName(), 1, WOOD.getName(), 1);
	
    /**
     * Builds BuildArmy with a given player
     * 
     * @param player the player
     * @param begin true if the action is a beginning action, false otherwise
     * @param listChooser the listChooser
     */
	public BuildArmy(Player player, boolean begin, ListChooser<Object> listChooser) {
        super(player, listChooser);
        this.beginAction = begin;
    }
    
    /**
     * Returns True if the player has the ressources that he needs in order to build an Army.
     * 
     * @return True if the player has the needed ressources to build the army, false otherwise
     */
    public boolean canExecute(){
    	return player.hasEnoughRessources(COST_MAP) && player.getWarriorCount() > 0;
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
    * Build an Army on a choosen x and y
    * 
    * @throws Exception if the placment is incorrect
    */
    public void execute() throws Exception{
        Tile tileChosen = (Tile)this.lc.choose("Sur quelle tuile du plateau voulez-vous construire votre armée ? \n", this.allCorrectPlacement());

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
     * process for the execution of building an army with a given tile
     * 
     * @param tile the tile which is going to stock the army
     * @throws Exception if there is a problem
     */
	public void process(Tile tile) throws Exception {
		if (canExecute()) {
            player.removeResources(COST_MAP);
            player.useWarriors(1);
            Army army = new Army(tile, player, 1);
            player.addBuilding(army);
            tile.setBuilding(army);
            System.out.println(player.getName() + " a construit une armée sur " + tile.toString());
        }
    }

    /**
     * returns a string of the cost in order to build an army
     * 
     * @return string a string for the cost
     */
	public String toString() {
		return "Construction d'une armée : (1 bois, 1 mouton, 1 blé requis + 1 guerrier)";
	}

    /**
     * retrusn the cost in order to build an army
     * 
     * @return int the cost
     */
	public int cost() {
		return COST_MAP.values().stream().mapToInt(Integer::intValue).sum();
	}

}