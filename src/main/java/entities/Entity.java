package entities;

public abstract class Entity {

    protected String icon;
    protected int id;

    private static int nextId = 1;

    public Entity(String icon) {
        this.icon = icon;
        this.id = nextId++;
    }

    public String getIcon() {
        return icon;
    }

    public int getId() {
        return id;
    }
}
