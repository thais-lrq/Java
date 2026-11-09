package goosegame;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestLastCell {

    private LastCell lastCell;
    private Player player1;
    private Player player2;
    private Board fakeBoard;

    @BeforeEach
    public void before() {
        lastCell = new LastCell(10);
        player1 = new Player("Alice");
        player2 = new Player("Bob");

        fakeBoard = new Board(11) {
            @Override
            protected void initBoard() {
                for (int i = 0; i < 11; i++) {
                    if (i == 10)
                        cells[i] = lastCell;
                    else
                        cells[i] = new NormalCell(i);
                }
            }
        };
    }

    @Test
    public void testIsWinningCell() {
        assertTrue(lastCell.isWinningCell(), "LastCell should be a winning cell");
    }

    @Test
    public void testApplyReboundReturnsItself() {
        Cell rebound = lastCell.applyRebound(fakeBoard, 5);
        assertEquals(lastCell, rebound, "Rebound should return the same LastCell");
    }

    @Test
    public void testIsBlockedAlwaysFalse() {
        assertFalse(lastCell.isBlocked(player1), "Player should never be blocked on LastCell");
    }

    @Test
    public void testArriveAtCellEmpty() {
        Cell result = lastCell.ArriveAtCell(player1, fakeBoard);
        assertEquals(lastCell, result, "ArriveAtCell should return itself");
        assertEquals(player1, lastCell.getPlayer(), "Player should occupy the LastCell");
    }

    @Test
    public void testUpdateWaitStatusDoesNothing() {
        lastCell.ArriveAtCell(player1, fakeBoard);
        lastCell.updateWaitStatus(player1);
        assertEquals(player1, lastCell.getPlayer(), "Player should remain the same after updateWaitStatus");
    }
}
