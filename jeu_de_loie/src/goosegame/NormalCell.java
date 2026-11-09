package goosegame;

/**
 * The class NormalCell
 */
public class NormalCell extends Cell {

    /**
     * The constructor of NormalCell
     * 
     * @param index the index
     */
    public NormalCell(int index) {
        super(index);

    }

    /**
     * Apply a Rebound on a NormalCell
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
    }

    /**
     * If the player is block with this type of cell
     * 
     * @return false
     */
    @Override
    public boolean isBlocked(Player p) {
        return false;
    }

    /**
     * 
     */
    @Override
    public Cell ArriveAtCell(Player p, Board board) {
        if (isOccupied()) {
            Player other = this.player;
            Cell previous = p.getCell();

            System.out.println(" cell is busy, " + other + " is sent to cell " + previous.getIndex());

            this.player = p;

            other.changeCell(previous);
            previous.ArriveAtCell(other, board);
        } else {
            this.player = p;
        }
        return this;
    }

    /**
     * Get the type
     * 
     * @return a String name
     */
    @Override
    public String getTypeName() {
        return "";
    }

}