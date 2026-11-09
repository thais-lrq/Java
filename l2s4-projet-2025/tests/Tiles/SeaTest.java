package Tiles;

import Ressources.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * tests for the Sea class.
 */
class SeaTest {

    private Sea seaTile;

    @BeforeEach
    void setUp() {
        seaTile = new Sea(2, 5);
    }

    @Test
    void testPasturePosition() {
        assertEquals(2, seaTile.getX(), "La position X de la tuile mer est incorrecte.");
        assertEquals(5, seaTile.getY(), "La position Y de la tuile mer est incorrecte.");
    }

    @Test
    void testProduce() {
        assertNull(seaTile.produce(), "La mer ne produit rien.");
    }

    @Test
    public void testToString() {
        Sea seaTile = new Sea(4, 6);
        assertEquals("Sea(4,6)", seaTile.toString(), "La représentation de la mer devrait être 'Sea(x,y)'.");
    }
}
