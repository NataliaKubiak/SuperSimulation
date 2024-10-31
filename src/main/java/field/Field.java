package field;

import entities.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Field {

    private HashMap<Cell, Entity> field = new HashMap<>();
    private static final int ENTITIES_AMOUNT = 80;
    private static final int FIELD_SIZE = 30;
    private static final int PAUSE_BETWEEN_STEPS_MILLIS = 700;

    private InitActions initActions = new InitActions(field, FIELD_SIZE, ENTITIES_AMOUNT);
    private TurnActions turnActions = new TurnActions(field, FIELD_SIZE);
    private FieldRenderer renderer = new FieldRenderer(field, FIELD_SIZE);

    public void startSimulation() {
        initActions.start();
        renderer.renderField();
    }

    public void nextTurn() throws InterruptedException {
        List<Cell> allCreatures = turnActions.findAllCreatures();

        for (Cell creatureCell : allCreatures) {
            turnActions.oneStepForAllCreatures(renderer, creatureCell, PAUSE_BETWEEN_STEPS_MILLIS);
        }
        Thread.sleep(PAUSE_BETWEEN_STEPS_MILLIS);
    }

    public HashMap<Cell, Entity> getField() {
        return field;
    }

    //method for testing SearchService
    public void searchActions() {

        List<Cell> eee = field.entrySet().stream()
                .filter(e -> e.getValue() instanceof Herbivore ||
                        e.getValue() instanceof Predator)
                .map(Map.Entry::getKey)
                .toList();

        for (Cell cell : eee) {
            System.out.println(field.get(cell));
        }
    }
}
