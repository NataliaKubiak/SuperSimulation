package field.actions;

import entities.*;
import field.WorldField;

import java.util.List;
import java.util.Random;

public class RoundActions extends Actions {

    public RoundActions(WorldField field) {
        super(field);
    }

    public void addGrass(int amount) {
        Random random = new Random();
        List<Coordinates> allGroundCells = field.findAllCellsWith(Ground.class);

        for (int i = 0; i <= amount; i++) {
            int randomCellIndex = random.nextInt(allGroundCells.size());
            Coordinates randomGroundCell = allGroundCells.get(randomCellIndex);
            field.placeEntity(randomGroundCell, new Grass());
        }
    }

    public void addHerbivores(int amount) {
        Random random = new Random();
        List<Coordinates> allGroundCells = field.findAllCellsWith(Ground.class);

        for (int i = 0; i <= amount; i++) {
            int randomCellIndex = random.nextInt(allGroundCells.size());
            Coordinates randomGroundCell = allGroundCells.get(randomCellIndex);
            field.placeEntity(randomGroundCell, new Herbivore(randomGroundCell));
        }
    }

    public void clearPawSteps() {
        List<Coordinates> allPawCoordinates = field.findAllCellsWith(PawSteps.class);
        for (Coordinates coordinates : allPawCoordinates) {
            field.removeEntity(coordinates);
        }
    }
}
