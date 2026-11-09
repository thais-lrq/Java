package Actions;

import Buildings.*;
import Essential.Player;
import Ressources.Ore;
import Ressources.Ressource;
import Ressources.Wood;
import java.util.List;
import java.util.Map;
import utils.listchooser.ListChooser;

/**
 * Replaces an Army with a Camp.
 * Costs 2 Wood and 3 Ore.
 */
public class ReplaceArmyWithCamp extends CostAction {

    /* the ressource we need in order to replace with a camp */
    private static final Ressource WOOD = new Wood();
    private static final Ressource ORE = new Ore();

    /** the map which contains the cost in order to replace an army with a camp */
    public static final Map<String, Integer> COST_MAP = Map.of(ORE.getName(), 3, WOOD.getName(), 2);


    /** 
     * Default constructor 
     * 
     * @param player the player
     * @param listChooser the listChooser
     */
    public ReplaceArmyWithCamp(Player player, ListChooser<Object> listChooser) {
        super(player, listChooser);
    }

    /** 
     * returns true if the player has enough ressources in order to replace with a camp
     * 
     * @return true if the player has enough ressources false otherwise
     */
    public boolean canExecute() {
        return player.hasEnoughRessources(COST_MAP);
    }

    /**
     * execution in order to replace an army with a camp
     */
    public void execute() {
        List<Building> listOfArmy = this.player.getBuildings().stream().filter(building -> (building instanceof Army) && !(building instanceof Camp)).toList();
        if (listOfArmy.isEmpty()) {
            System.out.println("Vous ne possédez aucune armée à remplacer.");
            return;
        }
        Building armyChosen = (Building)this.lc.choose("Quelle armée voulez-vous améliorer ?", listOfArmy);

        System.out.println("Vous avez choisi d'améliorer : " + armyChosen.toString());
        if(armyChosen != null) {
            process(armyChosen);
        }

    }

    /**
     * remove the ressources we need of the player
     * 
     * @param building the buildig
     */
    public void process(Building building) {
        if (canExecute()) {
            this.player.removeResources(COST_MAP);
            this.player.removeBuilding(building);
            Building newBuilding = building.upgrade();
            this.player.addBuilding(newBuilding);
            building.getTile().setBuilding(newBuilding);
            System.out.println(player.getName() + " a remplacé une armée par un camp !");
        } else {
            System.out.println("Ressources insuffisantes pour remplacer l'armée par un camp.");
        }
    }

    /**
     * the cost in order to replace the army with a camp
     */
    public int cost() {
        return COST_MAP.values().stream().mapToInt(Integer::intValue).sum();
    }

    /** 
     * returns string for the ressources needed 
     * 
     * @return string the ressources we need
     */
    public String toString() {
        return "Remplacement d'une armée avec un camp: (2 bois, 3 minerais)";
    }
}
