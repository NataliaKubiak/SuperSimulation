package entities;

import java.util.List;

public abstract class Creature extends Entity {

    protected int speed;
    protected Coordinates coordinates;

    public Creature(String icon,
                    int speed,
                    Coordinates coordinates) {
        super(icon);
        this.speed = speed;
        this.coordinates = coordinates;
    }

    public void makeMove(List<Coordinates> path, int stepIndex) {
        this.coordinates = path.get(stepIndex);
    }

    public int getSpeed() {
        return speed;
    }

    public Coordinates getCell() {
        return coordinates;
    }
}
