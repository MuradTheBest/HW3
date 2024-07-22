import java.util.Random;

public class Cell {
    private CellType type;

    public Cell(CellType type) {
        this.type = type;
    }

    public Cell(Random random, int range) {
        this.type = random.nextInt(range) % 2 == 0 ? CellType.DEAD : CellType.HEALTHY;
    }

    public Cell nextGeneration(int numHealthy, int numSick) {
        switch (this.type) {
            case DEAD:
                if (numHealthy == 3) {
                    return new Cell(CellType.HEALTHY);
                }
                break;
            case HEALTHY:
                if ((numHealthy < 2 || numHealthy > 3) || numSick > 3) {
                    return new Cell(CellType.SICK);
                }
                break;
            case SICK:
                if ((numHealthy < 2 || numHealthy > 3) || numSick > 2) {
                    return new Cell(CellType.DYING);
                }
                else {
                    return new Cell(CellType.HEALTHY);
                }
            case DYING:
                if (numHealthy != 3 || numSick > 1) {
                    return new Cell(CellType.DEAD);
                }
                else {
                    return new Cell(CellType.HEALTHY);
                }
        }

//        System.out.println(type + ", numHealthy = " + numHealthy + ", numSick = " + numSick);

        return this;
    }

    public CellType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cell cell)) return false;
        return this.type == cell.type;
    }

    @Override
    public int hashCode() {
        return this.type.toString().hashCode();
    }
}
