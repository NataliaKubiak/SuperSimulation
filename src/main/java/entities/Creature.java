package entities;

import java.util.Objects;

public abstract class Creature extends Entity {

    protected int speed;
    protected int HP;

    public Creature(String icon,
                    Cell cell,
                    int speed, int HP) {
        super(icon, cell);
        this.speed = speed;
        this.HP = HP;
    }

    public abstract void makeMove();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Creature creature = (Creature) o;
        return speed == creature.speed && HP == creature.HP && cell.equals(creature.cell);
    }

    @Override
    public int hashCode() {
        //TODO чем одно отличается от другого?
//        int result = speed;
//        result = 31 * result + HP;
//        return result;
        return Objects.hash(cell);
    }
}
