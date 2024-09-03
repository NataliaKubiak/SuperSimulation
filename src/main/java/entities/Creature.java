package entities;

public abstract class Creature extends Entity {

    protected int speed;
    protected int HP;

    public Creature(String icon,
                    int speed, int HP) {
        super(icon);
        this.speed = speed;
        this.HP = HP;
    }

    public abstract void makeMove();
}
