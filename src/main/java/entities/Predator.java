package entities;

import java.util.List;

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
    public void makeMove(List<Cell> path) {

        //if path = sequence of cells to Herbivore -> 1 move = cell from sequence at the index speed - 1
        this.cell = path.get(speed - 1);
    }
}
