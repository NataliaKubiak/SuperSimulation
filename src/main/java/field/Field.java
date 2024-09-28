package field;

import entities.Cell;
import entities.Entity;
import entities.WayToGoalObj;

import java.util.HashMap;
import java.util.List;

public class Field {

    private HashMap<Cell, Entity> field = new HashMap<>();
    private static final int ENTITIES_AMOUNT = 20;
    private static final int FIELD_SIZE = 20;

    private InitActions initActions = new InitActions(field, FIELD_SIZE, ENTITIES_AMOUNT);
    private TurnActions turnActions = new TurnActions(field, FIELD_SIZE);
    private FieldRenderer renderer = new FieldRenderer(field, FIELD_SIZE);

    public void startSimulation() {
        initActions.start();
        renderer.renderField();
    }

    public void nextTurn() {
//        turnActions.oneStepForAllCreatures();
        renderer.moveCursorToStart();
        renderer.renderField();
    }

    public HashMap<Cell, Entity> getField() {
        return field;
    }

    //method for testing SearchService
    public void searchActions() {
        Cell firstHerbivoreCoord = turnActions.findFirstPredator();
        SearchService searchService = new SearchService(field, FIELD_SIZE, firstHerbivoreCoord);

        List<Cell> pathToGrass = searchService.findPathToGoalObject();


        for (int i = 0; i < pathToGrass.size() - 1 ; i++) {
            Cell stepToGoalCell = pathToGrass.get(i);
            field.put(stepToGoalCell, new WayToGoalObj());
        }

        System.out.println("first obj Coord = " + firstHerbivoreCoord);
        System.out.println("This is hopefully path!!!!!!!!\n" + pathToGrass);

        renderer.renderField();


    }
}
