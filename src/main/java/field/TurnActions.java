package field;

import entities.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TurnActions extends Actions {

//    private static final Logger logger = LogManager.getLogger();

    public TurnActions(HashMap<Cell, Entity> field, int FIELD_SIZE) {
        super(field, FIELD_SIZE);
    }

    public void oneStepForAllCreatures(FieldRenderer renderer, Cell creatureCoord) throws InterruptedException {
        Creature creature = (Creature) field.get(creatureCoord);

        SearchService searchService = new SearchService(field, FIELD_SIZE, creatureCoord);

        List<Cell> pathToGoalObj = searchService.findPathToGoalObject();

        for (int i = 0; i < pathToGoalObj.size() - 1 ; i++) {
            Cell stepToGoalCell = pathToGoalObj.get(i);
            field.put(stepToGoalCell, new WayToGoalObj());
        }
        renderer.moveCursorToStart();
        renderer.renderField();

        int creatureSpeed = creature.getSpeed();

        for (int i = 0; i < creatureSpeed; i++) {
            Cell oldCoord = creature.getCell();
            creature.makeMove(pathToGoalObj, i);
            field.put(oldCoord, new EmptySpot());
            field.put(creature.getCell(), creature);

            renderer.moveCursorToStart();
            renderer.renderField();
            Thread.sleep(700);
        }
    }

    public List<Cell> findAllPredators() {

        return field.entrySet().stream()
                .filter(e -> e.getValue() instanceof Predator)
                .map(Map.Entry::getKey)
                .toList();
    }

    public List<Cell> findAllHerbivores() {

        return field.entrySet().stream()
                .filter(e -> e.getValue() instanceof Herbivore)
                .map(Map.Entry::getKey)
                .toList();
    }

    public List<Cell> findAllCreatures() {

        return field.entrySet().stream()
                .filter(e -> e.getValue() instanceof Herbivore ||
                        e.getValue() instanceof Predator)
                .map(Map.Entry::getKey)
                .toList();
    }

        public void clearPawSteps() {

    }
}
