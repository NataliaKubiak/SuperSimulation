package entities;

public class Herbivore extends Creature {

    public Herbivore(Coordinates coordinates) {
        super("\ud83d\udc30",
                coordinates,
                1,
                100);
    }

    @Override
    public void makeMove() {
        int newY = coordinates.getY() + speed;
        coordinates.setY(newY);
    }
}
