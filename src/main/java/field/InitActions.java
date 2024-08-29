package field;

import entities.*;

import java.util.HashMap;
import java.util.Random;

public class InitActions extends Actions {

    private HashMap<Cell, Entity> map;
    private final int SIZE;
    private final int ENTITIES_AMOUNT;

    public InitActions(HashMap<Cell, Entity> map, int SIZE, int ENTITIES_AMOUNT) {
        this.map = map;
        this.SIZE = SIZE;
        this.ENTITIES_AMOUNT = ENTITIES_AMOUNT;
    }

    public void start() {
        generateEmptyMap();
        addEntities();
    }

    private void generateEmptyMap() {
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                Cell cell = new Cell(x, y, SIZE);
                cell.generateNeighbourCells();
                map.put(cell, null);
                System.out.println("-".repeat(30));
                System.out.println("Coordinates: x = " + x + " y = " + y);
                System.out.println("Neighbour cells: " + cell.getNeighbourCells());
            }
        }
    }

    private void addEntities() {

        Random random = new Random();

        for (int i = 0; i < ENTITIES_AMOUNT; i++) {
            int x = random.nextInt(SIZE + 1);
            int y = random.nextInt(SIZE + 1);
            Cell cell = new Cell(x, y);

            int index = random.nextInt(5);
            switch (index) {
                case 0:
                    map.put(cell, new Rock(cell));
                    break;
                case 1:
                    map.put(cell, new Tree(cell));
                    break;
                case 2:
                    map.put(cell, new Grass(cell));
                    break;
                case 3:
                    map.put(cell, new Predator(cell));
                    break;
                case 4:
                    map.put(cell, new Herbivore(cell));
                    break;
            }
        }
    }
}
