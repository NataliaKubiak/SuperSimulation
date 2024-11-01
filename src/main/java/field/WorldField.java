package field;

import entities.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WorldField {

    private HashMap<Coordinates, Entity> field = new HashMap<>();
    private final int fieldSize;
    private final int entitiesAmount;

    public WorldField(int fieldSize, int entitiesAmount) {
        this.fieldSize = fieldSize;
        this.entitiesAmount = entitiesAmount;
    }

    public int getSize() {
        return fieldSize;
    }

    public int getEntitiesAmount() {
        return entitiesAmount;
    }

    public void placeEntity(Coordinates coordinates, Entity entity) {
        field.put(coordinates, entity);
    }

    public Entity getEntity(Coordinates coordinates) {
        return field.get(coordinates);
    }

    public void moveEntity(Coordinates from, Coordinates to) {
        Entity entity = getEntity(from);

        removeEntity(from);
        placeEntity(to, entity);
    }

    public void removeEntity(Coordinates coordinates) {
        placeEntity(coordinates, new Ground());
    }

    public <T extends Entity> List<Coordinates> findAllCellsWith(Class<T> type) {
        return field.entrySet().stream()
                .filter(e -> type.isInstance(e.getValue()))
                .map(Map.Entry::getKey)
                .toList();
    }

    public List<Coordinates> findAllCreatures() {
        return Stream.concat(
                findAllCellsWith(Predator.class).stream(),
                findAllCellsWith(Herbivore.class).stream()
        ).toList();
    }

    public HashMap<Coordinates, Entity> filterField(Coordinates objectCoord) {
        Creature creature = (Creature) field.get(objectCoord);

        if (creature instanceof Predator) {
            return field.entrySet()
                    .stream()
                    .filter(entry -> (entry.getValue() instanceof Ground) ||
                            (entry.getValue() instanceof Herbivore) ||
                            (entry.getValue() instanceof PawSteps))
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            Map.Entry::getValue,
                            (e1, e2) -> e1,
                            HashMap::new
                    ));
        } else {
            return field.entrySet()
                    .stream()
                    .filter(entry -> (entry.getValue() instanceof Ground) ||
                            (entry.getValue() instanceof Grass) ||
                            (entry.getValue() instanceof PawSteps))
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            Map.Entry::getValue,
                            (e1, e2) -> e1,
                            HashMap::new
                    ));
        }
    }
}
