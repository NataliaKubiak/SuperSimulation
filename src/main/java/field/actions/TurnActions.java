package field.actions;

import entities.*;
import field.FieldRenderer;
import field.WorldField;

import java.util.List;

public class TurnActions extends Actions {

    public TurnActions(WorldField field) {
        super(field);
    }

    public boolean oneStepForCreature(FieldRenderer renderer, Coordinates creatureCoord, int pauseMillis) throws InterruptedException {
        Creature creature = (Creature) field.getEntity(creatureCoord);

        SearchService searchService = new SearchService(field, creatureCoord);

        List<Coordinates> pathToGoalObj = searchService.findPathToGoalObject();
        try {
            int pathSize = pathToGoalObj.size();

            createPawStepsWay(pathSize, pathToGoalObj);
            renderer.renderChangesOnField();

            int creatureSpeed = creature.getSpeed();

            for (int i = 0; i < creatureSpeed; i++) {
                if (i < pathSize) {
                    if (i == pathSize - 1) {
                        Coordinates goalObjCoordinates = pathToGoalObj.get(pathSize - 1);
                        Entity goalObj = field.getEntity(goalObjCoordinates);
                        if (goalObj instanceof Herbivore) {
                            attackOrKillHerbivore((Herbivore) goalObj, creature, pathToGoalObj, i);
                        } else {
                            moveCreatureByOneStep(creature, pathToGoalObj, i);
                        }
                    } else {
                        moveCreatureByOneStep(creature, pathToGoalObj, i);
                    }
                    renderer.renderChangesOnField();
                    Thread.sleep(pauseMillis);
                }
            }
        } catch (NullPointerException ex) {
            return false;
        }
        return true;
    }

    private void createPawStepsWay(int pathSize, List<Coordinates> pathToGoalObj) {
        for (int i = 0; i < pathSize - 1 ; i++) {
            Coordinates stepToGoalCoordinates = pathToGoalObj.get(i);
            field.placeEntity(stepToGoalCoordinates, new PawSteps());
        }
    }

//    private void renderChangesOnField(FieldRenderer renderer) {
//        renderer.moveCursorToStart();
//        renderer.renderField();
//    }

    private void moveCreatureByOneStep(Creature creature, List<Coordinates> pathToGoalObj, int i) {
        Coordinates oldCoord = creature.getCell();
        creature.makeMove(pathToGoalObj, i);
        field.moveEntity(oldCoord, creature.getCell());
    }

    private void attackOrKillHerbivore(Herbivore goalObj, Creature creature, List<Coordinates> pathToGoalObj, int i) {
        int bunnyHp = goalObj.getHP();
        if ( bunnyHp > 50) {
            goalObj.attacked();
        } else {
            moveCreatureByOneStep(creature, pathToGoalObj, i);
        }
    }
}
