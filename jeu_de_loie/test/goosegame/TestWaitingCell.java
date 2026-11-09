package goosegame;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestWaitingCell {
    private Board board;
    private WaitingCell waiting;
    private Player p;

    @BeforeEach
    public void before() {
        board = new ClassicalBoard();
        waiting = new WaitingCell(10, 2);
        p = new Player("Alice");
        Cell start = new Cell(0) {
            @Override
            public Cell applyRebound(Board board, int diceValue) {
                return this;
            }

            @Override
            public void updateWaitStatus(Player p) {
            }

            @Override
            public boolean isBlocked(Player p) {
                return false;
            }

            @Override
            public Cell ArriveAtCell(Player p, Board board) {
                this.player = p;
                return this;
            }

            @Override
            public String getTypeName() {
                return "";
            }
        };

        p.changeCell(start);
    }

    @Test
    public void testPlayerIsBlockedInitially() {
        waiting.ArriveAtCell(p, board);
        assertTrue(waiting.isBlocked(p));
    }

    @Test
    public void testPlayerBecomesUnblockedAfterWaiting() {
        waiting.ArriveAtCell(p, board);
        waiting.updateWaitStatus(p);
        assertTrue(waiting.isBlocked(p));
        waiting.updateWaitStatus(p);
        assertFalse(waiting.isBlocked(p));
    }

    @Test
    public void testArriveAtCellReturnsWaitingCell() {
        Cell result = waiting.ArriveAtCell(p, board);
        assertEquals(waiting, result);
    }

}
