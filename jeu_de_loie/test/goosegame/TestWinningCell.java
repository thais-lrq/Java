package goosegame;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestWinningCell {

    private WinningCell win;
    private Player p;

    @BeforeEach
    public void before() {
        win = new WinningCell(63);
        p = new Player("Carol");
    }

    @Test
    public void testIsWinningCell() {
        assertTrue(win.isWinningCell());
    }

    @Test
    public void testArriveAtCellReturnsItself() {
        Cell result = win.ArriveAtCell(p, null);
        assertEquals(win, result);
    }

}
