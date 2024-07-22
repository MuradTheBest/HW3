import java.util.Random;

public class Board {

    private Cell[][] cells;
    private int rows;
    private int cols;
    private int seed;
    private int range;

    public Board(int rows, int cols, int seed, int range) {
        cells = new Cell[rows][cols];
        this.rows = rows;
        this.cols = cols;
        this.seed = seed;
        this.range = range;

        // fills the board with random cells
        Random random = new Random(seed);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                cells[i][j] = new Cell(random, range);
            }
        }
    }

    public Cell[][] getCells() {
        return cells;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public int getSeed() {
        return seed;
    }

    public int getRange() {
        return range;
    }

    public void nextGeneration() {
        Cell[][] newCells = new Cell[rows][cols]; // new board to store the next generation of cells

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {

                int numHealthy = 0;
                int numSick = 0;

                // counts the number of healthy and ill cells in the 3x3 grid around this cell
                for (int k = row-1; k <= row+1; k++) {
                    for (int l = col-1; l <= col+1; l++) {
                        if ((k >= 0 && k < rows && l >= 0 && l < cols)) {
                            if (k == row && l == col) {
                                continue;
                            }
//                            System.out.println("Checking neighbour: [" + k+","+l+"]");
                            if (cells[k][l].getType() == CellType.HEALTHY) {
                                numHealthy++;
                            }
                            else if (cells[k][l].getType() == CellType.SICK) {
                                numSick++;
                            }
                        }
                    }
                }

                // creates a new cell at this location based on the rules of the game
                newCells[row][col] = cells[row][col].nextGeneration(numHealthy, numSick);
            }
        }

        this.cells = newCells;
    }

    @Override
    public String toString() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length-1; j++) {
                System.out.print(cells[i][j].getType() + " ");
            }
            System.out.print(cells[i][cells[i].length-1].getType()); // prints the last cell in the row
            System.out.println();
        }

        return "";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Board)) return false;

        Board other = (Board) obj;

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

    @Override
    public int hashCode() {

        int result = 1;

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                result = 31 * result + cells[i][j].hashCode();
            }
        }

        return result;
    }
}
