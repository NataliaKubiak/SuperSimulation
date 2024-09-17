package entities;

public class Herbivore extends Creature {

    public Herbivore(Cell cell) {
        super("bunny",
                1,
                100,
                cell);
    }

    @Override
    public void makeMove(Cell cell) {
        this.cell = cell;
    }
}
