package entities;

public class Predator extends Creature {

    protected int attackPower;

    public Predator(Cell cell) {
        super("\ud83d\udc2f",
                cell,
                2,
                100);
        this.attackPower = 5;
    }

    @Override
    public void makeMove() {
        int newX = cell.getX() + speed;
        cell.setX(newX);
    }
}
