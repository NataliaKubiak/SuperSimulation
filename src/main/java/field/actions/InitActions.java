package field.actions;

import entities.*;
import field.WorldField;

import java.util.Random;

public class InitActions extends Actions {

    public InitActions(WorldField field) {
        super(field);
    }

    public void start() {
        generateEmptyField();
        initEntities();
    }

    public void stop() {
        System.out.println("The simulation STOPPED coz there is no rabbits or grass left :'(");
    }

    private void generateEmptyField() {
        for (int y = 0; y < fieldSize; y++) {
            for (int x = 0; x < fieldSize; x++) {
                Coordinates coordinates = new Coordinates(x, y);
                field.placeEntity(coordinates, new Ground());
            }
        }
    }

    private void initEntities() {
        Random random = new Random();

        for (int i = 0; i < field.getEntitiesAmount(); i++) {
            int x = random.nextInt(fieldSize);
            int y = random.nextInt(fieldSize);
            Coordinates coordinates = new Coordinates(x, y);

            int index = random.nextInt(5);
            switch (index) {
                case 0:
                    field.placeEntity(coordinates, new Rock());
                    break;
                case 1:
                    field.placeEntity(coordinates, new Tree());
                    break;
                case 2:
                    field.placeEntity(coordinates, new Grass());
                    break;
                case 3:
                    field.placeEntity(coordinates, new Predator(coordinates));
                    break;
                case 4:
                    field.placeEntity(coordinates, new Herbivore(coordinates));
                    break;
            }
        }
    }
}
