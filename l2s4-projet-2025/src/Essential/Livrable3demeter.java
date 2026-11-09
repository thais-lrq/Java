// package Essential;

// import Actions.*;
// import Games.Demeter;
// import Ressources.*;

// import java.util.Random;
// import java.util.List;
// import Tiles.*;
// import Buildings.*;

// public class Livrable3demeter {
//     /**
//     * Sets up the game board and test the game's actions.
//     * 
//     * @param args Command-line arguments for board width and height.
//     */
//     public static void main(String[] args) {

//         if (args.length < 2) {
//             System.out.println("it's necessary to have <width> <height>");
//             return;
//         }

//         try {

//             // Initialisation 
//             Demeter game = new Demeter(Math.max(Integer.parseInt(args[0]), 10), Math.max(Integer.parseInt(args[1]), 10), 0.5);

//             Player player1 = new Player("Timo");
//             Player player2 = new Player("Léon");
            
//             game.addPlayer(player1);
//             game.addPlayer(player2);
//             game.initializePlayers();

//             Random random = new Random();

//             System.out.println("-_-_-_-_-_- DEMETER -_-_-_-_-_-");

//             game.getBoard().printBoard();

//             // Utils pour execution
//             List<Tile> allLandTiles = game.getBoard().allLandTiles();
//             List<Tile> allLandTilesNextToSea = game.getBoard().allLandTilesNextToSea();
//             List<Building> playerBuildings = player1.getBuildings();
        

//             // Construction d'une ferme 
//             System.out.println(">>> " + player1.toString()+" veut construire une ferme");
//             BuildFarm buildFarm = new BuildFarm(player1);
//             Tile tileFarm = allLandTiles.get(random.nextInt(allLandTiles.size()));
//             buildFarm.process(tileFarm);

//             System.out.println(player1.toString()+"\n");

//             // Fait évoluer une ferme en une exploitation
//             System.out.println(">>> " + player1.toString()+" veut faire évoluer sa ferme en exploitation " + tileFarm.getBuilding().toString());
//             ReplaceWithExploitation replaceWithExploitation = new ReplaceWithExploitation(player1);
//             replaceWithExploitation.process(tileFarm.getBuilding());

//             System.out.println(player1.toString()+"\n");

//             // Construit un port
//             System.out.println(">>> " + player1.toString()+" veut construire un port");
//             BuildPort buildPort = new BuildPort(player1);
//             Tile tilePort = allLandTilesNextToSea.get(random.nextInt(allLandTilesNextToSea.size()));
//             buildPort.process(tilePort);

//             System.out.println(player1.toString()+"\n");

//             // Echange 3 ressources contre une
//             System.out.println(">>> " + player1.toString()+" veut échanger 3 ressources contre une autre");
//             TradeRessources tradeRessources = new TradeRessources(player1);
//             tradeRessources.process(new Wheat(), new Ore());

//             System.out.println(player1.toString()+"\n");

//             // Echange 2 ressources contre une grâce à son port
//             System.out.println(">>> " + player1.toString()+" veut échanger 2 ressources contre une autre grâce à son port");
//             TradePort tradePort = new TradePort(player1);
//             tradePort.process(new Wood(), new Wood());

//             System.out.println(player1.toString()+"\n");


//             // Achète un voleur
//             System.out.println(">>> " + player1.toString()+" veut acheter un voleur");


//             System.out.println(player1.toString()+"\n");


//             // Fin 
//             System.out.println("==> Liste des bâtiments que possède " + player1.getName()+":");
//             for (Building building : playerBuildings) {
//                 System.out.println(building.toString());
//             }
//             System.out.println();


//             System.out.println("==> Liste des tuiles occupées par " + player1.getName()+":");
//             for (Building building : playerBuildings) {
//                 System.out.println(building.getTile().toString());
//             }
//             System.out.println();




            
//         } catch (NumberFormatException e) {
//             System.out.println("Arguments must be integers.");
//         }
//     }
// }
