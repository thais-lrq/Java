package Ressources;

/**
 * The `Sheep` class represents a "Sheep" resource in the game
 * It implements the `Ressources` interface and provides the `getName()` method to return the resource's name
 */
public class Sheep implements Ressource {

    private final String name;

    /**
     * Constructor for the `Sheep` class
     * Initializes the resource's name to "Sheep"
     */
    public Sheep() {
        this.name = "Sheep";
    }

    /**
     * Returns the name of the resource, which is "Sheep"
     *
     * @return The name of the resource (here "Sheep")
     */
    public String getName() {
        return name;
    }
}
