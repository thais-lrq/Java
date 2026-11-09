package goosegame;

/**
 * The class TeleportCell
 */
public class TeleportCell extends Cell {

    private int destinationIndex;

    /**
     * Constructor of TeleportCell
     *
     * @param index            the index of this teleport cell
     * @param destinationIndex the index of the target cell to teleport to
     */
    public TeleportCell(int index, int destinationIndex) {
        super(index);
        this.destinationIndex = destinationIndex;
    }

    /**
     * Apply a rebound for a TeleportCell
     * The player is teleported to the destination cell
     *
     * @param board     the board
     * @param diceValue the value of dice (ignored)
     * @return the cell after teleportation
     */
    @Override
    public Cell applyRebound(Board board, int diceValue) {
        return board.getCell(destinationIndex);
    }

    /**
     * Update wait status
     *
     * @param p the player
     */
    @Override
    public void updateWaitStatus(Player p) {
    }

    /**
     * A player is never blocked by a teleport cell
     *
     * @param p the player
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
    public Cell ArriveAtCell(Player p, Board board) {
        Cell finalDestination = board.getCell(destinationIndex);

        if (finalDestination.isOccupied()) {
            Player other = finalDestination.getPlayer();
            Cell previous = p.getCell();

            System.out.print(" cell is busy, " + other + " is sent to cell " + previous.getIndex());

            other.changeCell(previous);
            previous.ArriveAtCell(other, board);
        }
        this.player = null;
        return finalDestination.ArriveAtCell(p, board);
    }

    /**
     * Get the type
     * 
     * @return a String name
     */
    @Override
    public String getTypeName() {
        return "teleport to " + this.destinationIndex;
    }
}
