package field;

import entities.Cell;
import entities.Entity;

import java.util.*;

public class SearchService {

    private HashMap<Cell, Entity> map;
    private Cell startCell;

    public SearchService(HashMap<Cell, Entity> map) {
        this.map = map;
    }

    public void generateNeighbourCells() {
        int startX = startCell.getX();
        int startY = startCell.getY();
        Set<Cell> neighbourCells = new HashSet<>();

        Cell cellTop = new Cell(startX, startY-1);
        Cell cellBottom = new Cell(startX, startY+1);
        Cell cellRight = new Cell(startX+1, startY);
        Cell cellLeft = new Cell(startX-1, startY);
        Set<Cell> possibleNeighbours = new HashSet<>(Arrays.asList(cellTop, cellBottom, cellRight, cellLeft));

        //check if added cells in the map
//        for (Cell c : possibleNeighbours) {
//            if (c.getX() >= 0 && c.getX() < mapSize && c.getY() >= 0 && c.getY() < mapSize) {
//                neighbourCells.add(c);
//            }
//        }
    }

}
