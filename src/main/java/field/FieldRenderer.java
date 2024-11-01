package field;

import entities.Cell;
import entities.Entity;

import java.util.HashMap;

public class FieldRenderer {

    private static final String ANSI_UP_ONE_LINE = "\u001B[A";
    private static final String BUNNY_EMOJI = "\ud83d\udc30";
    private static final String FACE_WITH_SWEAT_EMOJI = "\uD83D\uDE05";
    private static final String TIGER_EMOJI = "\ud83d\udc2f";
    private static final String GRASS_EMOJI = "\ud83c\udf31";
    private static final String TREE_EMOJI = "\uD83C\uDF33";
    private static final String ROCK_EMOJI = "\uD83E\uDEA8";
    private static final String PAW_STEPS_EMOJI = "\uD83D\uDC3E";
    private static final String EMPTY_SPOT = ". ";

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
            System.out.print(ANSI_UP_ONE_LINE);
        }
    }

    private String renderIcon(String iconName) {
        switch (iconName) {
            case "bunny": return BUNNY_EMOJI;
            case "survival": return FACE_WITH_SWEAT_EMOJI;
            case "tiger": return TIGER_EMOJI;
            case "grass": return GRASS_EMOJI;
            case "tree": return TREE_EMOJI;
            case "rock": return ROCK_EMOJI;
            case "pawSteps": return PAW_STEPS_EMOJI;
            case "emptySpot": return EMPTY_SPOT;
            default: return "X ";
        }
    }
}
