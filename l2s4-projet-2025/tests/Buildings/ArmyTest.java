package Buildings;

import Essential.Player;
import Tiles.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class ArmyTest {

    private Player player;
    private Tile tile;
    private Army army;
    private Camp camp;
    private Port port;

    @BeforeEach
    void setUp() {
        player = new Player("Player1");
        tile = new Forest(0, 0);
        player.initializeWarriors(30);
        army = new Army(tile, player, 3);
        camp = new Camp(tile, player, 5);
        port = new Port(tile, player);
    }

    @Test
    void testArmyInitialization() {
        assertEquals(3, army.getDimension(), "L'armée doit avoir 3 guerriers");
    }


    @Test
    void testArmyUpgradeToCamp() {
        Camp upgradedCamp = army.upgrade();
        assertEquals(3, upgradedCamp.getDimension(), "Le camp doit hériter des guerriers de l'armée");
    }

    @Test
    void testCampInitialization() {
        assertEquals(5, camp.getDimension(), "Le camp doit commencer avec 5 guerriers");
    }

    @Test
    void testCampAddingWarriors() {
        camp.addWarriors(3);
        assertEquals(8, camp.getDimension(), "Le camp doit maintenant avoir 8 guerriers");
    }

    @Test
    void testCampDoubleProduction() {
        int initialStockSize = camp.getStock().size();
        camp.produce();
        assertEquals(initialStockSize + 2, camp.getStock().size(), "Le camp doit produire 2 ressources");
    }

    @Test
    void testPortCreation() {
        assertNotNull(port, "Le port doit être correctement initialisé");
    }
}
