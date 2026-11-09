package Essential;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import Actions.*;
import Buildings.*;
import Games.*;
import Ressources.*;

import Essential.Player;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the Player class.
 */
class PlayerTest {

    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player("TestPlayer");
    }

    @Test
    void testInitializePlayer() {
        player.initializePlayer();
        assertEquals(10, player.getDataSheet().getOrDefault("Ore", 10));
        assertEquals(10, player.getDataSheet().getOrDefault("Sheep", 10));
        assertEquals(10, player.getDataSheet().getOrDefault("Wheat", 10));
        assertEquals(10, player.getDataSheet().getOrDefault("Wood", 10));
    }

    @Test
    void testInitializeWarriors() {
        player.initializeWarriors(5);
        assertEquals(5, player.getWarriorCount());
    }

    @Test
    void testHasEnoughWarriors() {
        player.initializeWarriors(3);
        assertTrue(player.hasEnoughWarriors(2));
        assertFalse(player.hasEnoughWarriors(4));
    }

    @Test
    void testUseWarriors() {
        player.initializeWarriors(5);
        player.useWarriors(3);
        assertEquals(2, player.getWarriorCount());
    }

    @Test
    void testUseWarriors_NotEnough() {
        player.initializeWarriors(2);
        assertThrows(IllegalArgumentException.class, () -> player.useWarriors(3));
    }

    @Test
    void testAddWarriors() {
        player.initializeWarriors(2);
        player.addWarriors(3);
        assertEquals(5, player.getWarriorCount());
    }

    @Test
    void testAddWarriors_Negative() {
        assertThrows(IllegalArgumentException.class, () -> player.addWarriors(-1));
    }

}
