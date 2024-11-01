package entities;

public abstract class Entity {

    protected String icon;

    public Entity(String icon) {
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }
}
