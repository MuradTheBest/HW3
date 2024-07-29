public class DeadCell extends Cell {

    /**
     * Constructs a dead cell.
     */
    public DeadCell() {
        this.type = "-";
    }

    /**
     * Returns a new cell based on the number of healthy and sick neighbors.
     * @param numHealthy the number of healthy neighbors
     * @param numSick the number of sick neighbors
     * @return a new cell based on the number of healthy and sick neighbors
     */
    @Override
    public Cell nextGeneration(int numHealthy, int numSick) {
        if (numHealthy == 3) {
            return new HealthyCell();
        }
        return this;
    }

}
