package entities;

import java.util.List;

public class Herbivore extends Creature {

    public Herbivore(Cell cell) {
        super("bunny",
                1,
                100,
                cell);
    }

    @Override
    public void makeMove(List<Cell> path) {

        //if path = sequence of cells to Grass -> 1 move = cell from sequence at the index speed - 1
        this.cell = path.get(speed - 1);
    }
}
