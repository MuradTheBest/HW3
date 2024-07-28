public class DeadCell extends Cell {

    @Override
    public Cell nextGeneration(int numHealthy, int numSick) {
        if (numHealthy == 3) {
            return new HealthyCell();
        }
        return this;
    }

    @Override
    public String toString() {
        return "-";
    }
}
