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

    public void oneStepForAllCreatures(FieldRenderer renderer, Cell creatureCoord, int pauseMillis) throws InterruptedException {
        Creature creature = (Creature) field.get(creatureCoord);

        SearchService searchService = new SearchService(field, FIELD_SIZE, creatureCoord);

        List<Cell> pathToGoalObj = searchService.findPathToGoalObject();
        int pathSize = pathToGoalObj.size();

        createPawStepsWay(pathSize, pathToGoalObj);
        renderChangesOnField(renderer);

        int creatureSpeed = creature.getSpeed();

        for (int i = 0; i < creatureSpeed; i++) {
            if (i < pathSize) {
                if (i == pathSize - 1) {
                    Cell goalObjCell = pathToGoalObj.get(pathSize - 1);
                    Entity goalObj = field.get(goalObjCell);
                    if (goalObj instanceof Herbivore) {
                        attackOrKillHerbivore((Herbivore) goalObj, creature, pathToGoalObj, i);
                    } else {
                        moveCreature(creature, pathToGoalObj, i);
                    }
                } else {
                    moveCreature(creature, pathToGoalObj, i);
                }
                renderChangesOnField(renderer);
                Thread.sleep(pauseMillis);
            }
        }
    }

    private void createPawStepsWay(int pathSize, List<Cell> pathToGoalObj) {
        for (int i = 0; i < pathSize - 1 ; i++) {
            Cell stepToGoalCell = pathToGoalObj.get(i);
            field.put(stepToGoalCell, new WayToGoalObj());
        }
    }

    private void renderChangesOnField(FieldRenderer renderer) {
        renderer.moveCursorToStart();
        renderer.renderField();
    }

    private void moveCreature(Creature creature, List<Cell> pathToGoalObj, int i) {
        Cell oldCoord = creature.getCell();
        creature.makeMove(pathToGoalObj, i);
        field.put(oldCoord, new EmptySpot());
        field.put(creature.getCell(), creature);
    }

    private void attackOrKillHerbivore(Herbivore goalObj, Creature creature, List<Cell> pathToGoalObj, int i) {
        int bunnyHp = goalObj.getHP();
        if ( bunnyHp > 50) {
            goalObj.attacked();
        } else {
            moveCreature(creature, pathToGoalObj, i);
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
}
