package field;

import entities.*;

import java.util.HashMap;
import java.util.Random;

public class InitActions extends Actions {

    private HashMap<Cell, Entity> field;
    private final int FIELD_SIZE;
    private final int ENTITIES_AMOUNT;

    public InitActions(HashMap<Cell, Entity> field, int FIELD_SIZE, int ENTITIES_AMOUNT) {
        this.field = field;
        this.FIELD_SIZE = FIELD_SIZE;
        this.ENTITIES_AMOUNT = ENTITIES_AMOUNT;
    }

    public void start() {
        generateEmptyMap();
        addEntities();
    }

    private void generateEmptyMap() {
        for (int y = 0; y < FIELD_SIZE; y++) {
            for (int x = 0; x < FIELD_SIZE; x++) {
                Cell cell = new Cell(x, y);
                field.put(cell, new EmptySpot());
            }
        }
    }

    private void addEntities() {

        Random random = new Random();

        for (int i = 0; i < ENTITIES_AMOUNT; i++) {
            int x = random.nextInt(FIELD_SIZE);
            int y = random.nextInt(FIELD_SIZE);
            Cell cell = new Cell(x, y);

            int index = random.nextInt(5);
            switch (index) {
                case 0:
                    field.put(cell, new Rock());
                    break;
                case 1:
                    field.put(cell, new Tree());
                    break;
                case 2:
                    field.put(cell, new Grass());
                    break;
                case 3:
                    field.put(cell, new Predator(cell));
                    break;
                case 4:
                    field.put(cell, new Herbivore(cell));
                    break;
            }
        }
    }
}
