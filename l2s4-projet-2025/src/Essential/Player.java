package Essential;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;

import Actions.*;
import Buildings.*;
import Games.*;
import Ressources.*;
import utils.Island;
import utils.goals.*;

/**
 * Represents a player in the game. 
 * The player has a data sheet that stores various resources (like warriors) and their quantities. 
 * The class provides methods to manage these resources.
 */
public class Player {

// ------------------------------------------------------------ Attributs ------------------------------------------------------------
    
    /** the game */
    private Game game;
    /** the data of the player */
    private HashMap<String, Integer> dataSheet;
    /** the inventory of the player */
    private HashMap<String, Integer> inventory;
    /** the buildings */
    private ArrayList<Building> buildings;
    /** the warrior key */
    private static final String WARRIORS_KEY = "Warriors"; 
    /** the name of the player */
    private String name;
    /** the action the plaer can choose */
    private ArrayList<Action> listActions;
    /** the player's goal */
    private Goal goal;
    
    /**
     * Creates a new player with an empty data sheet.
     * Initializes the player with the given name.
     *
     * @param name The name of the player.
     */
     public Player(String name) {
        this.setName(name);
        this.inventory = new HashMap<String, Integer>();
        this.dataSheet = new HashMap<String, Integer>();
        this.buildings = new ArrayList<Building>();
        this.listActions = new ArrayList<Action>();
        this.initializePlayer();
    }


     /**
     * Initialize a Player at the beginning of a game: he begin with 10 Ore, 10 Sheep, 10 Wheat and 10 Wood.
     */
    public void initializePlayer() {
        this.addResource("Ore", 10);
        this.addResource("Sheep", 10);
        this.addResource("Wheat", 10);
        this.addResource("Wood", 10);
        
    }
    
    
    /**
     * Retrieves the player's data sheet containing resources and their quantities.
     * This sheet stores all the resources the player owns and their respective amounts.
     * 
     * @return A HashMap where keys are resource names (e.g., "Warriors", "Wood") and values are their quantities.
     */
    public HashMap<String, Integer> getDataSheet() {
        return dataSheet;
    }

     /**
     * returns the Player's name.
     * @return name the name of the player.
     */
	public String getName() {
		return name;
	}

	/**
	 * Set a given name to a Player
	 * @param name the name of the player
	 */
	public void setName(String name) {
		this.name = name;
	}

    public String toString() {
        return this.getName() + ": " + printInventory();
    }

// ------------------------------------------------------------ Gestion des guerriers / voleur / arme secrète --------------------------------------

    /**
     * Initializes warriors stock
     * @param nb The number of warriors the player starts with.
     */
    public void initializeWarriors(int nb) {
        dataSheet.put(WARRIORS_KEY, nb);
    }

    /**
     * Initializes remaining secret weapon
     * @param nb The number of secret weapon in the whole game for the player.
     */
    public void initializeRemainingSecretWeapon(int nb) {
        dataSheet.put("RemainingSecretWeapon", nb);
    }

    /**
     * Initializes remaining thief
     * @param nb The number of thief in the whole game for the player.
     */
    public void initializeRemainingThief(int nb) {
        dataSheet.put("RemainingThief", nb);
    }
    
    /**
     * Checks if the player has at least `nb` warriors.
     * @param nb The number of warriors required.
     * @return true if the player has enough warriors, false otherwise.
     */
    public boolean hasEnoughWarriors(int nb) {
        return dataSheet.getOrDefault(WARRIORS_KEY, 0) >= nb;
    }
    
    /**
     * Uses `nb` warriors from the player's stock.
     * @param nb The number of warriors to use.
     * @throws IllegalArgumentException if the player does not have enough warriors.
     */
    public void useWarriors(int nb) throws IllegalArgumentException {
        if (!hasEnoughWarriors(nb)) {
            throw new IllegalArgumentException("Le joueur n'a pas assez de guerriers !");
        }
        dataSheet.put(WARRIORS_KEY, dataSheet.get(WARRIORS_KEY) - nb);
    }
    
    
    /**
     * Adds warriors to the player's stock.
     * @param nb The number of warriors to add.
     * @throws IllegalArgumentException if `nb` is negative.
     */
    public void addWarriors(int nb) throws IllegalArgumentException {
        if (nb < 0) {
            throw new IllegalArgumentException("Impossible d'ajouter un nombre négatif de guerriers.");
        }
        dataSheet.put(WARRIORS_KEY, dataSheet.getOrDefault(WARRIORS_KEY, 0) + nb);
    }
    
    
    /**
     * Returns the number of warriors the player currently has.
     *
     * @return the number of warriors
     */
    public int getWarriorCount() {
        return dataSheet.getOrDefault(WARRIORS_KEY, 0);
    }

    /**
     * Returns a string representation of the number of warriors.
     *
     * @return a string like "(X Warriors)", where X is the number of warriors
     */
    public String warriorString() {
        return "(" + this.getWarriorCount() + " Warriors)";
    }

    /**
     * Returns the number of secret weapons the player has left.
     *
     * @return the number of remaining secret weapons
     */
    public int getRemainingSecretWeapons() {
        return dataSheet.getOrDefault("RemainingSecretWeapon", 0);
    }

    
    /**
     * Adds a secret weapon to the player's stock.
     * 
     */
    public void addSecretWeapon() {
        if (this.getRemainingSecretWeapons() > 0) {
            dataSheet.put("SecretWeapon", dataSheet.getOrDefault("SecretWeapon", 0) + 1);
            dataSheet.put("RemainingSecretWeapon", dataSheet.getOrDefault("RemainingSecretWeapon", 0) - 1);
            System.out.println("Le stock d'armes secrètes restantes est de " + dataSheet.getOrDefault("RemainingSecretWeapon", 0));
        } else {
            System.out.println("Il n'y a plus d'arme secrète disponible");
        }
    }

    /**
     * Use a secret weapon to the player's stock.
     * 
     */

    public void useSecretWeapon() {
        if (dataSheet.getOrDefault("SecretWeapon", 0) > 0) {
            dataSheet.put("SecretWeapon", dataSheet.getOrDefault("SecretWeapon", 0) - 1);
        }
    }

    /**
     * Returns the number of Thief the player has left.
     *
     * @return the number of remaining Thief
     */

    public int getRemainingThief() {
        return dataSheet.getOrDefault("RemainingThief", 0);
    }
    
    /**
     * Adds a Thief to the player's stock.
     * 
     */
    public void addThief() {
        if (this.getRemainingThief() > 0) {
            dataSheet.put("Thief", dataSheet.getOrDefault("Thief", 0) + 1);
            dataSheet.put("RemainingThief", dataSheet.getOrDefault("RemainingThief", 0) - 1);
            System.out.println("Le stock de voleurs restants est de " + dataSheet.getOrDefault("RemainingThief", 0));
        } else {
            System.out.println("Il n'y a plus de voleur disponible");
        }
    }
    /**
     * Use a Thief to the player's stock.
     * 
     */
    public void useThief() {
        if (dataSheet.getOrDefault("Thief", 0) > 0) {
            dataSheet.put("Thief", dataSheet.getOrDefault("Thief", 0) - 1);
        }
    }

// ------------------------------------------------------------ Gestion des ressources ------------------------------------------------------------


    /**
     * Returns a list of Resource instances for each type of resource
     * at the player disposal (quantity > 0).
     *
     * @return list of resources
     */
    public List<Ressource> getResources() {
        List<Ressource> resourceList = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            if (entry.getValue() > 0) {
                String resourceName = entry.getKey();
                if (resourceName.equals("Wood")) {
                    resourceList.add(new Wood());
                } else if (resourceName.equals("Wheat")) {
                    resourceList.add(new Wheat());
                } else if (resourceName.equals("Sheep")) {
                    resourceList.add(new Sheep());
                } else if (resourceName.equals("Ore")) {
                    resourceList.add(new Ore());
                }
            }
        }
        return resourceList;
    }

    /**
     * Get the inventory in string
     * @return the inventory 
     */
    public String printInventory(){
        String str = new String();
        for (Map.Entry<String, Integer> entry : this.inventory.entrySet()) {
            str = str + entry.getKey() + "(" + entry.getValue()+ ") ";
        }
        return "( "+str+")";
    }

    /**
     * Adds a specified quantity of a resource to the player's data sheet.
     * If the resource already exists, its quantity is increased.
     * 
     * @param resource The name of the resource to add.
     * @param quantity The amount to add to the resource count.
     */
    public void addResource(String resource, int quantity) {
        inventory.put(resource, inventory.getOrDefault(resource, 0) + quantity);
    }


	
	/**
	 * Check if a Player has enough resource of a given resource and a given quantity
	 * @param resource the resource we want to check
	 * @param quantity the quantity of the resource we want to check
	 * @return true if the player has more resource than the given quantity, false otherwise
	 */
	public boolean hasResource(String resource, int quantity) {
        if (this.inventory.containsKey(resource)) {
		    return inventory.get(resource) >= quantity;
        } 
        return false;
        
	}

	/**
	 * Returns true if the player has enough resources
	 * @param ressourceMap the map which contains the resources
	 * @return true if the player jas enough resources, false otherwise
	 */
    public boolean hasEnoughRessources(Map<String, Integer> ressourceMap) {
        for (Map.Entry<String, Integer> entry : ressourceMap.entrySet()) {
            if (!this.hasResource(entry.getKey(), entry.getValue())) {
                return false;
            }
        }
        return true;
    }
	
	/**
	 * Remove a given quantity of a given resource 
	 * @param resource the resource we want to remove
	 * @param quantity the quantity we want to remove
	 * @throws IllegalArgumentException if the player doesn't have enough resources
	 */
	public void removeResource(String resource, int quantity)throws IllegalArgumentException {
		if (!hasResource(resource, quantity)) {
	        throw new IllegalArgumentException("Pas assez de " + resource + " !");
	    }
	    this.inventory.put(resource, this.inventory.get(resource) - quantity);
	}

	/**
	 * Removes the resources of a player
	 * @param ressourceMap the map which contains the resources
	 * @throws IllegalArgumentException if there is a problem
	 */
    public void removeResources(Map<String, Integer> ressourceMap)throws IllegalArgumentException {
		for (Map.Entry<String, Integer> entry : ressourceMap.entrySet()) {
            removeResource(entry.getKey(), entry.getValue());
        }
	}

    /**
     * Return the entire stock of a ressources and delete it to the inventory
     * @param ressource the ressource 
     * @return the entire stock of the ressource given
     */
    public int stealingRessources(Ressource ressource) {
        int nb = this.inventory.getOrDefault(ressource.getName(), 0);
        this.inventory.put(ressource.getName(), 0);
        return nb;
    }
    
// ------------------------------------------------------------ Gestion des bâtiments ------------------------------------------------------------


    /**
     * Adds a building to our list of buildings
     * @param building the building we want to add to our list
     */
    public void addBuilding(Building building){
        this.buildings.add(building);
    }

    /**
     * removes a building from our list of buildings
     * @param building the building we want to remove
     */
    public void removeBuilding(Building building) {
        this.buildings.remove(building);
    }

    /**
     * Collects all the buildings
     */
    public void collectAllBuildings() {
        for (Building building : this.buildings) {
            if (building instanceof ProducingBuilding){
                this.collectBuilding((ProducingBuilding)building);
            }
        }
    }

    /**
     * Collect a single building
     * @param building the building we want to collect
     */
    public void collectBuilding(ProducingBuilding building) {
        while (!building.getStock().isEmpty()) {
            Ressource ressource = building.getStock().poll();
            this.addResource(ressource.getName(), 1);
        }
    }

    /**
     * gets a building
     * @return building
     */
    public ArrayList<Building> getBuildings() {
        return this.buildings;
    }

//  ------------------------------------------------------------ Gestion du Jeu  ------------------------------------------------------------

    /**
     * gets the game
     * 
     * @return the game
     */
    public Game getGame(){
        return this.game;
    }

    /**
     * sets the game
     * 
     * @param game the game
     */
    public void setGame(Game game){
        this.game = game;
    }

// ------------------------------------------------------------ Gestion des Actions ------------------------------------------------------------


    /**
     * Sets a list of actions
     * @param listActions the list of actions 
     */
    public void setListActions(ArrayList<Action> listActions){
        this.listActions = listActions;
    }
    

    /**
     * Gets the list of ations
     * @return the list of actions 
     */
    public ArrayList<Action> getListActions() {
        return this.listActions;
    }

    /**
     * Adds a given action to the list of actions
     * @param action the action we want to add to our list
     */
    public void addToListActions(Action action) {
        this.listActions.add(action);
    }

// ------------------------------------------------------------ Gestion des Iles ------------------------------------------------------------


    /**
     * Returns the list of buildings located on the specified island.
     *
     * @param island the island 
     * @return a list of buildings present on the given island
     */
    public List<Building> islandBuildingList(Island island) {
        return getBuildings().stream().filter(building -> (this.game.getBoard().getTilesIsland(building.getTile())) == island).toList();
    }

    /**
     * Returns the number of buildings on the specified island that are owned by this player.
     *
     * @param island the island
     * @return the number of buildings owned by the player in the given island
     */
    public int numberOfBuildings(Island island) {
        return this.islandBuildingList(island).size();
    }

    /**
     * Checks if the player has a port
     * 
     * @return a boolean : true if the player has a port, false otherwise
     */
    public boolean hasPort() {
        for (Building building : this.buildings) {
            if (building instanceof Port) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the player has a building on the specified island
     * 
     * @param island the island we want to check
     * @return a boolean : true if the player is on the specified island, false otherwise
     */
	public boolean isOnIsland(Island island) {
        return !(islandBuildingList(island).isEmpty());
    }

    /**
     * Returns the list of the islands occupied by this player
     * 
     * @return player's island list
     */
    public List<Island> getOccupiedIslands() {
        List<Island> listIslands = new ArrayList<Island>();
        List<Island> allIslands = this.getGame().getBoard().getIslands();
        for (Island island : allIslands) {
            if (this.isOnIsland(island) && (!listIslands.contains(island))) {
                listIslands.add(island);
            }
        }
        return listIslands;
    }

    /**
     * Checks if all islands occupied by this player have at least the specified number of buildings.
     *
     * @param count the minimum number of buildings required per island
     * @return true if all occupied islands have at least the given number of buildings, false otherwise
     */
    public boolean hasBuildingCountPerOccupiedIsland(int count) {
        for (Island island : this.getOccupiedIslands()) {
            if (island.numberOfBuildings(this) < count) {
                return false;
            }
        }
        return true;
    }

// ------------------------------------------------------------ Gestion des Objectifs ------------------------------------------------------------

    /**
    * Initializes the player's goal by randomly selecting one from the list.
    *
    * @param goals  the list of possible goals
    * @param random the random generator used to shuffle the list
    */
    public void initializeGoal(List<Goal> goals, Random random) {
        Collections.shuffle(goals, random);
        this.goal = goals.get(0);
    }

    /**
     * Returns the current goal of the player.
     *
     * @return the player's goal
     */
    public Goal getGoal() {
        return this.goal;
    }

    /**
     * Displays the result of the game for this player, depending on whether they won.
     *
     * @param isWinner true if the player completed their goal, false otherwise
     */
    public void showPlayerResult(boolean isWinner) {
        if (isWinner) {
            System.out.println("[ " + this.getName() + " ]");
            System.out.println("\t | " + this.getName() + " completed their goal: ");
            System.out.println("\t | " + this.goal.toString());
        } else {
            System.out.println("[ " + this.getName() + " ]");
            System.out.println("\t | " + this.getName() + " did not complete their goal: ");
            System.out.println("\t | " + this.goal.toString());
        }
    }

    /**
     * Displays the goal the player must complete.
     */
    public void showPlayerGoal() {
        System.out.println("[ " + this.getName() + " ]");
        System.out.println("\t | " + this.getName() + " must complete the goal: ");
        System.out.println("\t | " + this.goal.toString());
    }


}

