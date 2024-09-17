package entities;

public abstract class Creature extends Entity {

    protected int speed;
    protected int HP;
    protected Cell cell;

    public Creature(String icon,
                    int speed,
                    int HP,
                    Cell cell) {
        super(icon);
        this.speed = speed;
        this.HP = HP;
        this.cell = cell;
    }

    public abstract void makeMove(Cell cell);
}
