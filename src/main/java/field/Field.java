package field;

import entities.Cell;
import entities.Entity;

import java.util.HashMap;

public class Field {

    private HashMap<Cell, Entity> field = new HashMap<>();
    private static final int ENTITIES_AMOUNT = 50;
    private static final int FIELD_SIZE = 20;

    private InitActions initActions = new InitActions(field, FIELD_SIZE, ENTITIES_AMOUNT);
    private TurnActions turnActions = new TurnActions(field, FIELD_SIZE);
    private FieldRenderer renderer = new FieldRenderer(field, FIELD_SIZE);

    public void startSimulation() {
        initActions.start();
        renderer.renderField();
    }

    public void nextTurn() {
        turnActions.oneStepForAllCreatures();
        renderer.moveCursorToStart();
        renderer.renderField();
    }

    public HashMap<Cell, Entity> getField() {
        return field;
    }
}
