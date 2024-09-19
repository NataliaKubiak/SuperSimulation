package field;

import entities.*;

import java.util.*;
import java.util.stream.Collectors;

public class SearchService extends Actions {

    //startCell будет перемещаться когда мы берем соседа - тогда сосед становится startCell??? или нам лучше хранить всегда отправную точку?
    private Cell startCell;
    private HashMap<Cell, Entity> filteredField = new HashMap<>();
    private Queue<Cell> neighbours = new LinkedList<>();
    private Set<Cell> checkedCells = new HashSet<>();

    public SearchService(HashMap<Cell, Entity> field, int FIELD_SIZE, Cell startCell) {
        super(field, FIELD_SIZE);
        this.startCell = startCell;
    }

    public void runSearch() {

        filterObjectsOnField();
        generateNeighbourCells();

        //это надо делать в цикле чтобы постоянно убирались добавленные соседи
        Cell oneNeighbour = neighbours.poll();
        checkIfCellContainsGoalObject(oneNeighbour);
    }

    //TODO норм ли это, если методы void и мы модифицируем поля или лучше сделать, чтобы методы возращали какие-то значения?

    private void filterObjectsOnField() {
        Creature creature = (Creature) field.get(startCell);

        if (creature instanceof Predator) {
            filteredField = field.entrySet()
                    .stream()
                    .filter(entry -> (entry.getValue() instanceof EmptySpot) ||
                            (entry.getValue() instanceof Herbivore))
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            Map.Entry::getValue,
                            (e1, e2) -> e1,
                            HashMap::new
                    ));
        } else {
            filteredField = field.entrySet()
                    .stream()
                    .filter(entry -> (entry.getValue() instanceof EmptySpot) ||
                            (entry.getValue() instanceof Grass))
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            Map.Entry::getValue,
                            (e1, e2) -> e1,
                            HashMap::new
                    ));
        }
    }

    private void generateNeighbourCells() {
        int startX = startCell.getX();
        int startY = startCell.getY();

        Cell cellTop = new Cell(startX, loopField(startY-1));
        Cell cellBottom = new Cell(startX, loopField(startY+1));
        Cell cellRight = new Cell(loopField(startX+1), startY);
        Cell cellLeft = new Cell(loopField(startX-1), startY);
        Set<Cell> possibleNeighbours = new HashSet<>(Arrays.asList(cellTop, cellBottom, cellRight, cellLeft));

        for (Cell cell : possibleNeighbours) {
            if (filteredField.containsKey(cell) && !checkedCells.contains(cell)) {
                neighbours.add(cell);
            }
        }
    }

    private boolean checkIfCellContainsGoalObject(Cell neighbourCell) {

        Entity entity = field.get(startCell);

        if (entity instanceof Predator) {
            if (filteredField.get(neighbourCell) instanceof Herbivore) {
                return true;
            } else {
                return false;
            }
        } else if (entity instanceof Herbivore) {
            if (filteredField.get(neighbourCell) instanceof Grass) {
                return true;
            } else {
                return false;
            }
        }
        //default value
        return false;
    }

}
