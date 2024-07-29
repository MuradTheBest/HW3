public class HealthyCell extends Cell {

    /**
     * Constructs a healthy cell.
     */
    public HealthyCell() {
        this.type = "H";
    }

    /**
     * Returns a new cell based on the number of healthy and sick neighbors.
     * @param numHealthy the number of healthy neighbors
     * @param numSick the number of sick neighbors
     * @return a new cell based on the number of healthy and sick neighbors
     */
    @Override
    public Cell nextGeneration(int numHealthy, int numSick) {
        if ((numHealthy < 2 || numHealthy > 3) || numSick > 3) {
            return new SickCell();
        }
        return this;
    }
}
