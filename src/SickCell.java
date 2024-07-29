public class SickCell extends Cell {

    /**
     * Constructs a sick cell.
     */
    public SickCell() {
        this.type = "S";
    }

    /**
     * Returns a new cell based on the number of healthy and sick neighbors.
     * @param numHealthy the number of healthy neighbors
     * @param numSick the number of sick neighbors
     * @return a new cell based on the number of healthy and sick neighbors
     */
    @Override
    public Cell nextGeneration(int numHealthy, int numSick) {
        if ((numHealthy < 2 || numHealthy > 3) || numSick > 2) {
            return new DyingCell();
        }
        else {
            return new HealthyCell();
        }
    }
}
