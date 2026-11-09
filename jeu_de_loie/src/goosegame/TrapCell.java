package goosegame;

/**
 * The class TrapCell
 */
public class TrapCell extends Cell {
    private int destination;

    /**
     * The constructor of TrapCell
     * 
     * @param index       the index
     * @param destination the destination
     */
    public TrapCell(int index, int destination) {
        super(index);
        this.destination = destination;

    }

    /**
     * Apply a Rebound on a TrapCell
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
        if (!this.isOccupied()) {
            this.player = p;
        } else {
            Player displayed = this.player;
            Cell previous = p.getCell();
            displayed.changeCell(previous);
            this.player = p;

        }
    }

    /**
     * If the player is block with this type of cell
     * 
     * @return true
     */
    @Override
    public boolean isBlocked(Player p) {
        return this.player == p;
    }

    /**
     * When a player arrive at the cell
     * 
     * @param p     the player
     * @param board the board
     */
    @Override
    public Cell ArriveAtCell(Player p, Board board) {
        if (this.player != null) {
            Player other = this.player;
            Cell previous = p.getCell();
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
        return "trap";
    }

}
