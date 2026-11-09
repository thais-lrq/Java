package Actions;

import Essential.Player;
import utils.listchooser.ListChooser;
import Buildings.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Represents a fight action between armies on the board
 * The player can choose one of their armies to attack an adjacent enemy army
 */
public class Fight extends Action{
    
     /**
     * Constructs a Fight action.
     *
     * @param player the player doing the action
     * @param listChooser to choose from a list of objects.
     */
    public Fight(Player player, ListChooser<Object> listChooser) {
        super(player, listChooser);
    }

    /**
     * Checks if the player can execute a fight action
     * A fight is possible if the player has an army adjacent to an enemy army
     *
     * @return true if a fight can be executed false otherwise
     */
    public boolean canExecute() {
        List<Building> listArmy = this.player.getBuildings().stream().filter(building -> building instanceof Army).toList();
        for (Building building : listArmy) {
            List<Building> neighborBuildings = this.player.getGame().getBoard().getNeighborhood(building.getTile()).stream().map(tile -> tile.getBuilding()).toList();
            List<Boolean> hasArmyNeighborOwnedByOtherPlayer = neighborBuildings.stream().map(neighborBuilding -> (neighborBuilding instanceof Army) && (neighborBuilding.getOwner() != this.player)).toList();
            if(hasArmyNeighborOwnedByOtherPlayer.stream().reduce(false, (acc, bool) -> acc || bool)) {
                return true;
            }
        }
        return false;
    }

     /**
     * Returns all the player's armies that can initiate a fight
     * along with the adjacent enemy armies they can attack
     *
     * @return A HashMap linking each player army to its possible enemy targets
     */
    public HashMap<Building, List<Building>> getFights() {
        HashMap<Building, List<Building>> allFights = new HashMap<Building, List<Building>>();

        List<Building> listArmy = this.player.getBuildings().stream().filter(building -> building instanceof Army).toList();
        for (Building building : listArmy) {
            List<Building> neighborBuildings = this.player.getGame().getBoard().getNeighborhood(building.getTile()).stream().map(tile -> tile.getBuilding()).toList();
            List<Building> armyNeighborOwnedByOtherPlayer = neighborBuildings.stream().filter(neighborBuilding -> (neighborBuilding instanceof Army) && (neighborBuilding.getOwner() != this.player)).toList();
            if (!(armyNeighborOwnedByOtherPlayer.isEmpty())) {
                allFights.put(building, armyNeighborOwnedByOtherPlayer);
            }
        }
        return allFights;
    }

    /**
     * Rolls a given number of dice and returns the results
     *
     * @param nbDices the number of dice to roll
     * @return A list of integers representing the dice results
     */
    public List<Integer> rollDices(int nbDices) {
        List<Integer> results = new ArrayList<Integer>();
        Random random = new Random();
        for (int index = 0; index < nbDices; index++) {
            int resultDice = random.nextInt(1,6);
            results.add(resultDice);
            System.out.println("\t| Dé n°" + (index+1) + " : " + resultDice);
        }
        return results;
    }

     /**
     * Executes the fight action, allowing the player to choose which army to use and which enemy to attack
     */
    public void execute() {
        HashMap<Building, List<Building>> allFights = this.getFights();
        System.out.println("Vous avez choisi d'engager une bataille ! \n");
            List<Building> listPlayerArmy = allFights.keySet().stream().toList();
            Building playerArmy = (Building)this.lc.choose("Quelle armée / camp voulez vous impliquer dans la bataille ? ", listPlayerArmy);
            List<Building> listEnemyArmy = allFights.get(playerArmy);
            Building enemyArmy = (Building)this.lc.choose("Quelle armée / camp ennemi.e voulez vous attaquer ? ", listEnemyArmy);
            
            process((Army)playerArmy, (Army)enemyArmy, 0);
        }

     /**
     * Handles the battle resolution: rolls dice, compares results and applies damage to the armies
     *
     * @param playerArmy the player's attacking army
     * @param enemyArmy  the targeted enemy army
     * @param bonusDice  any bonus dice for the player
     */
    public void process(Army playerArmy, Army enemyArmy, int bonusDice) {
        if ((playerArmy == null) || (enemyArmy == null)) {
            System.out.println("Vous venez d'annuler votre action");
            return;
        }

        int playerResult;
        System.out.println(this.player.getName() + " jette ses dés :");
        if (playerArmy.getDimension()<=3) {
            playerResult = this.rollDices(1+bonusDice).stream().reduce(0, (acc, nb) -> acc + nb);
            System.out.println("Résultat : " + playerResult);
        } else if (playerArmy.getDimension()>3 && playerArmy.getDimension()<=7) {
            playerResult = this.rollDices(2+bonusDice).stream().reduce(0, (acc, nb) -> acc + nb);
            System.out.println("Résultat : " + playerResult);
        } else {
            playerResult = this.rollDices(3+bonusDice).stream().reduce(0, (acc, nb) -> acc + nb);
            System.out.println("Résultat : " + playerResult);
        }

        int enemyResult;
        System.out.println(enemyArmy.getOwner().getName() + " jette ses dés :");
        if (enemyArmy.getDimension()<=3) {
            enemyResult = this.rollDices(1).stream().reduce(0, (acc, nb) -> acc + nb);
            System.out.println("Résultat : " + enemyResult);
        } else if (enemyArmy.getDimension()>3 && enemyArmy.getDimension()<=7) {
            enemyResult = this.rollDices(2).stream().reduce(0, (acc, nb) -> acc + nb);
            System.out.println("Résultat : " + enemyResult);
        } else {
            enemyResult = this.rollDices(3).stream().reduce(0, (acc, nb) -> acc + nb);
            System.out.println("Résultat : " + enemyResult);
        }

        if((playerResult < enemyResult) && (playerArmy.getDimension()>1)) {
            playerArmy.removeToDimension(1);
            System.out.println("La bataille a été remporté par " + enemyArmy.getOwner().getName());
            System.out.println("Un guerrier a été enlevé de " + playerArmy.toString() + " appartenant à " + playerArmy.getOwner().getName());
        } else if((playerResult < enemyResult) && (playerArmy.getDimension()<=1)) {
            playerArmy.getTile().removeBuilding();
            System.out.println("La bataille a été remporté par " + enemyArmy.getOwner().getName());
            System.out.println(playerArmy.toString() + " appartenant à " + playerArmy.getOwner().getName() + " a été détruite");
        } else if((playerResult > enemyResult) && (playerArmy.getDimension()>1)) {
            enemyArmy.removeToDimension(1);
            System.out.println("La bataille a été remporté par " + playerArmy.getOwner().getName());
            System.out.println("Un guerrier a été enlevé de " + enemyArmy.toString() + " appartenant à " + enemyArmy.getOwner().getName());
        } else if((playerResult > enemyResult) && (playerArmy.getDimension()<=1)) {
            enemyArmy.getTile().removeBuilding();
            System.out.println("La bataille a été remporté par " + playerArmy.getOwner().getName());
            System.out.println(enemyArmy.toString() + " appartenant à " + enemyArmy.getOwner().getName() + " a été détruite");
        } else {
            System.out.println("La bataille s'est fini sur un match null");
        }

    }

    /** 
     * returns string to represent the action 
     * 
     * @return the representation of the action
     */
    public String toString() {
        return "Attaquer un voisin (gratuit)";
    }
    

}
