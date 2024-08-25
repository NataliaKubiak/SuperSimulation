package field;

import entities.Coordinates;
import entities.Entity;

import java.util.HashMap;

public class MapRenderer {

    private HashMap<Coordinates, Entity> map;
    private final int MAP_SIZE;

    public MapRenderer(HashMap<Coordinates, Entity> map, int mapSize) {
        this.map = map;
        MAP_SIZE = mapSize;
    }

    public void renderMap() {

        for (int x = 0; x < MAP_SIZE; x++) {
            for (int y = 0; y < MAP_SIZE; y++) {
                Entity entity = map.get(new Coordinates(x, y));

                if (entity == null) {
                    System.out.print("..");
                } else {
                    System.out.print(entity.getIcon());
                }
            }
            System.out.println();
        }
    }

    public void moveCursorToStart() {

        for (int i = 0; i < MAP_SIZE; i++) {
            System.out.print("\u001B[A");
        }
    }
}
