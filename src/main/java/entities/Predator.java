package entities;

public class Predator extends Creature {

    protected int attackPower;

    public Predator(Coordinates coordinates) {
        super("\ud83d\udc2f",
                coordinates,
                2,
                100);
        this.attackPower = 5;
    }

    @Override
    public void makeMove() {
        int newY = coordinates.getY() + speed;
        coordinates.setY(newY);
    }
}
