public class SickCell extends Cell {

    @Override
    public Cell nextGeneration(int numHealthy, int numSick) {
        if ((numHealthy < 2 || numHealthy > 3) || numSick > 2) {
            return new DyingCell();
        }
        else {
            return new HealthyCell();
        }
    }

    @Override
    public String toString() {
        return "S";
    }
}
