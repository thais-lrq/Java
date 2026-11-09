package goosegame;

import java.util.ArrayList;
import java.util.List;

/**
 * FirstCell represents the starting position where multiple players can be.
 */
public class FirstCell extends Cell {
    private List<Player> players;

    /**
     * Constructor of First Cell
     * 
     * @param index the index
     */
    public FirstCell(int index) {
        super(index);
        this.players = new ArrayList<>();
    }

    /**
     * When a player arrive at a cell
     * 
     * @param p     the player
     * @param board the board
     */
    @Override
    public Cell ArriveAtCell(Player p, Board board) {
        if (!players.contains(p)) {
            players.add(p);
        }
        return this;
    }

    /**
     * If the cell is occupied
     */
    @Override
    public boolean isOccupied() {

        return false;
    }

    /**
     * Apply a Rebound
     * 
     * @param board     the board
     * @param diceValue The value of the dice
     */
    @Override
    public Cell applyRebound(Board board, int diceValue) {
        return this;
    }

    /**
     * if the player is blocked
     * 
     * @param p the player
     */
    @Override
    public boolean isBlocked(Player p) {
        return false;
    }

    /**
     * update the wait status
     * 
     * @param p the player
     */
    @Override
    public void updateWaitStatus(Player p) {
    }

    /**
     * List of the player for the first Cell
     * 
     * @return the list of player
     */
    public List<Player> getPlayers() {
        return players;
    }
}
