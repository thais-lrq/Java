package goosegame;

import java.util.ArrayList;
import java.util.List;

/**
 * The class Game
 */
public class Game {
    private List<Player> players = new ArrayList<>();
    private Board board;

    /**
     * The constructor of Game
     * 
     * @param board the board
     */
    public Game(Board board) {
        this.board = board;
    }

    /**
     * Add a player to the game
     * 
     * @param p the player
     */
    public void addPlayer(Player p) {
        Cell start = board.getCell(0);
        p.changeCell(start);
        start.ArriveAtCell(p, board);
        players.add(p);
        System.out.println(p + " is added to the game at start.");
    }

    /**
     * Compute the target index considering the board limits
     * 
     * @param p    the player
     * @param dice the dice
     */
    private int computeTargetIndex(Player p, int dice) {
        int targetIndex = p.getCell().getIndex() + dice;
        if (targetIndex >= board.getNbOfCells()) {
            targetIndex = board.getNbOfCells() - 1 - (targetIndex - (board.getNbOfCells() - 1));
        }
        return targetIndex;
    }

    /**
     * Print the type of a cell
     * 
     * @param c a cell
     */
    private void printCellType(Cell c) {
        String type = c.getTypeName();
        if (!type.isEmpty()) {
            System.out.println(" (" + type + ")");
        }

    }

    /**
     * Play the game until a player wins
     */
    public void play() {
        Player winner = null;

        while (winner == null) {
            for (Player p : players) {
                Cell cell = p.getCell();
                if (cell.isBlocked(p)) {
                    System.out.println(p + " is in cell " + cell.getIndex()
                            + ", " + p + " cannot play.");
                    cell.updateWaitStatus(p);
                    continue;
                }

                int dice = p.twoDiceThrow();
                System.out.println(p + " is in cell " + cell.getIndex() + " and throws " + dice);

                int targetIndex = computeTargetIndex(p, dice);
                Cell reached = board.getCell(targetIndex);

                if (cell.getIndex() != 0) {
                    cell.removePlayer();
                }

                System.out.print(" and reaches cell " + reached.getIndex());
                printCellType(reached);

                Cell destination = reached.applyRebound(board, dice);
                destination = destination.ArriveAtCell(p, board);

                if (destination != reached) {
                    System.out.print(" and jumps to cell " + destination.getIndex());
                    printCellType(destination);
                }

                System.out.println();

                p.changeCell(destination);

                if (destination.isWinningCell()) {
                    winner = p;
                    break;
                }
            }

        }
        System.out.println("\n" + winner + " has won!");
    }
}
