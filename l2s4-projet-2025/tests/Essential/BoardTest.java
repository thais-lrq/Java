package Essential;
import org.junit.jupiter.api.*;

import Tiles.*;
import utils.Direction;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    private Board board;
    private final int width = 10;
    private final int height = 10;
    private final double landRatio = 0.6;

    @BeforeEach
    void setUp() {
        board = new Board(width, height, landRatio);
    }
    
    @Test
    void testBoardInitialization() {
        assertNotNull(board.getTiles(), "Le tableau de tuiles ne doit pas être null.");
        assertEquals(height, board.getTiles().length, "La hauteur du tableau doit être 10.");
        assertEquals(width, board.getTiles()[0].length, "La largeur du tableau doit être 10.");
    }
    

    
    @Test
    void testPlaceTileValidPosition() {
        int x = 2, y = 2;
        assertTrue(board.isInBounds(x, y), "La position doit être dans les limites du plateau.");
        board.clearBoard();
        if (board.getTiles()[y][x] == null) {
            board.placeTile(x, y);
            assertFalse(board.getTiles()[y][x] instanceof Sea, "La case ne doit pas être une tuile d'eau après placement.");
        }
    }
    
    @Test
    void testHasAdjacentLandTile() {
        board.clearBoard();
        board.getTiles()[2][2] = new Forest(height, height);
        assertTrue(board.hasAdjacentLandTile(2, 3), "Une tuile adjacente de terre est présente.");
        assertFalse(board.hasAdjacentLandTile(4, 4), "Il ne doit pas y avoir de tuile adjacente de terre.");
    }
    


    
    @Test
    void testClearBoard() {
        board.placeTile(2, 2);
        board.clearBoard();
        for (int y = 0; y < board.getTiles().length; y++) {
            for (int x = 0; x < board.getTiles()[0].length; x++) {
                assertNull(board.getTiles()[y][x], "Toutes les cases doivent être null après reset.");
            }
        }
    }


    }



