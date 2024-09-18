package field;

import entities.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class TurnActions extends Actions {

    private static final Logger logger = LogManager.getLogger();

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
            logger.info("{}:{} made move!",
                    entry.getValue().getIcon(),
                    entry.getValue().getId());
        } else if (entry.getValue() instanceof Herbivore) {
            newX = entry.getKey().getX();
            newY = entry.getKey().getY() - 1;
            logger.info("{}:{} made move!",
                    entry.getValue().getIcon(),
                    entry.getValue().getId());
        }
        logger.info("NewX = {}", newX);
        logger.info("NewY = {}", newY);

        Cell newCell = new Cell(loopField(newX), loopField(newY));
        logger.info("newCell X = {}", newCell.getX());
        logger.info("newCell Y = {}", newCell.getY());
        return newCell;
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
