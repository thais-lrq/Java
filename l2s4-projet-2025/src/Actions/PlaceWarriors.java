package Actions;

import Buildings.*;
import Essential.Player;
import java.util.List;
import java.util.stream.Collectors;
import utils.listchooser.ListChooser;
/**
 * Place Warriors.
 */
public class PlaceWarriors extends CostAction{
	
    /**
     * Place Warriors.
     *
     * @param player the player who place Warrior.
     * @param listChooser  helps the player choose from a list.
     */

	public PlaceWarriors(Player player, ListChooser<Object> listChooser) {
        super(player, listChooser);
    }
    
    /**
     * Returns True if the player has the ressources that he needs in order to build an Army.
     * 
     * @return True if the player has the needed ressources to build the army, false otherwise
     */
    public boolean canExecute(){
    	return (!player.getBuildings().isEmpty()) && player.getWarriorCount() > 0;
    }

    public void execute() throws Exception{
        List<Building> listOfArmyAndCamp = this.player.getBuildings().stream().filter(building -> (building instanceof Army) || (building instanceof Camp)).collect(Collectors.toList());
        if (listOfArmyAndCamp.isEmpty()) {
            System.out.println("Vous ne possédez aucune armée ou camp à remplir.");
            return;
        }
        Building buildingChosen = (Building)this.lc.choose("Dans quelle armée voulez-vous placer votre guerrier ?", listOfArmyAndCamp);

        System.out.println("Vous avez choisi de placer 1 guerrier sur : " + buildingChosen.toString());
        if(buildingChosen != null) {
            this.process(buildingChosen, 1);
        }
    }

	/**
     * Processes the action of adding warriors to a building. 
     * If the building reaches a dimension of 5 or more after adding the warriors, 
     * it will be upgraded to a new building type.
     * 
     * @param building The building to which warriors are added.
     * @param nbWarriors The number of warriors to add to the building.
     */
    public void process(Building building, int nbWarriors) {
        if (canExecute() && (building.getDimension() + nbWarriors)<5) {
            this.player.useWarriors(nbWarriors);
            building.addToDimension(nbWarriors);
            System.out.println(player.getName() + player.warriorString() + " a ajouté " + nbWarriors + " à " + building.toString());
        } else if (canExecute() && (building.getDimension() + nbWarriors)>=5) {
            this.player.useWarriors(nbWarriors);
            building.addToDimension(nbWarriors);
            // Upgrade 
            this.player.removeBuilding(building);
            Building newBuilding = building.upgrade();
            this.player.addBuilding(newBuilding);
            building.getTile().setBuilding(newBuilding);
            //
            System.out.println(player.getName() + player.warriorString() + " a ajouté " + nbWarriors + " à " + building.toString() + " ce qui lui a permis d'être améliorer en " + newBuilding.toString());
        } else {
            System.out.println("Nombre de guerriers insuffisant pour pouvoir en ajouter");
        }
    }

    /**
     * the cost in order to replace the army with a camp
     */
    public int cost() {
        return 1;
    }

    /** 
     * returns string for the ressources needed 
     * 
     * @return string the ressources we need
     */
    public String toString() {
        return "Ajout de 1 guerrier à une armée ou un camp: (1 warrior)";
    }

}
