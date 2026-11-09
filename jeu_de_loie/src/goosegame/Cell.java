package goosegame;

/**
 * The abstract class Cell
 */
public abstract class Cell {

    /**
     * the index
     */
    protected int index;
    /**
     * the player
     */
    protected Player player;

    /**
     * Constructor of Cell
     * 
     * @param index the index
     */
    public Cell(int index) {
        this.index = index;
        this.player = null;

    }

    /**
     * the abstract method applyRebound
     * 
     * @param board     the board
     * @param diceValue the value of dice
     * @return the rebound if they are
     */
    public abstract Cell applyRebound(Board board, int diceValue);

    /**
     * the abstract method isBlocked
     * 
     * @param p the player
     * @return true is there is a player
     */
    public abstract boolean isBlocked(Player p);

    /**
     * the abstract method updateWaitStatus
     * 
     * @param p the player
     */
    public abstract void updateWaitStatus(Player p);

    /**
     * get the Index
     * 
     * @return the index
     */
    public int getIndex() {
        return this.index;
    }

    /**
     * Indicate if there is a player is the cell
     * 
     * @return true if the cell is occupied by a player
     */
    public boolean isOccupied() {
        return this.player != null;

    }

    /**
     * Get the player
     * 
     * @return the player
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * It is a winning cell
     * 
     * @return true if it's a winning cell, otherwise false
     */
    public boolean isWinningCell() {
        return false;
    }

    /**
     * When the player arrives at the cell
     *
     * @param p     the player
     * @param board the board (needed for teleports, rebounds, etc.)
     * @return a Cell
     */
    public abstract Cell ArriveAtCell(Player p, Board board);

    /**
     * Get the type
     * 
     * @return a String name
     */
    public String getTypeName() {
        return "";
    }

    /**
     * Remove the player from this cell.
     */
    public void removePlayer() {
        this.player = null;
    }

}
