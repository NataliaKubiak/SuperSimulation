package entities;

import java.util.List;

public class Herbivore extends Creature {

    public Herbivore(Cell cell) {
        super("bunny",
                3,
                100,
                cell);
    }

    @Override
    public void makeMove(List<Cell> path, int stepIndex) {
            this.cell = path.get(stepIndex);
    }

    public void attacked() {
        this.HP = HP - 50;
        this.icon = "survival";
    }
}
