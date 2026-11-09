// package Essential;

// import Actions.*;
// import Games.Ares;
// import Ressources.*;

// import java.util.Random;
// import java.util.List;
// import Tiles.*;
// import Buildings.*;

// public class Livrable3ares {
//     /**
//     * Sets up the game board and test the game's actions.
//     * 
//     * @param args Command-line arguments for board width and height.
//     * @throws Exception 
//     */
//     public static void main(String[] args) throws Exception {

//         if (args.length < 2) {
//             System.out.println("it's necessary to have <width> <height>");
//             return;
//         }

//         try {

//             // Initialisation 
//             Ares game = new Ares(Math.max(Integer.parseInt(args[0]), 10), Math.max(Integer.parseInt(args[1]), 10), 0.5);

//             Player player1 = new Player("Timo");
//             Player player2 = new Player("Léon");
            
//             game.addPlayer(player1);
//             game.addPlayer(player2);
//             game.initializePlayers();

//             Random random = new Random();

//             System.out.println("-_-_-_-_-_- ARES -_-_-_-_-_-");

//             game.getBoard().printBoard();

//             // Utils pour execution
//             List<Tile> allLandTiles = game.getBoard().allLandTiles();
//             List<Tile> allLandTilesNextToSea = game.getBoard().allLandTilesNextToSea();
//             List<Building> playerBuildings = player1.getBuildings();
        

//             // Construction d'une armée 
//             System.out.println(">>> " + player1.toString() + player1.warriorString() +" veut construire une armée");
//             BuildArmy buildArmy = new BuildArmy(player1);
//             Tile tileArmy = allLandTiles.get(random.nextInt(allLandTiles.size()));
//             buildArmy.process(tileArmy);

//             System.out.println(player1.toString() + player1.warriorString() + "\n");

//             // Ajoute des guerriers à son armée jusqu'à atteindre 5
//             PlaceWarriors placeWarriors = new PlaceWarriors(player1);
//             while (tileArmy.getBuilding().getDimension()<5) {
//                 System.out.println(">>> " + player1.toString() + player1.warriorString() +" veut ajouter des guerriers à son armée pour arriver à 5");
//                 placeWarriors.process(tileArmy.getBuilding(), 1);
//             }

//             // Fait évoluer une armée en camps
//             System.out.println(">>> " + player1.toString() + player1.warriorString() + " veut faire évoluer son armée en camps " + tileArmy.getBuilding().toString());
//             ReplaceArmyWithCamp replaceArmyWithCamp = new ReplaceArmyWithCamp(player1);
//             replaceArmyWithCamp.process(tileArmy.getBuilding());

//             System.out.println(player1.toString() + player1.warriorString() + "\n");

//             // Achat de 5 guerriers
//             System.out.println(">>> " + player1.toString() + player1.warriorString() + " veut acheter 5 guerriers ");
//             BuyWarriors buyWarriors = new BuyWarriors(player1);
//             buyWarriors.process();

//             System.out.println(player1.toString() + player1.warriorString() + "\n");


//             // Construit un port
//             System.out.println(">>> " + player1.toString()+" veut construire un port");
//             BuildPort buildPort = new BuildPort(player1);
//             Tile tilePort = allLandTilesNextToSea.get(random.nextInt(allLandTilesNextToSea.size()));
//             buildPort.process(tilePort);

//             System.out.println(player1.toString() + player1.warriorString() + "\n");

//             // Echange 3 ressources contre une
//             System.out.println(">>> " + player1.toString()+" veut échanger 3 ressources contre une autre");
//             TradeRessources tradeRessources = new TradeRessources(player1);
//             tradeRessources.process(new Wood(), new Wood());

//             System.out.println(player1.toString() + player1.warriorString() + "\n");


//             // Achète une arme secrète
//             System.out.println(">>> " + player1.toString()+" veut acheter une arme secrète");


//             System.out.println(player1.toString() + player1.warriorString() + "\n");


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

