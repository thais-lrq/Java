package Buildings;
import Essential.Player;
import Tiles.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;




public class BuildingTest {

	private Player player;
    private Tile tile;
    private BasicFarm basicFarm;
    private AdvancedFarm advancedFarm;
    
    @BeforeEach
    void setUp() {
        player = new Player("Player2");
        tile = new Forest(0, 0);
        basicFarm = new BasicFarm(tile, player);
        advancedFarm = new AdvancedFarm(tile, player);
    }

    @Test
    void testBasicFarmInitialization() {
        assertEquals(1, basicFarm.dimension, "La ferme basique doit avoir une dimension d'1");
        assertTrue(basicFarm.getStock().isEmpty(), "Le stock est vide au départ");
    }

    @Test
    void testAdvancedFarmInitialization() {
        assertNotNull(advancedFarm, "L'exploitation avancée n'est pas null");
        assertEquals(2, advancedFarm.getDimension(), "L'exploitation avancée doit avoir une dimension de 2");
        assertTrue(advancedFarm.getStock().isEmpty(), "Le stock doit être vide au départ");
    }

    @Test
    void testBasicFarmProduction() {
        basicFarm.produce();
        assertEquals(1, basicFarm.getStock().size(), "La ferme basique doit produire une ressource");
    }

    @Test
    void testAdvancedFarmProduction() {
        advancedFarm.produce();
        assertEquals(2, advancedFarm.getStock().size(), "L'exploitation avancée doit produire deux ressources");
    }

    @Test
    void testBuildingOwnership() {
        assertEquals(player, basicFarm.getOwner(), "Le Owner/propriétaire de la ferme doit être correct");
        assertEquals(tile, basicFarm.getTile(), "La tuile associée à la ferme doit être correcte");
    }

    @Test
    void testToString() {
        assertEquals("BasicFarm - Tile : Forest(0,0)", basicFarm.toString(), "La représentation de la ferme basique doit être 'f'");
        assertEquals("AdvancedFarm - Tile : Forest(0,0)", advancedFarm.toString(), "La représentation de l'exploitation avancée doit être 'F'");
    }
}
