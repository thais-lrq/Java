package Ressources;

/**
 * The `Wood` class represents a "Wood" resource in the game
 * It implements the `Ressources` interface and provides the `getName()` method to return the resource's name
 */
public class Wood implements Ressource {

    private final String name;

    /**
     * Constructor for the `Wood` class
     * Initializes the resource's name to "Wood".
     */
    public Wood(){
        this.name = "Wood";
    }

    /**
     * Returns the name of the resource, which is "Wood".
     *
     * @return The name of the resource (here "Wood")
     */
    public String getName() {
        return name;
    }
}
