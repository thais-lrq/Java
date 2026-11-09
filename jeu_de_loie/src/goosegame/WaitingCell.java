package goosegame;

/**
 * The class WaitingCell
 */
public class WaitingCell extends Cell {
    private int waitTurns;
    private int timmer = 0;

    /**
     * The constructor of WaitingCell
     * 
     * @param index     the index
     * @param waitTurns the number waiting of turns
     */
    public WaitingCell(int index, int waitTurns) {
        super(index);
        this.waitTurns = waitTurns;
    }

    /**
     * Apply a Rebound on a WaitingCell
     * 
     * @param board     the board
     * @param diceValue the value of the dice
     * @return the new Cell with the rebound
     **/
    @Override
    public Cell applyRebound(Board board, int diceValue) {
        return this;
    }

    /**
     * update the wait status
     * 
     * @param p the plater
     */
    @Override
    public void updateWaitStatus(Player p) {
        if (timmer > 0) {
            timmer--;
        }
    }

    /**
     * If the player is block with this type of cell
     * 
     * @return true
     */
    @Override
    public boolean isBlocked(Player p) {
        return timmer > 0;
    }

    /**
     * Handles the arrival of a player to this cell.
     * 
     * @param p the Player arriving at this cell
     */
    @Override
    public Cell ArriveAtCell(Player p, Board board) {
        Cell previousCell = p.getCell();
        previousCell.removePlayer();

        if (isOccupied()) {
            Player other = this.player;
            Cell previous = p.getCell();

            System.out.println(" cell is busy, " + other + " is sent to cell " + previous.getIndex());

            other.changeCell(previous);
            previous.ArriveAtCell(other, board);
        }
        this.player = p;
        timmer = waitTurns;
        return this;
    }

    /**
     * Get the type
     * 
     * @return a String name
     */
    @Override
    public String getTypeName() {
        return "waiting";
    }

}