public class Game {

    private Board board;
    private int numOfGenerations;

    public Game(int rows, int cols, int seed, int range, int numOfGenerations) {
        this.board = new Board(rows, cols, seed, range);
        this.numOfGenerations = numOfGenerations;
    }

    public void runGame() {
        Board temp = new Board(board.getRows(), board.getCols(), board.getSeed(), board.getRange());
        this.board = new Board(board.getRows(), board.getCols(), board.getSeed(), board.getRange());

        for (int i = 0; i <= numOfGenerations; i++) {

            System.out.println("Generation " + i + ":");
            System.out.print(board);
            System.out.println(board.hashCode());

            int count = 0;

            for (int row = 0; row < board.getRows(); row++) {
                for (int col = 0; col < board.getCols(); col++) {
//                    temp.getCells()[row][col] = new Cell(board.getCells()[row][col].getType(), row, col);
                    temp.getCells()[row][col] = board.getCells()[row][col];
                    if (board.getCells()[row][col].getType() == CellType.DEAD) {
                        count++;
                    }
                }
            }

            if (count == board.getRows()*board.getCols()) {
                System.out.println("All cells are dead.");
                return;
            }

            board.nextGeneration();

            // current generation is the same as the previous one
            if (board.equals(temp) && i+1 <= numOfGenerations) {
                System.out.println("Generation " + (i+1) + ":" );
                System.out.print(board);
                System.out.println("Cells have stabilized.");
                return;
            }
        }

        System.out.println("The generation limitation was reached.");
    }
}
