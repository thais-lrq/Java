import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import Ressources.*;

public class RessourceTest {

    @Test
    public void testOre() {
        Ressource ore = new Ore();
        assertEquals("Ore", ore.getName());
    }

    @Test
    public void testSheep() {
        Ressource sheep = new Sheep();
        assertEquals("Sheep", sheep.getName());
    }

    @Test
    public void testWheat() {
        Ressource wheat = new Wheat();
        assertEquals("Wheat", wheat.getName());
    }

    @Test
    public void testWood() {
        Ressource wood = new Wood();
        assertEquals("Wood", wood.getName());
    }

    @Test
    public void testAllRessources() {
        // Test que toutes les ressources de ALL_RESSOURCES sont bien présentes
        for (String resourceName : Ressource.ALL_RESSOURCES) {
            assertTrue(Ressource.ALL_RESSOURCES.contains(resourceName));
        }
    }
}
