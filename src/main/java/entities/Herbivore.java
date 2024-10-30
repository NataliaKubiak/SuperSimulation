package entities;

import java.util.List;

public class Herbivore extends Creature {

    public Herbivore(Cell cell) {
        super("bunny",
                2,
                100,
                cell);
    }

    @Override
    public void makeMove(List<Cell> path, int stepIndex) {

        if (path.size() <= stepIndex) {
            this.cell = path.get(0);
        } else {
            this.cell = path.get(stepIndex);
        }
    }
}
