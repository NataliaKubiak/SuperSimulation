package field;

import entities.Cell;
import entities.Entity;

import java.util.HashMap;

public class FieldRenderer {

    private HashMap<Cell, Entity> field;
    private final int FIELD_SIZE;

    public FieldRenderer(HashMap<Cell, Entity> field, int fieldSize) {
        this.field = field;
        FIELD_SIZE = fieldSize;
    }

    public void renderField() {

        for (int y = 0; y < FIELD_SIZE; y++) {
            for (int x = 0; x < FIELD_SIZE; x++) {
                Entity entity = field.get(new Cell(x, y));

                    System.out.print(renderIcon(entity.getIcon()));
            }
            System.out.println();
        }
    }

    public void moveCursorToStart() {
        for (int i = 0; i < FIELD_SIZE; i++) {
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
            case "emptySpot": return ". ";
            default: return "X ";
        }
    }
}
