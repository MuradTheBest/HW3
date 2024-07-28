public class HealthyCell extends Cell {

    @Override
    public Cell nextGeneration(int numHealthy, int numSick) {
        if ((numHealthy < 2 || numHealthy > 3) || numSick > 3) {
            return new SickCell();
        }
        return this;
    }

    @Override
    public String toString() {
        return "H";
    }
}
