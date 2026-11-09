package goosegame;

/**
 * The class WinningCell
 * 
 */
public class WinningCell extends Cell {

    /**
     * Constructor of WinningCell
     * 
     * @param index the index
     */
    public WinningCell(int index) {
        super(index);
    }

    /**
     * A winning cell does not cause any rebound
     */
    @Override
    public Cell applyRebound(Board board, int diceValue) {
        return this;
    }

    /**
     * The player is never block on a winning cell
     */
    @Override
    public boolean isBlocked(Player p) {
        return false;
    }

    /**
     * No waiting logic on a winning cell.
     */
    @Override
    public void updateWaitStatus(Player p) {
        // nothing here
    }

    /**
     * When a player arrives on the winning cell,
     */
    @Override
    public Cell ArriveAtCell(Player p, Board board) {
        this.player = p;
        return this;
    }

    /**
     * It is the winning cell.
     */
    @Override
    public boolean isWinningCell() {
        return true;
    }
}
