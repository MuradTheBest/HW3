public class DeadCell extends GameCell{
    @Override
    public GameCell nextGeneration(int numHealthy, int numSick) {
        if (numHealthy == 3) {
            return new HealthyCell();
        }
        return this;
    }

    @Override
    public String getType() {
        return "-";
    }
}
