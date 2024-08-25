package entities;

public abstract class Entity {

    protected String icon;
    protected int id;
    protected Coordinates coordinates;

    private static int nextId = 1;

    public Entity(String icon,
                  Coordinates coordinates) {
        this.icon = icon;
        this.coordinates = coordinates;
        this.id = nextId++;
    }

    public String getIcon() {
        return icon;
    }

    public int getId() {
        return id;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }
}
