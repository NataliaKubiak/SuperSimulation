package entities;

public class Herbivore extends Creature {

    public Herbivore(Cell cell) {
        super("bunny",
                cell,
                1,
                100);
    }

    @Override
    public void makeMove() {
        int newX = cell.getX() + speed;
        cell.setX(newX);
    }
}
