
package Buildings;
import Essential.Player;
import Tiles.*;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class ProducingBuildingTest {
    private BasicFarm farm;
    private Player player;
    private Tile tile;

    @BeforeEach
    void setUp() {
        player = new Player("Player1");
        tile = new Forest(0, 0);
        farm = new BasicFarm(tile, player);
    }

    @Test
    void testStockManagement() {
        assertTrue(farm.getStock().isEmpty(), "Le stock doit être vide au départ");
        farm.produce();
        assertEquals(1, farm.getStock().size(), "La production doit remplir le stock");
    }
}
