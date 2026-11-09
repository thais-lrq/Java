package Actions;

import Essential.Player;
import Ressources.*;
import java.util.Map;
import utils.listchooser.ListChooser;

/**
 * buy a seacret weapon 
 */

public class BuySecretWeapon extends Action{
    
    /* the ressource we need in order to buy a secret weapon */
    private static final Ressource ORE = new Ore();
    private static final Ressource WOOD = new Wood();

    /** the map which contains the cost in order to buy a secret weapon*/
    public static final Map<String, Integer> COST_MAP = Map.of(ORE.getName(), 1, WOOD.getName(), 1);

    /**
     * Buy Secret Weapon with a given player
     * 
     * @param player the player
     * @param listChooser the listChooser
     */

    public BuySecretWeapon(Player player, ListChooser<Object> listChooser) {
        super(player, listChooser);
    }

    /**
     * Returns True if the player has the ressources that he needs in order to buy a secret weapon.
     * 
     * @return True if the player has the needed ressources to buy a secret weapon, false otherwise
     */
    public boolean canExecute(){
    	return player.hasEnoughRessources(COST_MAP) && player.getRemainingSecretWeapons() > 0;
    }

    /**
     * execution in order to buy a Secret Weapon 
     * 
     * @throws Exception if not the ressources 
     */

    public void execute() throws Exception{
        if (!this.canExecute()) {
            System.out.println("Vous ne possédez pas assez de ressources");
            return;
        }

        System.out.println("Vous avez choisi d'acheter 1 arme secrète à ajouter à votre stock ");
        this.process();
    }

    /**
     * remove the ressources we need of the player
     */
    public void process() {
        if (canExecute()) {
            this.player.addSecretWeapon();
            this.player.removeResources(COST_MAP);
            System.out.println(player.getName() + player.warriorString() + " a ajouté 1 arme sercrète à son stock");
        } else {
            System.out.println("Ressources insuffisantes pour acheter une arme secrète");
        }
    }

    /** 
     * returns string for the ressources needed 
     * 
     * @return string the ressources we need
     */
    public String toString() {
        return "Achat d'une arme secrète (1 Ore, 1 Wood)";
    }

}
