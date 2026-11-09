package Tiles;

import Ressources.*;
import Essential.*;
import Buildings.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * tests for the Mountain class.
 */
class MountainTest {

    private Mountain mountainTile;

    @BeforeEach
    void setUp() {
        mountainTile = new Mountain(4, 6);
    }

    @Test
    void testMountainPosition() {
        assertEquals(4, mountainTile.getX(), "La position X de la montagne est incorrecte.");
        assertEquals(6, mountainTile.getY(), "La position Y de la montagne est incorrecte.");
    }

    @Test
    void testProduce() {
        assertInstanceOf(Ore.class, mountainTile.produce(), "La montagne devrait produire du minerai.");
    }

    @Test
    public void testToString() {
    Mountain mountainTile = new Mountain(4, 6);
    assertEquals("Mountain(4,6)", mountainTile.toString(), "La représentation de la montagne devrait être 'Mountain(x,y)'.");
}

    @Test
    public void testToStringForDisplayWithoutBuilding() {
        Mountain mountainTile = new Mountain(2, 3);
        assertEquals("M/", mountainTile.toStringForDisplay(), "La représentation d'affichage de la montagne sans bâtiment devrait être 'M/'.");
    }

    @Test
    public void testToStringForDisplayWithBuilding() {
        Mountain mountainTile = new Mountain(5, 5);
        Player player = new Player("TestPlayer");
        BasicFarm farm = new BasicFarm(mountainTile, player);
        mountainTile.setBuilding(farm);

        assertEquals("M" + farm.toStringForDisplay(), mountainTile.toStringForDisplay(),"La représentation d'affichage de la montagne avec un bâtiment est incorrecte.");
    }
}
