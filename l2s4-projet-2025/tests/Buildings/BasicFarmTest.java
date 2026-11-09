package Buildings;

import Essential.Player;
import Tiles.*;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class BasicFarmTest {
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
    void testInitialization() {
        assertEquals(player, farm.getOwner(), "L'owner/propriétaire est correct");
        assertEquals(tile, farm.getTile(), "La position est correcte");
        assertEquals(0, farm.getStock().size(), "Le stock est bien vide au départ");
    }

    @Test
    void testProduce() {
        farm.produce();
        assertEquals(1, farm.getStock().size(), "La ferme basique doit produire une ressource");
    }

    @Test
    void testToString() {
        assertEquals("BasicFarm - Tile : Forest(0,0)", farm.toString(), "La représentation doit être 'f'");
    }
}
