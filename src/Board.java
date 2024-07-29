import java.util.Random;

/**
 * The Board class represents a game board consisting of cells.
 * It provides methods to generate the board, copy the board,
 * generate the next generation of cells, and check the board's state.
 */
public class Board {

    private Cell[][] cells; // 2D array of cells on the board
    private final int rows; // number of rows in the board
    private final int cols; // number of columns in the board
    private final int seed; // seed for the random number generator
    private final int range; // range for the random number generator

    /**
     * Constructor for the Board class.
     * @param rows the number of rows in the board
     * @param cols the number of columns in the board
     * @param seed the seed for the random number generator
     * @param range the range for the random number generator
     */
    public Board(int rows, int cols, int seed, int range) {
        cells = new Cell[rows][cols];
        this.rows = rows;
        this.cols = cols;
        this.seed = seed;
        this.range = range;

        // fills the board with random cells
        generateBoard(rows, cols, seed, range);
    }

    /**
     * Copy constructor for the Board class.
     * @param other the other board to copy
     */
    public Board(Board other) {
        this.rows = other.getRows();
        this.cols = other.getCols();
        this.cells = new Cell[this.rows][this.cols];
        this.seed = other.getSeed();
        this.range = other.getRange();

        // copies the cells from the other board
        for (int row = 0; row < this.rows; row++) {
            for (int col = 0; col < this.cols; col++) {
                this.cells[row][col] = other.getCells()[row][col];
            }
        }
    }

    /**
     * Fills the board with random cells based on the seed and range.
     * @param rows the number of rows in the board
     * @param cols the number of columns in the board
     * @param seed the seed for the random number generator
     * @param range the range for the random number generator
     */
    private void generateBoard(int rows, int cols, int seed, int range) {
        Random random = new Random(seed);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                cells[i][j] = (random.nextInt(range) % 2 == 0) ? new DeadCell() : new HealthyCell();
            }
        }
    }

    /**
     * Gets the cells of the board.
     * @return a 2D array of cells
     */
    public Cell[][] getCells() {
        return cells;
    }

    /**
     * Gets the number of rows in the board.
     * @return the number of rows
     */
    public int getRows() {
        return rows;
    }

    /**
     * Gets the number of columns in the board.
     * @return the number of columns
     */
    public int getCols() {
        return cols;
    }

    /**
     * Gets the seed for the random number generator.
     * @return the seed
     */
    public int getSeed() {
        return seed;
    }

    /**
     * Gets the range for the random number generator.
     * @return the range
     */
    public int getRange() {
        return range;
    }

    /**
     * Generates the next generation of cells based on the rules of the game.
     */
    public void nextGeneration() {
        Cell[][] newCells = new Cell[rows][cols]; // new board to store the next generation of cells

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {

                int numHealthy = 0;
                int numSick = 0;

                // counts the number of healthy and sick cells in the 3x3 grid around this cell
                for (int k = row-1; k <= row+1; k++) {
                    for (int l = col-1; l <= col+1; l++) {
                        if ((k >= 0 && k < rows && l >= 0 && l < cols)) {
                            // skip the current cell
                            if (k == row && l == col) {
                                continue;
                            }

                            if (cells[k][l] instanceof HealthyCell) {
                                numHealthy++;
                            }
                            else if (cells[k][l] instanceof SickCell) {
                                numSick++;
                            }
                        }
                    }
                }

                // creates a new cell at this location in the next generation's board based on the rules of the game
                newCells[row][col] = cells[row][col].nextGeneration(numHealthy, numSick);
            }
        }

        this.cells = newCells; // updates the board with the next generation of cells
    }

    /**
     * Checks if the current board is stabilized.
     * @param other the other board to compare with
     * @return true if the boards are the same, false otherwise
     */
    public boolean isStabilized(Board other) {
        return this.equals(other);
    }

    /**
     * Checks if all cells in the board are dead.
     * @return true if all cells are dead, false otherwise
     */
    public boolean isDead() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!(cells[i][j] instanceof DeadCell)) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Prints the board.
     * @return the string representation of the board
     */
    @Override
    public String toString() {

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols-1; j++) {
                System.out.print(cells[i][j].getType() + " "); // prints the cell followed by a space
            }
            System.out.print(cells[i][cols-1].getType()); // prints the last cell in the row
            System.out.println();
        }

        return "";
    }

    /**
     * Checks if two boards are equal.
     * @param obj the other board to compare with
     * @return true if the boards are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // checks if the boards are the same object
        if (!(obj instanceof Board other)) return false; // checks if the other object is a board

        // checks if the boards have the same dimensions
        if (this.rows != other.rows || this.cols != other.cols) return false;

        // checks if the boards have the same cells
        for (int i = 0; i < other.rows; i++) {
            for (int j = 0; j < other.cols; j++) {
                if (!this.cells[i][j].equals(other.cells[i][j])) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Calculates the hash code of the board.
     * @return the hash code of the board
     */
    @Override
    public int hashCode() {
        int result = 1;

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                result = 31 * result + cells[i][j].hashCode(); // calculates the hash code based on the cells
            }
        }

        return result;
    }
}