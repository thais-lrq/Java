package Actions;

import java.util.HashMap;
import java.util.List;

import Buildings.Army;
import Buildings.Building;
import Essential.Player;
import utils.listchooser.ListChooser;


/**
 * This class lets a player start a battle using a secret weapon.
 * The secret weapon gives the player 1 extra dice during the fight.
 */
public class FightWithSecretWeapon extends Fight {
    
     /**
     * Creates a new fight action with a secret weapon.
     *
     * @param player the player who wants to fight.
     * @param listChooser  helps the player choose from a list.
     */
    public FightWithSecretWeapon(Player player, ListChooser<Object> listChooser) {
        super(player, listChooser);
    }

    /**
     * Checks if the player can use this action.
     *
     * @return true if the player can use this action, false otherwise.
     */
    public boolean canExecute() {
        return (super.canExecute() && (this.player.getDataSheet().getOrDefault("SecretWeapon", 0) > 0));
    }

     /**
     * Runs the action.
     * The player chooses their army and an enemy to attack.
     * The fight is done with 1 bonus dice thanks to the secret weapon.
     */
    public void execute() {
        HashMap<Building, List<Building>> allFights = this.getFights();
        System.out.println("Vous avez choisi d'engager une bataille ! \n");
            List<Building> listPlayerArmy = allFights.keySet().stream().toList();
            Building playerArmy = (Building)this.lc.choose("Quelle armée / camp voulez vous impliquer dans la bataille ? ", listPlayerArmy);
            List<Building> listEnemyArmy = allFights.get(playerArmy);
            Building enemyArmy = (Building)this.lc.choose("Quelle armée / camp ennemi.e voulez vous attaquer ? ", listEnemyArmy);
            
            process((Army)playerArmy, (Army)enemyArmy, 1);
        }

   /**
     * Starts the fight between the two armies.
     * Uses the bonus dice from the secret weapon.
     *
     * @param playerArmy the player's army.
     * @param enemyArmy  the enemy's army.
     * @param bonusDice  the number of extra dice (1 if using secret weapon).
     */
    public void process(Army playerArmy, Army enemyArmy, int bonusDice) {
        super.process(playerArmy, enemyArmy, bonusDice);
    }

    /** 
     * returns string to represent the action 
     * 
     * @return the representation of the action
     */
    public String toString() {
        return "Attaquer un voisin avec une arme secrète (1 arme secrète)";
    }
}
