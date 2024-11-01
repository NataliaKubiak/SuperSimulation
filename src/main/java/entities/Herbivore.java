package entities;

public class Herbivore extends Creature {

    private int HP;

    public Herbivore(Coordinates coordinates) {
        super("bunny",
                3,
                coordinates);
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
