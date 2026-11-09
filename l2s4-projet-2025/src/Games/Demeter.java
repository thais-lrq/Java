package Games;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Actions.*;
import Essential.*;
import utils.goals.*;
import utils.listchooser.ListChooser;

/** 
 * This class represents the game Demeter.  
 * It is a specific type of Game.
 */
public class Demeter extends Game {

    /**
     * Creates a new Demeter game with given width, height, ratio, and list chooser.
     * 
     * @param width the width of the board
     * @param height the height of the board
     * @param ratio the ratio for board generation
     * @param listChooser the list chooser used in the game
     */
    public Demeter(int width, int height, double ratio, ListChooser<Object> listChooser){
        super(width, height, ratio, listChooser);
    }

    /**
     * Returns a list of goals for the given player.
     * 
     * @param player the player
     * @return a list of goals for the player
     */
    public List<Goal> getGoals(Player player) {
        List<Goal> goals = new ArrayList<Goal>();
        goals.add(new ScoreGoal(player, this.random.nextInt(15, 20)));
        return goals;
    }

    /** 
     * Initializes all players in the game.
     * Each player receives actions, goals, and thieves.
     */
    public void initializePlayers(){
        for (Player player : this.players) {
            this.initializePlayerActions(player);
            player.initializeGoal(getGoals(player), this.random);
            player.initializeRemainingThief(2);
        }
    }

    /** 
     * Initializes the list of actions for a specific player.
     * 
     * @param player the player to initialize actions for
     */
    public void initializePlayerActions(Player player){
        player.addToListActions(new BuildFarm(player, false, this.lc));
        player.addToListActions(new BuildPort(player, this.lc));
        player.addToListActions(new ReplaceWithExploitation(player, this.lc));
        player.addToListActions(new TradePort(player, this.lc));
        player.addToListActions(new TradeRessources(player, this.lc));
        player.addToListActions(new BuyThief(player, this.lc));
        player.addToListActions(new Steal(player, this.lc));
    }

    /**
     * Starts the round for a specific player.
     * The player performs a predefined action.
     * 
     * @param player the player who starts the round
     */
    public void playerStartRound(Player player) {
        String roundStr = (this.roundNumber == 1) ? "Premier" : "Second";

        try {
            System.out.println(roundStr + " tours de : " + player.getName());
            Action action = new BuildFarm(player, true, this.lc);
            action.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            this.playerStartRound(player);
        }
    }

    /**
     * Starts the beginning of the game.
     * Each player plays twice in a round (once in order and once in reverse).
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

        Collections.reverse(playersList);

        this.board.printBoard();
        System.out.println("=============== Round " + this.roundNumber + " ===============" + "\n");
        for (Player currentPlayer : playersList) {
            playerStartRound(currentPlayer);
        }
        this.roundNumber++;
    }
}
