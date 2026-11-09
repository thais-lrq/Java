package Buildings;

import Essential.*;
import Tiles.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class AdvancedFarmTest {

    private AdvancedFarm advancedFarm;
    private Player player;
    private Tile tile;

    @BeforeEach
    void setUp() {
        player = new Player("TestPlayer");
        tile = new Field(2, 3);
        advancedFarm = new AdvancedFarm(tile, player);
    }

    @Test
    void testGetCost() {
        Map<String, Integer> cost = advancedFarm.getCost();
        assertEquals(1, cost.get("Wheat"), "Le coût en blé doit être de 1.");
        assertEquals(2, cost.get("Wood"), "Le coût en bois doit être de 2.");
        assertEquals(1, cost.get("Sheep"), "Le coût en mouton doit être de 1.");
    }

    @Test
    void testProduce() {
        int initialStockSize = advancedFarm.getStock().size();
        advancedFarm.produce();
        assertEquals(initialStockSize + 2, advancedFarm.getStock().size(), 
            "AdvancedFarm doit produire 2 ressources à chaque production.");
    }

    @Test
    void testToStringForDisplay() {
        assertEquals("F", advancedFarm.toStringForDisplay(), 
            "La représentation visuelle de l'AdvancedFarm doit être 'F'.");
    }

    @Test
    void testToString() {
        assertEquals("AdvancedFarm - Tile : " + tile.toString(), 
            advancedFarm.toString(), 
            "La représentation textuelle de l'AdvancedFarm doit inclure sa tuile.");
    }
}
