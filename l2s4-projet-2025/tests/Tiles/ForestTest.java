package Tiles;

import Ressources.*;
import Essential.*;
import Buildings.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * tests for the Forest class.
 */
class ForestTest {

    private Forest forestTile;

    @BeforeEach
    void setUp() {
        forestTile = new Forest(2, 3);
    }

    @Test
    void testForestPosition() {
        assertEquals(2, forestTile.getX(), "La position X de la forêt est incorrecte.");
        assertEquals(3, forestTile.getY(), "La position Y de la forêt est incorrecte.");
    }

    @Test
    void testProduce() {
        Forest forestTile = new Forest(4, 6);
        assertInstanceOf(Wood.class, forestTile.produce(), "La forêt devrait produire du bois.");
    }

    @Test
    public void testToString() {
        Forest forestTile = new Forest(4, 6);
        assertEquals("Forest(4,6)", forestTile.toString(), "La représentation de la forêt devrait être 'Forest(x,y)'.");
    }

    @Test
    public void testToStringForDisplayWithoutBuilding() {
        Forest forestTile = new Forest(2, 3);
        assertEquals("T/", forestTile.toStringForDisplay(), "La représentation d'affichage de la forêt sans bâtiment devrait être 'T/'.");
    }

    @Test
    public void testToStringForDisplayWithBuilding() {
        Forest forestTile = new Forest(5, 5);
        Player player = new Player("TestPlayer");
        BasicFarm farm = new BasicFarm(forestTile, player);
        forestTile.setBuilding(farm);

        assertEquals("T" + farm.toStringForDisplay(), forestTile.toStringForDisplay(),"La représentation d'affichage de la forêt avec un bâtiment est incorrecte.");
    }
}
