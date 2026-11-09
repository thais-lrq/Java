package goosegame;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestTeleportCell {

    private Board board;
    private TeleportCell teleporter;
    private Player p;

    @BeforeEach
    public void before() {
        board = new ClassicalBoard();
        teleporter = new TeleportCell(6, 12);
        p = new Player("Bob");
    }

    @Test
    public void testTeleportation() {
        Cell result = teleporter.ArriveAtCell(p, board);

        assertEquals(12, result.getIndex());
    }

    @Test
    public void testTeleportReturnsCorrectCell() {
        Cell arrival = teleporter.ArriveAtCell(p, board);

        assertSame(board.getCell(12), arrival);
    }

    @Test
    public void testPlayerEndsUpAtTeleportedCell() {
        Cell finalCell = teleporter.ArriveAtCell(p, board);
        p.changeCell(finalCell);

        assertEquals(12, p.getCell().getIndex());
    }

}
