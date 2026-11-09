// package Essential;


// /**
//  * Demonstrates creating a game board and printing it.
//  */
// public class Livrable1 {
//     /**
//     * Sets up the game board and prints it.
//     * 
//     * @param args Command-line arguments for board width and height.
//     */
//     public static void main(String[] args) {

//         if (args.length < 2) {
//             System.out.println("it's necessary to have <width> <height>");
//             return;
//         }

//         try {
//             int width = Integer.parseInt(args[0]);
//             int height = Integer.parseInt(args[1]);

//             Board board = new Board(width, height, 0.5);
//             board.printBoard();
//         } catch (NumberFormatException e) {
//             System.out.println("Arguments must be integers.");
//         }
//     }
// }
