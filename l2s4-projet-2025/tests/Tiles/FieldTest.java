package Tiles;

import Ressources.*;
import Essential.*;
import Buildings.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * tests for the Field class.
 */
class FieldTest {

    private Field fieldTile;

    @BeforeEach
    void setUp() {
        fieldTile = new Field(5, 7);
    }

    @Test
    void testFieldPosition() {
        assertEquals(5, fieldTile.getX(), "La position X du champ est incorrecte.");
        assertEquals(7, fieldTile.getY(), "La position Y du champ est incorrecte.");
    }

    @Test
    void testProduce() {
        assertInstanceOf(Wheat.class, fieldTile.produce(), "Le champ devrait produire du blé.");
    }

    @Test
    public void testToString() {
        Field fieldTile = new Field(4, 6);
        assertEquals("Field(4,6)", fieldTile.toString(), "La représentation du champ devrait être 'Field(x,y)'.");
    }

    @Test
    public void testToStringForDisplayWithoutBuilding() {
        Field fieldTile = new Field(2, 3);
        assertEquals("F/", fieldTile.toStringForDisplay(), "La représentation d'affichage du champ sans bâtiment devrait être 'F/'.");
    }

    @Test
    public void testToStringForDisplayWithBuilding() {
        Field fieldtTile = new Field(5, 5);
        Player player = new Player("TestPlayer");
        BasicFarm farm = new BasicFarm(fieldtTile, player);
        fieldTile.setBuilding(farm);

        assertEquals("F" + farm.toStringForDisplay(), fieldTile.toStringForDisplay(),"La représentation d'affichage du champ avec un bâtiment est incorrecte.");
    }
}
