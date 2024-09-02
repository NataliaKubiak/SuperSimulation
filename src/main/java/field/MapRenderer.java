package field;

import entities.Cell;
import entities.Entity;

import java.util.HashMap;

public class MapRenderer {

    private HashMap<Cell, Entity> map;
    private final int MAP_SIZE;

    public MapRenderer(HashMap<Cell, Entity> map, int mapSize) {
        this.map = map;
        MAP_SIZE = mapSize;
    }

    public void renderMap() {

        for (int y = 0; y < MAP_SIZE; y++) {
            for (int x = 0; x < MAP_SIZE; x++) {
                Entity entity = map.get(new Cell(x, y));

                if (entity == null) {
                    System.out.print("..");
                } else {
                    System.out.print(renderIcon(entity.getIcon()));
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

    private String renderIcon(String iconName) {
        switch (iconName) {
            case "bunny": return "\ud83d\udc30";
            case "tiger": return "\ud83d\udc2f";
            case "grass": return "\ud83c\udf31";
            case "tree": return "\uD83C\uDF33";
            case "rock": return "\uD83E\uDEA8";
            default: return "X";
        }
    }
}
