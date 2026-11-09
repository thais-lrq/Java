package goosegame;

/**
 * The class GooseCell
 */
public class GooseCell extends Cell {

    /**
     * The constructor of GooseCell
     * 
     * @param index the index
     */
    public GooseCell(int index) {
        super(index);

    }

    /**
     * Apply a Rebound on a GooseCell
     * 
     * @param board     the board
     * @param diceValue the value of the dice
     * @return the new Cell with the rebound
     **/
    @Override
    public Cell applyRebound(Board board, int diceValue) {
        int newIndex = this.index + diceValue;
        if (newIndex >= board.getNbOfCells()) {
            int overshoot = newIndex - (board.getNbOfCells() - 1);
            newIndex = board.getNbOfCells() - 1 - overshoot;
        }

        return board.getCell(newIndex);
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
     * When a player arrive at the cell
     * 
     * @param p     the player
     * @param board the board
     */
    @Override
    public Cell ArriveAtCell(Player p, Board board) {
        if (this.isOccupied()) {
            Player other = this.player;
            Cell previous = p.getCell();

            System.out.print(" cell is busy, " + other + " is sent to cell " + previous.getIndex());

            this.player = p;

            other.changeCell(previous);
            previous.ArriveAtCell(other, board);
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
        return "goose";
    }

}