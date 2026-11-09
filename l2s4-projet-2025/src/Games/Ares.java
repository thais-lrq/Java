package Games;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Actions.*;
import Essential.*;
import utils.goals.ConquerTilesGoal;
import utils.goals.Goal;
import utils.goals.InvadeIslandGoal;
import utils.goals.WarriorCountGoal;
import utils.listchooser.ListChooser;

/**
 * Represents the game "Ares", a specific implementation of the Game class.
 * This game includes unique goals and action mechanics for each player.
 */
public class Ares extends Game {

    /**
     * Constructs the Ares game with a specified board size and configuration.
     *
     * @param width       the width of the game board
     * @param height      the height of the game board
     * @param ratio       the terrain generation ratio
     * @param listChooser the list chooser used for player interactions
     */
    public Ares(int width, int height, double ratio, ListChooser<Object> listChooser){
        super(width, height, ratio, listChooser);
    }

    /**
     * Generates and returns a list of goals for a player.
     * The goals include conquering tiles, collecting warriors, and invading islands.
     *
     * @param player the player to assign goals to
     * @return a list of goals for the player
     */
    public List<Goal> getGoals(Player player) {
        List<Goal> goals = new ArrayList<Goal>();
        goals.add(new ConquerTilesGoal(player, this.random.nextInt(15, 20)));
        goals.add(new WarriorCountGoal(player, this.random.nextInt(20, 25)));
        goals.add(new InvadeIslandGoal(player));

        return goals;
    }

    /**
     * Initializes all players in the Ares game.
     * Assigns actions, initial warriors, goals, and secret weapons to each player.
     */
    public void initializePlayers() {
        for (Player player : this.players) {
            this.initializePlayerActions(player); 
            player.initializeWarriors(30);
            player.initializeGoal(getGoals(player), this.random);
            player.initializeRemainingSecretWeapon(2);
        }
    }

    /**
     * Adds the list of available actions to a given player.
     * Each action defines a specific ability the player can perform during the game.
     *
     * @param player the player to initialize actions for
     */
    public void initializePlayerActions(Player player){
        player.addToListActions(new BuildArmy(player, false, this.lc));
        player.addToListActions(new BuildPort(player, this.lc));
        player.addToListActions(new PlaceWarriors(player, this.lc));
        player.addToListActions(new ReplaceArmyWithCamp(player, this.lc));
        player.addToListActions(new TradePort(player, this.lc));
        player.addToListActions(new TradeRessources(player, this.lc));
        player.addToListActions(new BuySecretWeapon(player, this.lc));
        player.addToListActions(new BuyWarriors(player, this.lc));
        player.addToListActions(new Fight(player, this.lc));
        player.addToListActions(new FightWithSecretWeapon(player, this.lc));
    }

    /**
     * Executes the special starting round for a player.
     * In this phase, the player must build an army.
     * If an error occurs, the method retries.
     *
     * @param player the player taking their starting round
     */
    public void playerStartRound(Player player) {
        String roundStr = (this.roundNumber == 1) ? "First" : "Second";

        try {
            System.out.println(roundStr + " round for: " + player.getName());
            Action action = new BuildArmy(player, true, this.lc);
            action.execute();
        } catch (Exception e) {
            // Retry the start round if an error occurred
            this.playerStartRound(player);
        }
    }

    /**
     * Begins the game with two starting rounds:
     * one in the original order and one in reverse.
     * Each player starts by building their army.
     */
    public void beginning() {
        List<Player> playersList = new ArrayList<Player>();
        playersList.addAll(this.players);
        
        this.board.printBoard();
        System.out.println("=============== Round " + this.roundNumber + " ===============" + "\n");
        for (Player currentPlayer : playersList) {
            playerStartRound(currentPlayer);
        }
        this.roundNumber++;

        // Reverse player order for the second start round
        Collections.reverse(playersList);

        this.board.printBoard();
        System.out.println("=============== Round " + this.roundNumber + " ===============" + "\n");
        for (Player currentPlayer : playersList) {
            playerStartRound(currentPlayer);
        }
        this.roundNumber++;
    }
}
