package field;

import entities.Cell;
import entities.Entity;

import java.util.HashMap;

public abstract class Actions {

    protected HashMap<Cell, Entity> field;
    protected final int FIELD_SIZE;

    public Actions(HashMap<Cell, Entity> field, int FIELD_SIZE) {
        this.field = field;
        this.FIELD_SIZE = FIELD_SIZE;
    }

    protected int loopField(int x) {
        //base case
        if (x >= 0 && x < FIELD_SIZE) {
            return x;
        } else if (x < 0) {
            return loopField(FIELD_SIZE + x);
        } else {
            return loopField(x - FIELD_SIZE);
        }
    }
}
