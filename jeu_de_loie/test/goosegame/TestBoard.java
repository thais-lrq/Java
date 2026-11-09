package goosegame;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestBoard {

    private Board board;

    @BeforeEach
    public void before() {
        board = new ClassicalBoard();
    }

    @Test
    public void testBoardHasCorrectNumberOfCells() {
        assertEquals(64, board.getNbOfCells());
    }

    @Test
    public void testAllCellsAreInitialized() {
        for (int i = 0; i < board.getNbOfCells(); i++) {
            assertNotNull(board.getCell(i), "Cell " + i + " should not be null");
        }
    }

    @Test
    public void testStartCellIsFirstCell() {
        assertTrue(board.getCell(0) instanceof FirstCell);
    }

    @Test
    public void testLastCellIsWinningCell() {
        assertTrue(board.getCell(board.getNbOfCells() - 1).isWinningCell());
    }
}
