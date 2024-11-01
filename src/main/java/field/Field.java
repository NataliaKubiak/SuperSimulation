package field;

import entities.Cell;
import entities.Entity;
import field.actions.InitActions;
import field.actions.RoundActions;
import field.actions.TurnActions;

import java.util.HashMap;
import java.util.List;

public class Field {

    private static final int ENTITIES_AMOUNT = 50;
    private static final int FIELD_SIZE = 30;
    private static final int PAUSE_BETWEEN_STEPS_MILLIS = 600;
    private static final int GRASS_ADDITION = 10;
    private static final int HERBIVORES_ADDITION = 6;

    private HashMap<Cell, Entity> field = new HashMap<>();
    private InitActions initActions = new InitActions(field, FIELD_SIZE, ENTITIES_AMOUNT);
    private TurnActions turnActions = new TurnActions(field, FIELD_SIZE);
    private RoundActions roundActions = new RoundActions(field, FIELD_SIZE);
    private FieldRenderer renderer = new FieldRenderer(field, FIELD_SIZE);

    public void startSimulation() {
        initActions.start();
        renderer.renderField();
    }

    public void stopSimulation() {
        initActions.stop();
    }

    public boolean nextTurn() throws InterruptedException {
        boolean flag = true;
        List<Cell> allCreatures = turnActions.findAllCreatures();

        for (Cell creatureCell : allCreatures) {
            flag = turnActions.oneStepForCreature(renderer, creatureCell, PAUSE_BETWEEN_STEPS_MILLIS);
        }
        Thread.sleep(PAUSE_BETWEEN_STEPS_MILLIS);

        roundActions.clearPawSteps();
        roundActions.addGrass(GRASS_ADDITION);
        roundActions.addHerbivores(HERBIVORES_ADDITION);
        Thread.sleep(PAUSE_BETWEEN_STEPS_MILLIS);
        return flag;
    }
}
