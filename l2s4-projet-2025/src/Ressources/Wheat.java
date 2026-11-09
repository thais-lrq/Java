package Ressources;

/**
 * The `Wheat` class represents a "Wheat" resource in the game
 * It implements the `Ressources` interface and provides the `getName()` method to return the resource's name
 */
public class Wheat implements Ressource {

    private final String name;

    /**
     * Constructor for the `Wheat` class
     * Initializes the resource's name to "Wheat"
     */
    public Wheat(){
        this.name = "Wheat";
    }

    /**
     * Returns the name of the resource, which is "Wheat"
     *
     * @return The name of the resource (here "Wheat")
     */
    public String getName() {
        return name;
    }
}
