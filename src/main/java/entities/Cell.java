package entities;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Cell {

    private int x;
    private int y;
    private int mapSize;
    private Set<Cell> neighbourCells = new HashSet<>();

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Cell(int x, int y, int mapSize) {
        this.x = x;
        this.y = y;
        this.mapSize = mapSize;
    }

    //TODO there is a big chance to forget this method and have errors in search
    public void generateNeighbourCells() {
        Cell cellTop = new Cell(x, y-1);
        Cell cellBottom = new Cell(x, y+1);
        Cell cellRight = new Cell(x+1, y);
        Cell cellLeft = new Cell(x-1, y);
        Set<Cell> possibleNeighbours = new HashSet<>(Arrays.asList(cellTop, cellBottom, cellRight, cellLeft));

        for (Cell c : possibleNeighbours) {
            if (c.getX() >= 0 && c.getX() < mapSize && c.getY() >= 0 && c.getY() < mapSize) {
                neighbourCells.add(c);
            }
        }
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public Set<Cell> getNeighbourCells() {
        return neighbourCells;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cell that = (Cell) o;
        return y == that.y && x == that.x;
    }

    @Override
    public int hashCode() {
        int result = y;
        result = 31 * result + x;
        return result;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
