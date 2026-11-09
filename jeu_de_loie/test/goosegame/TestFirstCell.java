package goosegame;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestFirstCell {

    private Board board;
    private FirstCell first;
    private Player p1, p2;

    @BeforeEach
    public void before() {
        board = new ClassicalBoard();
        first = (FirstCell) board.getCell(0);
        p1 = new Player("Alice");
        p2 = new Player("Bob");
    }

    @Test
    public void testMultiplePlayersAllowed() {
        first.ArriveAtCell(p1, board);
        first.ArriveAtCell(p2, board);

        assertTrue(first.getPlayers().contains(p1));
        assertTrue(first.getPlayers().contains(p2));
    }

    @Test
    public void testArriveAtCellReturnsItself() {
        Cell result = first.ArriveAtCell(p1, board);
        assertEquals(first, result);
    }
}
