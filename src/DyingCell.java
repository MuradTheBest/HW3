public class DyingCell extends GameCell{
    @Override
    public GameCell nextGeneration(int numHealthy, int numSick) {
        if (numHealthy != 3 || numSick > 1) {
            return new DeadCell();
        }
        else {
            return new HealthyCell();
        }
    }

    @Override
    public String getType() {
        return "D";
    }
}
