package field;

import entities.*;

import java.util.HashMap;
import java.util.Random;

public class InitActions extends Actions {

    private HashMap<Coordinates, Entity> map;
    private final int SIZE;
    private final int ENTITIES_AMOUNT;

    public InitActions(HashMap<Coordinates, Entity> map, int SIZE, int ENTITIES_AMOUNT) {
        this.map = map;
        this.SIZE = SIZE;
        this.ENTITIES_AMOUNT = ENTITIES_AMOUNT;
    }

    public void start() {
        generateEmptyMap();
        addEntities();
    }

    private void generateEmptyMap() {
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                map.put(new Coordinates(x, y), null);
            }
        }
    }

    private void addEntities() {

        Random random = new Random();

        for (int i = 0; i < ENTITIES_AMOUNT; i++) {
            int x = random.nextInt(SIZE + 1);
            int y = random.nextInt(SIZE + 1);
            Coordinates coordinates = new Coordinates(x, y);

            int index = random.nextInt(5);
            switch (index) {
                case 0:
                    map.put(coordinates, new Rock(coordinates));
                    break;
                case 1:
                    map.put(coordinates, new Tree(coordinates));
                    break;
                case 2:
                    map.put(coordinates, new Grass(coordinates));
                    break;
                case 3:
                    map.put(coordinates, new Predator(coordinates));
                    break;
                case 4:
                    map.put(coordinates, new Herbivore(coordinates));
                    break;
            }
        }
    }
}
