package entities;

public class Herbivore extends Creature {

    private int HP;

    public Herbivore(Cell cell) {
        super("bunny",
                3,
                cell);
        this.HP = 100;
    }

    public void attacked() {
        this.HP = HP - 50;
        this.icon = "survival";
    }

    public int getHP() {
        return HP;
    }
}
