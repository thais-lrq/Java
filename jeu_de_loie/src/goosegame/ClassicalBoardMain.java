package goosegame;

/**
 * The classicalBordMain
 */
public class ClassicalBoardMain {
    /**
     * the main
     * 
     * @param args the args
     */
    public static void main(String[] args) {

        Board board = new ClassicalBoard();

        Game game = new Game(board);

        game.addPlayer(new Player("Alice"));
        game.addPlayer(new Player("Bob"));
        game.addPlayer(new Player("Charlie"));

        System.out.println("Starting the Goose Game\n");
        game.play();
    }
}
