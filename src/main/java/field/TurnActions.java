package field;

import entities.*;

import java.util.HashMap;
import java.util.Map;

public class TurnActions extends Actions {

    private HashMap<Cell, Entity> field;
    private int fieldSize;

    public TurnActions(HashMap<Cell, Entity> field, int fieldSize) {
        this.field = field;
        this.fieldSize = fieldSize;
    }

    public void oneStepForAllCreatures() {
        HashMap<Cell, Entity> tempMap = new HashMap<>();
        tempMap.putAll(field);

        //swap values
        //hhh.put(a, hhh.put(b, hhh.get(a)));

        for (Map.Entry<Cell, Entity> entry : tempMap.entrySet()) {
            if (entry.getValue() instanceof Creature) {
                Cell newCoordinates = fakeSearch(entry);
                ((Creature) entry.getValue()).makeMove(newCoordinates);
                field.put(newCoordinates, entry.getValue());
                field.put(entry.getKey(), new EmptySpot());
            }
        }
    }

    private Cell fakeSearch(Map.Entry<Cell, Entity> entry) {
        int newX = 0;
        int newY = 0;
        if (entry.getValue() instanceof Predator) {
            newX = entry.getKey().getX() + 1;
            newY = entry.getKey().getY();
        } else if (entry.getValue() instanceof Herbivore) {
            newX = entry.getKey().getX();
            newY = entry.getKey().getY() - 1;
        }

        return new Cell(loopField(newX), loopField(newY));
    }

    private int loopField(int x) {
        //base case
        if (x >= 0 && x < fieldSize) {
            return x;
        } else if (x < 0) {
            return loopField(fieldSize + x);
        } else {
            return loopField(x - fieldSize);
        }
    }

}
