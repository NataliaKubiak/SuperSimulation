package entities;

public class EmptySpot extends Entity {

    public EmptySpot() {
        super("emptySpot");
    }

    @Override
    public String toString() {
        return "EmptySpot{" +
                "icon='" + icon + '\'' +
                ", id=" + id +
                '}';
    }
}
