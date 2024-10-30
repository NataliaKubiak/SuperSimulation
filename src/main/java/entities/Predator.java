package entities;

import java.util.List;

public class Predator extends Creature {

    protected int attackPower;

    public Predator(Cell cell) {
        super("tiger",
                4,
                100,
                cell);
        this.attackPower = 5;
    }

    @Override
    public void makeMove(List<Cell> path, int stepIndex) {

        //TODO переписать перепрыгивание на 0, чтобы перепрыгивало на нужную клетку
        if (path.size() <= stepIndex) {
            this.cell = path.get(0);
        } else {
            this.cell = path.get(stepIndex);
        }
    }
}
