public abstract class GameCell {

    public GameCell() {
    }

    public abstract GameCell nextGeneration(int numHealthy, int numSick);
    public abstract String getType();

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof GameCell cell)) return false;
        return this.getClass() == cell.getClass();
    }
}
