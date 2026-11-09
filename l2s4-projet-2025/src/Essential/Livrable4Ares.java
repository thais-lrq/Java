package Essential;

import Games.*;
import utils.listchooser.*;

/**
 * Demonstrates creating a game board and printing it.
 */
public class Livrable4Ares {
    /**
    * Sets up the game board and prints it.
    * 
    * @param args Command-line arguments for board width and height.
    */
    public static void main(String[] args) {

        if (args.length < 3) {
            System.out.println("it's necessary to have <width> <height> <playerNumber> (and optional player's name) <name1> <name2> ...");
            return;
        }

        try {

            int width = Math.max(10, Integer.parseInt(args[0]));
            int height = Math.max(10, Integer.parseInt(args[1]));

            int nbPlayers = Math.max(2, Integer.parseInt(args[2]));
            
            ListChooser<Object> listChooser = new InteractiveListChooser<Object>();

            Game game = new Ares(width, height, 0.5, listChooser);


            for (int index = 0; index < nbPlayers; index++) {
                if (args.length >= (4 + index)) {
                    Player player = new Player(args[(3 + index)]);
                    game.addPlayer(player);
                } else {
                    Player player = new Player("Player" + (index+1));
                    game.addPlayer(player);
                }
            }

            game.initializePlayers();

            game.play();

            
        } catch (NumberFormatException e) {
            System.out.println("Arguments must be integers.");
        }
    }
}