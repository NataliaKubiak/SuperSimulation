package entities;

public class Predator extends Creature {

    protected int attackPower;

    public Predator(Cell cell) {
        super("tiger",
                2,
                100,
                cell);
        this.attackPower = 5;
    }

    @Override
    public void makeMove(Cell cell) {
        this.cell = cell;
    }
}
