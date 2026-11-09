// package Essential;

// import Buildings.*;
// import Tiles.*;

// import java.util.*;

// /**
//  * Demonstrates creating buildings on a game board and printing their costs.
//  */
// public class Livrable2 {
//     /**
//     * Sets up the game board, creates buildings, and prints their costs.
//     * 
//     * @param args Command-line arguments for board width and height.
//     * @throws NumberFormatException if the command-line arguments cannot be parsed into integers.
//     */
//     public static void main(String[] args) {
//         if (args.length < 2) {
//             System.out.println("It's necessary to have <width> <height>");
//             return;
//         }

//         try {
//             int width = Math.max(10, Integer.parseInt(args[0]));
//             int height = Math.max(10, Integer.parseInt(args[1]));

//             Board board = new Board(width, height, 0.5);
//             Player player = new Player("Player1");
            
       
//             Port port = new Port(null , player);
//             BasicFarm farm = new BasicFarm(null, player);
//             AdvancedFarm bigFarm = new AdvancedFarm(null, player);
//             Army army = new Army(null, player, 1);
//             Camp camp = new Camp(null, player, 1);
            
//             List<Building> buildings = Arrays.asList(port, farm, bigFarm, army, camp);
            
//             board.printBoard();
        
//             for (Building building : buildings) {
//                 System.out.print(building.toString() + ", cost -> ");
//                 for (Map.Entry<String, Integer> entry : building.getCost().entrySet()) {
//                     System.out.print(entry.getKey() + ":" + entry.getValue() + " ");
//                 }
//                 System.out.println();
//             }

//             List<Tile> allLandTiles = board.allLandTiles();
//             List<Tile> allLandTilesNextToSea = board.allLandTilesNextToSea();
            
//             System.out.println('\n');

//             port.setPosition(allLandTilesNextToSea.get(0));
//             allLandTiles.remove(allLandTilesNextToSea.get(0));
//             System.out.println("Port has been set on " + port.getTile().toString());

//             farm.setPosition(allLandTiles.get(0));
//             allLandTiles.remove(0);
//             System.out.println("Farm has been set on " + farm.getTile().toString());

//             bigFarm.setPosition(allLandTiles.get(0));
//             allLandTiles.remove(0);
//             System.out.println("BigFarm has been set on " + bigFarm.getTile().toString());

//             army.setPosition(allLandTiles.get(0));
//             allLandTiles.remove(0);
//             System.out.println("Army has been set on " + army.getTile().toString());

//             camp.setPosition(allLandTiles.get(0));
//             allLandTiles.remove(0);
//             System.out.println("Camp has been set on " + camp.getTile().toString());

//             farm.produce();
//             bigFarm.produce();
//             army.produce();
//             camp.produce();

//             System.out.println('\n');
//             System.out.println("Farm produce " + farm.getStock().size() + " " + farm.getStock().get(0).getName());
//             System.out.println("BigFarm produce " + bigFarm.getStock().size() + " " + bigFarm.getStock().get(0).getName());
//             System.out.println("Army produce " + army.getStock().size() + " " + army.getStock().get(0).getName());
//             System.out.println("Camp produce " + camp.getStock().size() + " " + camp.getStock().get(0).getName());


            
//         } catch (NumberFormatException e) {
//             System.out.println("Arguments must be integers.");
//         }
//     }
// }
