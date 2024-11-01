package field.actions;

import entities.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class RoundActions extends Actions {

    public RoundActions(HashMap<Cell, Entity> field, int FIELD_SIZE) {
        super(field, FIELD_SIZE);
    }

    public void addGrass(int amount) {
        Random random = new Random();
        List<Cell> emptySpotsCoord = findAllEmptySpots();

        for (int i = 0; i <= amount; i++) {
            int randomCellIndex = random.nextInt(emptySpotsCoord.size());
            Cell randomEmptyCell = emptySpotsCoord.get(randomCellIndex);
            field.put(randomEmptyCell, new Grass());
        }
    }

    public void addHerbivores(int amount) {
        Random random = new Random();
        List<Cell> emptySpotsCoord = findAllEmptySpots();

        for (int i = 0; i <= amount; i++) {
            int randomCellIndex = random.nextInt(emptySpotsCoord.size());
            Cell randomEmptyCell = emptySpotsCoord.get(randomCellIndex);
            field.put(randomEmptyCell, new Herbivore(randomEmptyCell));
        }
    }

    private List<Cell> findAllEmptySpots() {
        return field.entrySet().stream()
                .filter(e -> e.getValue() instanceof EmptySpot)
                .map(Map.Entry::getKey)
                .toList();
    }

    public void clearPawSteps() {
        for (Map.Entry<Cell, Entity> entry : field.entrySet()) {
            if (entry.getValue() instanceof PawSteps) {
                field.put(entry.getKey(), new EmptySpot());
            }
        }
    }
}
