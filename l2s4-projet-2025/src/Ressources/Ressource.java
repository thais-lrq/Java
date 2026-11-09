package Ressources;

import java.util.List;

/**
 * The Ressources interface represents a generic resource in the game.
 * Each specific resource (such as Wheat, Wood, etc.) must implement this interface.
 * The interface provides a method to retrieve the name of the resource.
 */
public interface Ressource {

    /** all of the resources */
    List<String> ALL_RESSOURCES = List.of("Ore", "Sheep", "Wheat", "Wood");

    /**
     * Retrieves the name of the resource.
     * The name will correspond to the type of the resource 
     *
     * @return The name of the resource as a string.
     */
    public String getName();
    
}
