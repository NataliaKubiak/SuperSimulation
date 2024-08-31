package field;

import entities.Cell;
import entities.Entity;

import java.util.HashMap;

public class Field {

    private HashMap<Cell, Entity> map = new HashMap<>();
    private static final int ENTITIES_AMOUNT = 50;
    private static final int MAP_SIZE = 20;

    private InitActions initActions = new InitActions(map, MAP_SIZE, ENTITIES_AMOUNT);
    private TurnActions turnActions = new TurnActions(map);
    private MapRenderer renderer = new MapRenderer(map, MAP_SIZE);

    public void startSimulation() {
        initActions.start();
        renderer.renderMap();
    }

    public void nextTurn() {
        turnActions.oneStepForAllCreatures();
        renderer.moveCursorToStart();
        renderer.renderMap();
    }

}
