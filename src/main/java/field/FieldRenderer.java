package field;

import entities.Coordinates;
import entities.Entity;

public class FieldRenderer {

    private final String ANSI_UP_ONE_LINE = "\u001B[A";
    private final String BUNNY_EMOJI = "\ud83d\udc30";
    private final String FACE_WITH_SWEAT_EMOJI = "\uD83D\uDE05";
    private final String TIGER_EMOJI = "\ud83d\udc2f";
    private final String GRASS_EMOJI = "\ud83c\udf31";
    private final String TREE_EMOJI = "\uD83C\uDF33";
    private final String ROCK_EMOJI = "\uD83E\uDEA8";
    private final String PAW_STEPS_EMOJI = "\uD83D\uDC3E";
    private final String GROUND = ". ";

    private WorldField field;
    private final int fieldSize;

    public FieldRenderer(WorldField field) {
        this.field = field;
        fieldSize = field.getSize();
    }

    public void renderField() {
        for (int y = 0; y < fieldSize; y++) {
            for (int x = 0; x < fieldSize; x++) {
                Entity entity = field.getEntity(new Coordinates(x, y));

                    System.out.print(renderIcon(entity.getIcon()));
            }
            System.out.println();
        }
    }

    public void renderChangesOnField() {
        moveCursorToStart();
        renderField();
    }

    private void moveCursorToStart() {
        for (int i = 0; i < fieldSize; i++) {
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
            case "ground": return GROUND;
            default: return "X ";
        }
    }
}
