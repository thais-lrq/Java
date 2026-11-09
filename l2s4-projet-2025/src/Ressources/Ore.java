package Ressources;

/**
 * Represents an ore resource in the game
 * Implements the Ressources interface
 */
public class Ore implements Ressource {

    private final String name;

    /**
     * Creates an Ore resource with the name "Ore"
     */
    public Ore() {
        this.name = "Ore";
    }

    /**
     * Returns the name of the ore resource
     * @return The name of the resource ("Ore")
     */
    public String getName() {
        return name;
    }
}
