public class HealthyCell extends GameCell{

    @Override
    public GameCell nextGeneration(int numHealthy, int numSick) {
        if ((numHealthy < 2 || numHealthy > 3) || numSick > 3) {
            return new SickCell();
        }
        return this;
    }

    @Override
    public String getType() {
        return "S";
    }
}
