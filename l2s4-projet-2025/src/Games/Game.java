package Games;

import Essential.Board;
import Essential.Player;
import Actions.*;
import Buildings.Building;
import Buildings.ProducingBuilding;
import utils.goals.Goal;
import utils.listchooser.ListChooser;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Represents a generic game.
 * This class must be extended to create a specific game.
 */
public abstract class Game {
    
    /** The board of the game */
    protected Board board;

    /** The list of players */
    protected ArrayList<Player> players;

    /** The current player */
    protected Player currentPlayer;

    /** The list chooser used for player choices */
    protected ListChooser<Object> lc;

    /** Random generator for the game */
    protected Random random;

    /** The number of the current round */
    protected int roundNumber;

    /**
     * Creates a game with a board of given width, height, and ratio.
     * 
     * @param width the width of the board
     * @param height the height of the board
     * @param ratio the ratio for board generation
     * @param listChooser the list chooser to use for player choices
     */
    public Game(int width, int height, double ratio, ListChooser<Object> listChooser){
        this.board = new Board(width, height, ratio);
        this.players = new ArrayList<Player>();
        this.lc = listChooser;
        this.random = new Random();
        this.roundNumber = 1;
    }

    /**
     * Adds a player to the game.
     * 
     * @param player the player to add
     */
    public void addPlayer(Player player){
        this.players.add(player);
        player.setGame(this);
    }

    /**
     * Returns the game board.
     * 
     * @return the board
     */
    public Board getBoard() {
        return this.board;
    }

    /**
     * Checks if the game is finished.
     * 
     * @return true if a player achieved their goal, false otherwise
     */
    public boolean isGameFinished() {
        for (Player player: this.players) {
            if (player.getGoal().isAchieved()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Executes a round for a single player.
     * The player chooses and executes an action.
     * 
     * @param player the player
     */
    public void playerRound(Player player){
        System.out.println(player.getListActions());
        List<Action> listAction = player.getListActions().stream()
            .filter(action -> action.canExecute())
            .collect(Collectors.toList());
        Action choice = (Action)this.lc.choose("\t| Quelle action voulez vous faire ?", listAction);
        try {
            System.out.println("\t| Vous avez choisi : " + choice);
            if (choice != null){ 
                choice.execute();
            }
        } catch (Exception e) {
            System.out.println("Une erreur est survenue" + e.getMessage());
        }
    }

    /**
     * Plays a full round for all players.
     * Produces resources and allows each player to act.
     */
    public void gameRound(){
        this.board.printBoard();
        System.out.println("=============== Round " + this.roundNumber + " ===============");
        this.produceForAllBuildings();
        this.players.stream().forEach(player -> player.collectAllBuildings());
        for (Player player : this.players) {
            System.out.println("\n");
            System.out.println(">>> Au tour de : " + player.getName());
            System.out.print("\t|");
            System.out.println(player.toString());
            playerRound(player);
            System.out.println("\n");
        }
        this.roundNumber++;
    }

    /**
     * Shows the results of the winners.
     * 
     * @param winners the list of players who won
     */
    public void showWinnersResult(List<Player> winners) {
        System.out.println("---------- Winner(s) ----------");
        for (Player player : winners) {
            player.showPlayerResult(true);
        }
    }

    /**
     * Shows the results of the losers.
     * 
     * @param losers the list of players who lost
     */
    public void showLosersResult(List<Player> losers) {
        System.out.println("---------- Loser(s) -----------");
        for (Player player : losers) {
            player.showPlayerResult(false);
        }
    }

    /**
     * Shows the final results of the game.
     * Divides players into winners and losers.
     */
    public void showGameResult() {
        List<Player> winners = new ArrayList<Player>();
        List<Player> losers = new ArrayList<Player>();

        for (Player player : this.players) {
            if (player.getGoal().isAchieved()) {
                winners.add(player);
            } else {
                losers.add(player);
            }
        }

        System.out.println("--------------- Results ---------------");
        this.showWinnersResult(winners);
        System.out.println("-------------------------------");
        this.showLosersResult(losers);
        System.out.println("-------------------------------");
        System.out.println("---------------------------------------");
    }

    /**
     * Shows the goals of all players.
     */
    public void showGoals() {
        System.out.println("---------------- Goals ----------------");
        for (Player player : this.players) {
            player.showPlayerGoal();
        }
        System.out.println("---------------------------------------");
    }

    /**
     * Starts and plays the full game until it ends.
     */
    public void play(){
        this.showGoals();
        this.beginning();
        while (!this.isGameFinished()) {
            this.gameRound();
        }
        this.showGameResult();
    }

    /**
     * Produces resources for all producing buildings on the board.
     */
    public void produceForAllBuildings() {
        List<Building> allBuildings = this.board.getAllBuildings();
        for (Building building : allBuildings) {
            if (building instanceof ProducingBuilding) {
                ((ProducingBuilding)building).produce();
            }
        }
    }

    /**
     * Returns the list of all players.
     * 
     * @return the list of players
     */
    public List<Player> getAllPlayers() {
        return this.players;
    }

    /**
     * Gets the list of goals for a player.
     * This method must be implemented by subclasses.
     * 
     * @param player the player
     * @return the list of goals
     */
    public abstract List<Goal> getGoals(Player player);

    /**
     * Initializes all players in the game.
     * This method must be implemented by subclasses.
     */
    public abstract void initializePlayers();

    /**
     * Starts the beginning of the game.
     * This method must be implemented by subclasses.
     */
    public abstract void beginning();
}
