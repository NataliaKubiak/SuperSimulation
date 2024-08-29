package entities;

public abstract class Entity {

    protected String icon;
    protected int id;
    protected Cell cell;

    private static int nextId = 1;

    public Entity(String icon,
                  Cell cell) {
        this.icon = icon;
        this.cell = cell;
        this.id = nextId++;
    }

    public String getIcon() {
        return icon;
    }

    public int getId() {
        return id;
    }

    public Cell getCoordinates() {
        return cell;
    }
}
