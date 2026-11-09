package Tiles;

import Buildings.*;
import Ressources.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * tests for the tile class
 */
class TileTest {
    
    private Tile forestTile;
    private Tile fieldTile;
    private Building farm;

    @BeforeEach
    void setUp() {
        forestTile = new Forest(2, 3);
        fieldTile = new Field(5, 7);
        farm = new BasicFarm(forestTile, null);  // Aucune appartenance à un joueur pour ce test
    }

    @Test
    void testTilePosition() {
        assertEquals(2, forestTile.getX(), "La position X de la tuile forêt est incorrecte.");
        assertEquals(3, forestTile.getY(), "La position Y de la tuile forêt est incorrecte.");

        assertEquals(5, fieldTile.getX(), "La position X de la tuile champ est incorrecte.");
        assertEquals(7, fieldTile.getY(), "La position Y de la tuile champ est incorrecte.");
    }

    @Test
    void testProduce() {
        assertTrue(forestTile.produce() instanceof Wood, "La forêt devrait produire du bois.");
        assertTrue(fieldTile.produce() instanceof Wheat, "Le champ devrait produire du blé.");
    }

    @Test
    void testToString() {
        assertEquals("Forest(2,3)", forestTile.toString(), "La représentation de la forêt devrait être 'Forest(x,y)'.");
        assertEquals("Field(5,7)", fieldTile.toString(), "La représentation du champ devrait être 'Field(x,y)'.");    }

    @Test
    void testBuildingPlacement() {
        assertNull(forestTile.getBuilding(), "La tuile forêt ne devrait pas avoir de bâtiment au départ.");

        forestTile.setBuilding(farm);
        assertNotNull(forestTile.getBuilding(), "La tuile forêt devrait maintenant contenir un bâtiment.");
        assertEquals(farm, forestTile.getBuilding(), "Le bâtiment sur la tuile forêt est incorrect.");
    }
}
