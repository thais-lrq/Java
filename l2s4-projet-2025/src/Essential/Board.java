
package Essential;

import java.util.*;

import Tiles.*;
import utils.*;
import Buildings.*;


/**
* The Board class represents the game board (2D grid of tiles)
* There are specific rules, including the placement of land and sea tiles for the construction
*/

public class Board {
    private Random random;
    private final int width;
    private final int height;
    private Tile[][] tiles;
    private final double landRatio; // Pourcentage de tuile de terre (ça pourrait changer selon le type de jeu)
    private Tile lastPlacedTile;
    private ArrayList<Island> islands;

    
    /**
    * Build a Board with given width and height
    *
    * @param landRatio the percent of land tiles
    * @param width the width of the Board
    * @param height the height of the Board
    */
    public Board(int width, int height, double landRatio) {
        this.random = new Random();
        this.width = width;
        this.height = height;
        this.landRatio = landRatio;
        this.tiles = new Tile[height][width];
        this.lastPlacedTile = null;
        this.islands = new ArrayList<Island>();
        generateBoard();
        initialisedIslands();
    }

    /**
    * Generates a board, place Tiles randomly while adhering to adjacency rules
    * fills the remaining empty spaces with sea tiles
    */
    public void generateBoard(){
      int totalLandTiles = calculateTotalLandTiles();
      int placedLandTiles = 0;
      
      fillWithLandTiles(totalLandTiles, placedLandTiles);
      placeLastTile();
      fillWithSea();
    }
    
    /**
     * Fills the game board with land tiles until the total number of land tiles is reached.
     * The method places land tiles based on certain conditions and adjacency rules.
     * 
     * @param totalLandTiles The total number of land tiles to be placed on the board.
     * @param placedLandTiles The current number of land tiles that have been placed on the board.
    */
	public void fillWithLandTiles(int totalLandTiles, int placedLandTiles) {
		// Tant que toutes les tuiles de terre n'ont pas été placés
        while (placedLandTiles < (totalLandTiles-1)) {
            if (this.lastPlacedTile == null) {
                // S'il n'y a pas de tuiles, on en place une
                this.lastPlacedTile = placeTile(chooseRandomX(), chooseRandomY());
                placedLandTiles++;
            } else if (!hasAdjacentLandTile(this.lastPlacedTile.getX(), this.lastPlacedTile.getY())||(haveEmptyAdjacent(this.lastPlacedTile) && willHaveAdjacent(50))) {
                // Si la dernière tuile placée a des tuiles adjascentes vide et qu'on peut ajouter une nouvelle tuile de terre
                int[] coordinate = chooseRandomEmptyAdjacent(this.lastPlacedTile);
                this.lastPlacedTile = placeTile(coordinate[0], coordinate[1]);
                placedLandTiles++;
            } else {
                // Si l'ajout adjascent échoue, on réinitialise la dernière tuile placée
                this.lastPlacedTile = null;
            }
        }
	}

   /**
    * Checks if the tile at the given coordinates is empty.
    *
    * @param x The x-coordinate of the tile.
    * @param y The y-coordinate of the tile.
    * @return true if the tile is empty, false otherwise.
    */
    private boolean isEmpty(int x, int y) {
        boolean res = false;
        if (!(this.tiles[y][x] instanceof Tile)) {
            res = true;
        }
        return res;
    }


    /**
    * Chooses a random x-coordinate within the board's width.
    *
    * @return a random integer between 0 and width
    */
    public int chooseRandomX() {
        int x = this.random.nextInt(width);
        return x;
    }


    /**
    * Chooses a random y-coordinate within the board's height.
    *
    * @return a random integer between 0 and height 
    */
    public int chooseRandomY() {
        int y = this.random.nextInt(height);
        return y;
    }


    /**
    * Places a land tile at the specified coordinates if the position is within bounds and empty.
    *
    * @param x the x-coordinate of the tile
    * @param y the y-coordinate of the tile
    * @return an array containing the coordinates of the tile
    * @throws IllegalStateException if the provided coordinates are out of bounds
    */
    public Tile placeTile(int x, int y) {
        if (!isInBounds(x,y)){
            throw new IllegalStateException("Coordinates out of bounds");
          }
  
          if (isEmpty(x,y)) {
                this.tiles[y][x]= randomLandType(x, y);
                return this.tiles[y][x];
          }
          return null;
    }


    /**
    * Place a land tile in a random empty position that is adjacent to an existing land tile.
    *
    */
    public void placeLastTile() {
        boolean isPlaced = false;
        if ((this.lastPlacedTile != null) && (!hasAdjacentLandTile(this.lastPlacedTile.getX(),this.lastPlacedTile.getY()))){
            int[] coordinate = chooseRandomEmptyAdjacent(this.lastPlacedTile);
            this.lastPlacedTile = placeTile(coordinate[0], coordinate[1]);
            isPlaced = true;
        }
        while (!isPlaced) {
            int x = chooseRandomX();
            int y = chooseRandomY();
            if (isEmpty(x, y) && hasAdjacentLandTile(x, y)) {
                placeTile(x, y);
                isPlaced = true;
            }
        }
    }
    

    /**
    * Checks if there is an empty adjacent tile that has at least one land tile next to it.
    *
    * @param tile An array containing the x and y coordinates of the current tile.
    * @return true if there is an empty adjacent tile with a neighboring land tile, false otherwise.
    */
    private boolean haveEmptyAdjacent(Tile tile) {
        List<Direction> directions = new ArrayList<>(Arrays.asList(Direction.values()));

        for (Direction dir : directions) {
            int newX = tile.getX() + dir.getDx();
            int newY = tile.getY() + dir.getDy();

            if (isInBounds(newX, newY) && isEmpty(newX, newY)) {
                return true;
            }
        }
        return false;
    }

    /**
    * Checks if there is an adjacent land tile at the given coordinates.
    *
    * @param x The x-coordinate of the current tile.
    * @param y The y-coordinate of the current tile.
    * @return true if at least one adjacent tile is a land tile, false otherwise.
    */
    public boolean hasAdjacentLandTile(int x, int y) {
        List<Direction> directions = Arrays.asList(Direction.values());
        for (Direction dir : directions) {
        	int newX = x + dir.getDx();
            int newY = y + dir.getDy();
            if (isInBounds(newX, newY)) {
            	Tile adjacentTile = this.tiles[newY][newX];
            	if (adjacentTile instanceof Forest || adjacentTile instanceof Mountain || adjacentTile instanceof Field || adjacentTile instanceof Pasture) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
    * Determines whether an adjacent element will be present based on a given ratio.
    *
    * @param ratio  The probability (in percentage) that the method returns true.
    * @return true if the generated number is less than or equal to the ratio, false otherwise.
    */
    private boolean willHaveAdjacent(int ratio) {
        int i = this.random.nextInt(100);
        return (i <= ratio);
    }


    /**
    * Chooses a random empty adjacent tile to the given tile's coordinates.
    * 
    * @param tile an array containing the x and y coordinates of the reference tile
    * @return an array containing the coordinates of an empty adjacent tile, or null if there is no empty adjacent tile
    */
    private int[] chooseRandomEmptyAdjacent(Tile tile) {
        List<Direction> directions = new ArrayList<>(Arrays.asList(Direction.values()));
        Collections.shuffle(directions, this.random); // Mélanger les directions

        for (Direction dir : directions) {
            int newX = tile.getX() + dir.getDx();
            int newY = tile.getY() + dir.getDy();

            if (isInBounds(newX, newY) && isEmpty(newX, newY)) {
                return  new int[]{newX,newY};
            }
        }
        return null;
    }


    /**
    * Calculates the number total of land tiles that should be placed on the Board
    *
    * @return the total number of land tiles (&/3 of the total board size)
    */
    private int calculateTotalLandTiles() {
        return (int) (width * height * landRatio);
    }
    
    
    /**
    * Gets the 2D array of tiles representing the Board
    *
    * @return the 2D array of tiles
    */
    public Tile[][] getTiles() {
        return this.tiles;
    }

    /**
    * retrieves the tile at the specified coordinates.
    * 
    * @param x The x-coordinate of the tile.
    * @param y The y-coordinate of the tile.
    * @return the tile at the given coordinates.
    * @throws IllegalArgumentException If the provided coordinates are out of bounds.
    */
    public Tile getTile(int x, int y) {
        if (isInBounds(x, y)) {
            return this.tiles[y][x];
        } else {
            throw new IllegalArgumentException("Coordinates out of bounds");
        }
    }

    /**
    * Fills all the remaining empty tiles on the board with sea tiles
    */
    public void fillWithSea() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (isEmpty(x, y)) {
                    this.tiles[y][x] = new Sea(x, y);
                }
            }
        }
    }

    /**
    * Generates a random land type (FIELD, MOUNTAIN, FOREST or PASTURE)
    *
    * @param x the x coordinate
    * @param y the y coordinate
    * @return a randomly selected land TileType
    */
    public Tile randomLandType(int x, int y) {
      Tile[] landTiles = {new Forest(x, y), new Mountain(x, y), new Field(x, y), new Pasture(x, y)};
      return landTiles[this.random.nextInt(landTiles.length)];
    }


    /**
    * Checks if the given coordinates are within the bounds of the Board
    *
    * @param x the x coordinate
    * @param y the y coordinate
    *
    * @return true if the coordinates are within bounds false otherwise
    */
    public boolean isInBounds(int x, int y) {
        return (x >= 0) && (x < width) && (y >= 0) && (y < height);
    }

    /**
     * Builds a list with all the lands Tiles of the board
     * 
     * @return a list of all the land tile of the board
     */
    public List<Tile> allLandTiles() {
        List<Tile> allLandTiles = new ArrayList<Tile>();
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                if (!(this.tiles[i][j] instanceof Tiles.Sea)) {
                    allLandTiles.add(this.tiles[i][j]);
                }
            }
        }
        return allLandTiles;
    }

    /**
     * returns true if the given tile is next to the sea
     * 
     * @param tile the tile
     * @return true if the tile is next to the sea, false otherwise
     */
    public boolean isNextToSea(Tile tile) {
        List<Direction> directions = new ArrayList<>(Arrays.asList(Direction.values()));

        for (Direction dir : directions) {
            int newX = tile.getX() + dir.getDx();
            int newY = tile.getY() + dir.getDy();
    
            if (isInBounds(newX, newY) && (this.tiles[newY][newX] instanceof Tiles.Sea)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Fill a list of all the tiles which are next to a sea
     * 
     * @return the list of tile which are next to the sea
     */
    public List<Tile> allLandTilesNextToSea() {
        List<Tile> allLandTiles = new ArrayList<Tile>();
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                if (!(this.tiles[i][j] instanceof Tiles.Sea) && (isNextToSea(this.tiles[i][j]))) {
                    allLandTiles.add(this.tiles[i][j]);
                }
            }
        }
        return allLandTiles;
    }

    /**
     * Prints the board for the console, showing the type of each tile
     */
    public void printBoard() {
        System.out.println();
        for (int x = 0; x < this.width/2; x++) {
            System.out.print("=====");
        }
        System.out.print("BOARD");
        for (int x = 0; x < this.width/2; x++) {
            System.out.print("=====");
        }
        System.out.println();
        System.out.print("Y\\X|");
        for (int x = 0; x < this.width; x++) {
            if (x<10) {
                System.out.print(" 0" + x + " |");
            } else {
                System.out.print(" " + x + " |");
            }
        }
        System.out.println();
        for (int y = 0; y < this.height; y++) {
            System.out.print("---+");
            for (int x = 0; x < this.width; x++) {
                System.out.print("----+");
            }
            System.out.println();

            if (y<10) {
                System.out.print("0" + y + " |");
            } else {
                System.out.print(y + " |");
            }

            for (int x = 0; x < this.width; x++) {
                System.out.print(" " + this.tiles[y][x].toStringForDisplay() + " |");
            }
            System.out.println();
        }
        System.out.println();
    }



    /**
     * Clears the entire board by setting all tiles null
     */
    public void clearBoard() {
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
            	this.tiles[i][j] = null;
            }
        }
    }

    /**
     * Gets the Neighborhood of a given tile
     * 
     * @param tile the tile
     * @return the list of the neighborhood
     */
    public List<Tile> getNeighborhood(Tile tile) {
        List<Direction> directions = new ArrayList<>(Arrays.asList(Direction.values()));
        List<Tile> neighborhood = new ArrayList<Tile>();

        for (Direction dir : directions) {
            int newX = tile.getX() + dir.getDx();
            int newY = tile.getY() + dir.getDy();

            if (isInBounds(newX, newY) && !(this.tiles[newY][newX] instanceof Tiles.Sea)) {
                neighborhood.add(this.tiles[newY][newX]);
            }
        }

        return neighborhood;
    }

    /**
     * Creates an island 
     * 
     * @param tile the tile
     * @return an island
     */
    public Island createIsland(Tile tile) {
        Queue<Tile> tileQueue = new LinkedList<Tile>();
        List<Tile> visited = new ArrayList<Tile>();
        Island island = new Island();
        tileQueue.add(tile);

        while (!tileQueue.isEmpty()) {
            Tile tileTmp = tileQueue.poll();
            island.addTile(tileTmp);
            for (Tile neighbor : this.getNeighborhood(tileTmp)){
                if (!visited.contains(neighbor)){
                    tileQueue.add(neighbor);
                    visited.add(neighbor);
                }
            }
        }

        return island;
    }

    /**
     * Initialized the islands 
     */
    public void initialisedIslands() {
        List<Tile> allLandTile = this.allLandTiles();
        List<Tile> visited = new ArrayList<Tile>();
        for (Tile tile : allLandTile) {
            if (!visited.contains(tile)) {
                Island island = this.createIsland(tile);
                this.islands.add(island);
                visited.addAll(island.getIsland());
            }
        }
    }

    /**
     * Gets the Islands 
     * 
     * @return the list of islands
     */
    public List<Island> getIslands() {
        return this.islands;
    }

    /**
     * Returns the island that contains the specified tile.
     * 
     * @param tile the tile 
     * @return the island that contain the tile (or null if none is found)
     */
    public Island getTilesIsland(Tile tile){
        for (Island island : this.islands) {
            if (island.containTile(tile)) {
                return island;
            }
        }
        return null;
    }
    /**
     * Returns a list of all buildings placed on land tiles.
     * It checks all land tiles and adds the buildings that are placed on them 
     * to the list, excluding empty tiles.
     *
     * @return A list of buildings placed on land tiles.
     */
    public List<Building> getAllBuildings(){
        List<Tile> allLandTiles = this.allLandTiles();
        List<Building> allBuildings = new ArrayList<Building>();
        for (Tile tile : allLandTiles) {
            if (tile.getBuilding() != null) {
                allBuildings.add(tile.getBuilding());
            }
        }
        return allBuildings;
    }

}
