package goosegame;

/**
 * Class Last Cell
 */
public class LastCell extends Cell {

    /**
     * Constructor of LastCell
     * 
     * @param index the index
     */
    public LastCell(int index) {
        super(index);
    }

    /**
     * It is a winning celle
     */
    @Override
    public boolean isWinningCell() {
        return true;
    }

    /**
     * Apply a Rebound
     * 
     * @param board     the board
     * @param diceValue the value of the dice
     * @return the rebound
     */
    @Override
    public Cell applyRebound(Board board, int diceValue) {
        return this;
    }

    /**
     * true if the player is blocked, otherwise false
     * 
     * @param p the player
     */
    @Override
    public boolean isBlocked(Player p) {
        return false;
    }

    /**
     * Update the wait Status
     * 
     * @param p the player
     */
    @Override
    public void updateWaitStatus(Player p) {
    }

    /**
     * When a player arrive at the cell
     * 
     * @param p     the player
     * @param board the board
     */
    @Override
    public Cell ArriveAtCell(Player p, Board board) {
        if (isOccupied()) {
            Player other = this.player;
            Cell previous = p.getCell();
            System.out.println(" cell is busy, " + other + " is sent to cell " + previous.getIndex());
            other.changeCell(previous);
            previous.ArriveAtCell(other, board);
        }
        this.player = p;
        return this;
    }

    /**
     * Get the type
     * 
     * @return a String name
     */
    @Override
    public String getTypeName() {
        return "winning";
    }

}
