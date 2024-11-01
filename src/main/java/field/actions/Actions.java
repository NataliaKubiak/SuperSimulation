package field.actions;

import field.WorldField;

public abstract class Actions {

    protected WorldField field;
    protected final int fieldSize;

    public Actions(WorldField field) {
        this.field = field;
        this.fieldSize = field.getSize();
    }

    protected int loopField(int x) {
        //base case
        if (x >= 0 && x < fieldSize) {
            return x;
        } else if (x < 0) {
            return loopField(fieldSize + x);
        } else {
            return loopField(x - fieldSize);
        }
    }
}
