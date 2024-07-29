public class DyingCell extends Cell {

    /**
     * Constructs a dying cell.
     */
    public DyingCell() {
        this.type = "D";
    }

    /**
     * Returns a new cell based on the number of healthy and sick neighbors.
     * @param numHealthy the number of healthy neighbors
     * @param numSick the number of sick neighbors
     * @return a new cell based on the number of healthy and sick neighbors
     */
    @Override
    public Cell nextGeneration(int numHealthy, int numSick) {
        if (numHealthy != 3 || numSick > 1) {
            return new DeadCell();
        }
        else {
            return new HealthyCell();
        }
    }
}
