package field.actions;

import entities.*;

import java.util.*;
import java.util.stream.Collectors;

public class SearchService extends Actions {

    private Cell startCell;
    private Cell endCell = null;
    private HashMap<Cell, Entity> filteredField = new HashMap<>();

    private Set<Cell> visitedCells = new HashSet<>();
    private Queue<Cell> cellQueue = new LinkedList<>();

    private HashMap<Cell, Cell> childParentMap = new HashMap<>();
    private LinkedList<Cell> path = new LinkedList<>();

    public SearchService(HashMap<Cell, Entity> field, int FIELD_SIZE, Cell startCell) {
        super(field, FIELD_SIZE);
        this.startCell = startCell;
    }

    public List<Cell> findPathToGoalObject() {
        runSearch();
        return makePathIfFoundGoalObj();
    }

    private void runSearch() {
        filterObjectsOnField();
        visitedCells.add(startCell);
        cellQueue.add(startCell);

        while (!cellQueue.isEmpty()) {
            Cell inspectingCell = cellQueue.poll();

            if (isCellContainsGoalObject(inspectingCell)) {
                endCell = inspectingCell;
                break;
            } else {
                exploreNeighbourCells(inspectingCell);
            }
        }
    }

    private void filterObjectsOnField() {
        Creature creature = (Creature) field.get(startCell);

        if (creature instanceof Predator) {
            filteredField = field.entrySet()
                    .stream()
                    .filter(entry -> (entry.getValue() instanceof EmptySpot) ||
                            (entry.getValue() instanceof Herbivore) ||
                            (entry.getValue() instanceof PawSteps))
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
                            (entry.getValue() instanceof Grass) ||
                            (entry.getValue() instanceof PawSteps))
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            Map.Entry::getValue,
                            (e1, e2) -> e1,
                            HashMap::new
                    ));
        }
    }

    private boolean isCellContainsGoalObject(Cell inspectingCell) {
        Entity entity = field.get(startCell);

        if (entity instanceof Predator) {
            if (filteredField.get(inspectingCell) instanceof Herbivore) {
                return true;
            } else {
                return false;
            }
        } else if (entity instanceof Herbivore) {
            if (filteredField.get(inspectingCell) instanceof Grass) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    private void exploreNeighbourCells(Cell inspectingCell) {
        int startX = inspectingCell.getX();
        int startY = inspectingCell.getY();

        Cell cellTop = new Cell(startX, loopField(startY-1));
        Cell cellBottom = new Cell(startX, loopField(startY+1));
        Cell cellRight = new Cell(loopField(startX+1), startY);
        Cell cellLeft = new Cell(loopField(startX-1), startY);
        Set<Cell> possibleNeighbours = new HashSet<>(Arrays.asList(cellTop, cellBottom, cellRight, cellLeft));

        for (Cell neighbourCell : possibleNeighbours) {
            if (filteredField.containsKey(neighbourCell) && !visitedCells.contains(neighbourCell)) {
                childParentMap.put(neighbourCell, inspectingCell);
                visitedCells.add(neighbourCell);
                cellQueue.add(neighbourCell);
            }
        }
    }

    private List<Cell> makePathIfFoundGoalObj() {
        if (endCell != null) {
            return recreatePath(endCell);
        }
        return null;
    }

    private List<Cell> recreatePath(Cell cell) {

        if (!cell.equals(startCell)) {
            path.addFirst(cell);
            Cell previousCell = childParentMap.get(cell);
            recreatePath(previousCell);
        }
        return path;
    }
}
