package field;

import entities.Cell;
import entities.Entity;
import entities.StaticObjects;

import java.util.*;
import java.util.stream.Collectors;

public class SearchService {

    private HashMap<Cell, Entity> field;
    private int fieldSize;
    private Cell startCell;

    public SearchService(HashMap<Cell, Entity> field, int fieldSize, Cell startCell) {
        this.field = field;
        this.fieldSize = fieldSize;
        this.startCell = startCell;
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
        for (Cell c : possibleNeighbours) {
            if (c.getX() >= 0 && c.getX() < fieldSize && c.getY() >= 0 && c.getY() < fieldSize) {
                neighbourCells.add(c);
            }
        }
    }

    public HashMap<Cell, Entity> filterOutStaticObjects() {
        HashMap<Cell, Entity> filteredField = field.entrySet()
                .stream()
                .filter(entry -> !(entry.getValue() instanceof StaticObjects))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        HashMap::new
                ));
        return filteredField;
    }

}
