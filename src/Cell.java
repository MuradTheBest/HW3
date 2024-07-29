public abstract class Cell {

    protected String type; // the type of the cell

    public Cell() {}

    /**
     * Returns a new cell based on the number of healthy and sick neighbors.
     * @param numHealthy the number of healthy neighbors
     * @param numSick the number of sick neighbors
     * @return a new cell based on the number of healthy and sick neighbors
     */
    public abstract Cell nextGeneration(int numHealthy, int numSick);

    /**
     * Returns the type of the cell.
     * @return the type of the cell
     */
    public String getType() {
        return this.type;
    }

    /**
     * Checks if two cells are equal.
     * @param obj the other cell to compare with
     * @return true if the cells are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // checks if the cells are the same object
        if (!(obj instanceof Cell cell)) return false; // checks if the other object is a cell
        return this.getClass() == cell.getClass(); // checks if the cells are of the same type
    }

    @Override
    public int hashCode() {
        return this.type.hashCode();
    }
}
