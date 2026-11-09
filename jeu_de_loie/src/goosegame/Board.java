package goosegame;

/**
 * Class Board
 */
public abstract class Board {
    /**
     * the number of cells
     */
    protected final int nbOfCells;
    /**
     * the cells
     */
    protected Cell[] cells;

    /**
     * The Constructor of Board
     * 
     * @param nbOfCells the nb of cells
     */
    public Board(int nbOfCells) {
        this.nbOfCells = nbOfCells;
        this.cells = new Cell[nbOfCells];
        initBoard();
    }

    /**
     * The abstract method initBoard
     */
    protected abstract void initBoard();

    /**
     * Get the cell
     * 
     * @param index the index
     * @return the index of the cells
     */
    public Cell getCell(int index) {
        return this.cells[index];
    }

    /**
     * Get the numbers of cells
     * 
     * @return the numbers of cells
     */
    public int getNbOfCells() {
        return nbOfCells;

    }
}