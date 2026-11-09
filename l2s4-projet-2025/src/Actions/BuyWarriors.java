package Actions;

import Essential.Player;
import Ressources.*;
import java.util.Map;
import utils.listchooser.ListChooser;

/**
 * buy a warriors 
 */

public class BuyWarriors extends Action {

    /* the ressource we need in order to buy warriors */
    private static final Ressource WHEAT = new Wheat();
    private static final Ressource ORE = new Ore();
    private static final Ressource SHEEP = new Sheep();

    /** the map which contains the cost in order to buy warriors*/
    public static final Map<String, Integer> COST_MAP = Map.of(ORE.getName(), 1, SHEEP.getName(), 2, WHEAT.getName(), 2);
    
    /**
     * Buy Warriors with a given player
     * 
     * @param player the player
     * @param listChooser the listChooser
     */

    public BuyWarriors(Player player, ListChooser<Object> listChooser) {
        super(player, listChooser);
    }
    
    /**
     * Returns True if the player has the ressources that he needs in order to buy warriors.
     * 
     * @return True if the player has the needed ressources to buy warriors, false otherwise
     */
    public boolean canExecute(){
    	return player.hasEnoughRessources(COST_MAP);
    }

    /**
     * execution in order to buy a Warriors
     * 
     * @throws Exception if not the ressources 
     */

    public void execute() throws Exception{
        if (!this.canExecute()) {
            System.out.println("Vous ne possédez pas assez de ressources");
            return;
        }

        System.out.println("Vous avez choisi d'acheter 5 guerriers à ajouter à votre stock ");
        this.process();
    }

	/**
     * remove the ressources we need of the player
     */
    public void process() {
        if (canExecute()) {
            this.player.addWarriors(5);
            this.player.removeResources(COST_MAP);
            System.out.println(player.getName() + player.warriorString() + " a ajouté 5 guerriers à son stock");
        } else {
            System.out.println("Ressources insuffisantes pour acheter des guerriers");
        }
    }


    /** 
     * returns string for the ressources needed 
     * 
     * @return string the ressources we need
     */
    public String toString() {
        return "Achat de 5 guerriers (1 Ore, 2 Sheep, 2 Wheat)";
    }
}
