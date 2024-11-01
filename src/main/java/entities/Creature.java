package entities;

import java.util.List;

public abstract class Creature extends Entity {

    protected int speed;
    protected Cell cell;

    public Creature(String icon,
                    int speed,
                    Cell cell) {
        super(icon);
        this.speed = speed;
        this.cell = cell;
    }

    public void makeMove(List<Cell> path, int stepIndex) {
        this.cell = path.get(stepIndex);
    }

    public int getSpeed() {
        return speed;
    }

    public Cell getCell() {
        return cell;
    }
}
