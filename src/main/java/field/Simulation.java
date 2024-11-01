package field;

import entities.Coordinates;
import field.actions.InitActions;
import field.actions.RoundActions;
import field.actions.TurnActions;

import java.util.List;

public class Simulation {

    private final int entitiesAmount = 40;
    private final int worldFieldSize = 30;
    private final int pauseBetweenStepsMillis = 600;
    private final int grassAddition = 10;
    private final int herbivoresAddition = 6;

    private WorldField field = new WorldField(worldFieldSize, entitiesAmount);
    private InitActions initActions = new InitActions(field);
    private TurnActions turnActions = new TurnActions(field);
    private RoundActions roundActions = new RoundActions(field);
    private FieldRenderer renderer = new FieldRenderer(field);

    public void start() {
        initActions.start();
        renderer.renderField();
    }

    public void stop() {
        initActions.stop();
    }

    public boolean nextTurn() throws InterruptedException {
        boolean flag = true;
        List<Coordinates> allCreatures = field.findAllCreatures();

        for (Coordinates creatureCoordinates : allCreatures) {
            flag = turnActions.oneStepForCreature(renderer, creatureCoordinates, pauseBetweenStepsMillis);
        }
        Thread.sleep(pauseBetweenStepsMillis);

        roundActions.clearPawSteps();
        roundActions.addGrass(grassAddition);
        roundActions.addHerbivores(herbivoresAddition);
        Thread.sleep(pauseBetweenStepsMillis);
        return flag;
    }
}
