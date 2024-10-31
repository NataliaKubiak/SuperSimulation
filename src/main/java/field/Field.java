package field;

import entities.Cell;
import entities.Entity;

import java.util.HashMap;
import java.util.List;

public class Field {

    private HashMap<Cell, Entity> field = new HashMap<>();
    private static final int ENTITIES_AMOUNT = 20;
    private static final int FIELD_SIZE = 30;
    private static final int PAUSE_BETWEEN_STEPS_MILLIS = 600;
    private static final int GRASS_ADDITION = 10;
    private static final int HERBIVORES_ADDITION = 6;

    private InitActions initActions = new InitActions(field, FIELD_SIZE, ENTITIES_AMOUNT);
    private TurnActions turnActions = new TurnActions(field, FIELD_SIZE);
    private RoundActions roundActions = new RoundActions(field, FIELD_SIZE);
    private FieldRenderer renderer = new FieldRenderer(field, FIELD_SIZE);

    public void startSimulation() {
        initActions.start();
        renderer.renderField();
    }

    public void nextTurn() throws InterruptedException {
        List<Cell> allCreatures = turnActions.findAllCreatures();

        for (Cell creatureCell : allCreatures) {
            turnActions.oneStepForCreature(renderer, creatureCell, PAUSE_BETWEEN_STEPS_MILLIS);
        }
        Thread.sleep(PAUSE_BETWEEN_STEPS_MILLIS);

        roundActions.clearPawSteps();
        roundActions.addGrass(GRASS_ADDITION);
        roundActions.addHerbivores(HERBIVORES_ADDITION);
        Thread.sleep(PAUSE_BETWEEN_STEPS_MILLIS);
    }

    public HashMap<Cell, Entity> getField() {
        return field;
    }
}
