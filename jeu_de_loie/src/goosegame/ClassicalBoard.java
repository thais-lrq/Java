package goosegame;

/**
 * ClassicalBoard represents the traditional goose game board with 63 cells.
 */
public class ClassicalBoard extends Board {

    /**
     * Constructor of classical Board
     */
    public ClassicalBoard() {
        super(64);
    }

    /**
     * Init the board
     */
    @Override
    protected void initBoard() {
        for (int i = 0; i < nbOfCells; i++) {
            cells[i] = new NormalCell(i);
        }

        cells[0] = new FirstCell(0);

        int[] gooseIndexes = { 9, 18, 27, 36, 45, 54 };
        for (int idx : gooseIndexes) {
            cells[idx] = new GooseCell(idx);
        }

        cells[31] = new TrapCell(31, 31);
        cells[52] = new TrapCell(52, 52);

        cells[19] = new WaitingCell(19, 2);

        cells[6] = new TeleportCell(6, 12);
        cells[42] = new TeleportCell(42, 30);
        cells[58] = new TeleportCell(58, 1);

        cells[63] = new LastCell(63);
    }
}
