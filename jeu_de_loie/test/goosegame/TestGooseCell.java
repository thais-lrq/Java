package goosegame;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test pour GooseCell
 */
public class TestGooseCell {

    private GooseCell goose;
    private Player p;
    private Board fakeBoard;

    @BeforeEach
    public void before() {
        p = new Player("TestPlayer");

        fakeBoard = new Board(20) {
            @Override
            protected void initBoard() {
                for (int i = 0; i < 20; i++) {
                    if (i == 5)
                        cells[i] = new GooseCell(i);
                    else
                        cells[i] = new NormalCell(i);
                }
            }
        };

        goose = (GooseCell) fakeBoard.getCell(5);
    }

    @Test
    public void testGooseEffect() {
        Cell destination = goose.applyRebound(fakeBoard, 4);
        assertEquals(9, destination.getIndex());
    }

}