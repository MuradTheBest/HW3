public class Game {

    private Board board; // the board for the game
    private final int numOfGenerations; // the number of generations to run the game

    /**
     * Constructor for the Game class.
     * @param rows the number of rows in the board
     * @param cols the number of columns in the board
     * @param seed the seed for the random number generator
     * @param range the range for the random number generator
     * @param numOfGenerations the number of generations to run the game
     */
    public Game(int rows, int cols, int seed, int range, int numOfGenerations) {
        this.board = new Board(rows, cols, seed, range); // creates a new board based on the parameters
        this.numOfGenerations = numOfGenerations; // sets the number of generations
    }

    /**
     * Runs the game for the specified number of generations.
     */
    public void runGame() {
        // resets the board to the initial state
        this.board = new Board(board.getRows(), board.getCols(), board.getSeed(), board.getRange());
        // creates a temporary board to store the current generation's cells
        Board temp;

        for (int i = 0; i <= numOfGenerations; i++) {

            System.out.println("Generation " + i + ":");
            System.out.print(board);

            temp = new Board(board); // copies the current generation's cells to the temp board

            // all cells are dead
            if (board.isDead()) {
                System.out.println("All cells are dead.");
                return;
            }

            board.nextGeneration();

            // checks if current generation is the same as the previous one
            if (board.isStabilized(temp) && i+1 <= numOfGenerations) {
                System.out.println("Generation " + (i+1) + ":" );
                System.out.print(board);
                System.out.println("Cells have stabilized.");
                return;
            }
        }

        System.out.println("The generation limitation was reached.");
    }
}
