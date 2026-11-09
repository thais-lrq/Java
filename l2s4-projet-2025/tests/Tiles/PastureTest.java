package Tiles;

import Ressources.*;
import Essential.*;
import Buildings.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * tests for the Pasture class.
 */
class PastureTest {

    private Pasture pastureTile;

    @BeforeEach
    void setUp() {
        pastureTile = new Pasture(2, 5);
    }

    @Test
    void testPasturePosition() {
        assertEquals(2, pastureTile.getX(), "La position X de la prairie est incorrecte.");
        assertEquals(5, pastureTile.getY(), "La position Y de la prairie est incorrecte.");
    }

    @Test
    void testProduce() {
        assertInstanceOf(Sheep.class, pastureTile.produce(), "La prairie devrait produire du mouton.");
    }

    @Test
    public void testToString() {
        Pasture pastureTile = new Pasture(4, 6);
        assertEquals("Pasture(4,6)", pastureTile.toString(), "La représentation de la pature devrait être 'Pature(x,y)'.");
    }

    @Test
    public void testToStringForDisplayWithoutBuilding() {
        Pasture pastureTile = new Pasture(2, 3);
        assertEquals("P/", pastureTile.toStringForDisplay(), "La représentation d'affichage de la pature sans bâtiment devrait être 'P/'.");
    }

    @Test
    public void testToStringForDisplayWithBuilding() {
        Pasture pastureTile = new Pasture(5, 5);
        Player player = new Player("TestPlayer");
        BasicFarm farm = new BasicFarm(pastureTile, player);
        pastureTile.setBuilding(farm);

        assertEquals("P" + farm.toStringForDisplay(), pastureTile.toStringForDisplay(),"La représentation d'affichage de la pature avec un bâtiment est incorrecte.");
    }
}
