package entities;

import java.util.List;

public class Predator extends Creature {

    protected int attackPower;

    public Predator(Cell cell) {
        super("tiger",
                5,
                100,
                cell);
        this.attackPower = 5;
    }

    @Override
    public void makeMove(List<Cell> path, int stepIndex) {
            this.cell = path.get(stepIndex);
    }
}
