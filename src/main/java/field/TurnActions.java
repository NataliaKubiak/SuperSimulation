package field;

import entities.Cell;
import entities.Entity;
import entities.Herbivore;
import entities.Predator;

import java.util.HashMap;
import java.util.Map;

public class TurnActions extends Actions {

//    private static final Logger logger = LogManager.getLogger();

    public TurnActions(HashMap<Cell, Entity> field, int FIELD_SIZE) {
        super(field, FIELD_SIZE);
    }

//    public void oneStepForAllCreatures() {
//        HashMap<Cell, Entity> tempMap = new HashMap<>();
//        tempMap.putAll(field);
//
//        for (Map.Entry<Cell, Entity> entry : tempMap.entrySet()) {
//            if (entry.getValue() instanceof Creature) {
//                Cell newCoordinates = fakeSearch(entry);
//                ((Creature) entry.getValue()).makeMove(newCoordinates);
//                field.put(newCoordinates, entry.getValue());
//                field.put(entry.getKey(), new EmptySpot());
//            }
//        }
//    }

    public Cell findFirstPredator() {

        for (Map.Entry<Cell, Entity> entry : field.entrySet()) {
            if (entry.getValue() instanceof Predator) {
                return entry.getKey();
            }
        }
        return new Cell(-1, -1);
    }

    public Cell findFirstHerbivore() {

        for (Map.Entry<Cell, Entity> entry : field.entrySet()) {
            if (entry.getValue() instanceof Herbivore) {
                return entry.getKey();
            }
        }
        return new Cell(-1, -1);
    }
}
