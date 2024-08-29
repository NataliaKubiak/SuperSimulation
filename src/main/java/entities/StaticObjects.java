package entities;

import java.util.Objects;

public abstract class StaticObjects extends Entity {

    public StaticObjects(String icon, Cell cell) {
        super(icon, cell);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StaticObjects staticObjects = (StaticObjects) o;
        return Objects.equals(cell, staticObjects.cell);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cell);
    }
}
