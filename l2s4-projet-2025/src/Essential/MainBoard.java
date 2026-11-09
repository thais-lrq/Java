package Essential;

import utils.Island;
import Ressources.*;
import java.util.*;

/**
 * MainBoard is the entry point of the game. It creates a game board based on the width and height provided via command-line arguments.
 * If the arguments are invalid, it shows an error message.
 */
public class MainBoard {

    /**
     * Sets up the game board and test the game's actions.
     * 
     * @param args Command-line arguments for board width and height.
    */
    public static void main(String[] args) {

        if (args.length < 2) {
            System.out.println("it's necessary to have <width> <height>");
            return;
        }

        try {
            int width = Integer.parseInt(args[0]);
            int height = Integer.parseInt(args[1]);

            Board board = new Board(width, height, 0.5);
            board.printBoard();
            for (Island is : board.getIslands()) {
                System.out.println(is.getIsland());
            }

            Map<String, Ressource> ressourcesMap= new HashMap<String, Ressource>();
            ressourcesMap.put("wheat",new Wheat());
            System.out.println(ressourcesMap.get("wheat").getName());

        } catch (NumberFormatException e) {
            System.out.println("Arguments must be integers.");
        }

        // Player player = new Player();

        // Tiles tile = board.getTiles()[0][0];

        
        // tile.produce();
        // System.out.println(player.getDataSheet());
    }
}
