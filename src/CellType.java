public enum CellType {
    HEALTHY ("H"),
    SICK ("S"),
    DYING ("D"),
    DEAD ("-");

    private String type;

    CellType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
