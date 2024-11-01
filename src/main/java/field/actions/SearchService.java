package field.actions;

import entities.*;
import field.WorldField;

import java.util.*;

public class SearchService extends Actions {

    private Coordinates startCoordinates;
    private Coordinates endCoordinates = null;
    private HashMap<Coordinates, Entity> filteredField = new HashMap<>();

    private Set<Coordinates> visitedCells = new HashSet<>();
    private Queue<Coordinates> cellsQueue = new LinkedList<>();

    private HashMap<Coordinates, Coordinates> childParentMap = new HashMap<>();
    private LinkedList<Coordinates> path = new LinkedList<>();

    public SearchService(WorldField field, Coordinates startCoordinates) {
        super(field);
        this.startCoordinates = startCoordinates;
    }

    public List<Coordinates> findPathToGoalObject() {
        runSearch();
        return makePathIfFoundGoalObj();
    }

    private void runSearch() {
        filteredField = field.filterFieldByInputObject(startCoordinates);
        visitedCells.add(startCoordinates);
        cellsQueue.add(startCoordinates);

        while (!cellsQueue.isEmpty()) {
            Coordinates inspectingCoordinates = cellsQueue.poll();

            if (isCellContainsGoalObject(inspectingCoordinates)) {
                endCoordinates = inspectingCoordinates;
                break;
            } else {
                exploreNeighbourCells(inspectingCoordinates);
            }
        }
    }

    private boolean isCellContainsGoalObject(Coordinates inspectingCell) {
        Entity entity = field.getEntity(startCoordinates);

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

    private void exploreNeighbourCells(Coordinates inspectingCell) {
        int startX = inspectingCell.getX();
        int startY = inspectingCell.getY();

        Coordinates coordinatesTop = new Coordinates(startX, loopField(startY-1));
        Coordinates coordinatesBottom = new Coordinates(startX, loopField(startY+1));
        Coordinates coordinatesRight = new Coordinates(loopField(startX+1), startY);
        Coordinates coordinatesLeft = new Coordinates(loopField(startX-1), startY);
        Set<Coordinates> possibleNeighbours = new HashSet<>(Arrays.asList(coordinatesTop, coordinatesBottom, coordinatesRight, coordinatesLeft));

        for (Coordinates neighbourCoordinates : possibleNeighbours) {
            if (filteredField.containsKey(neighbourCoordinates) && !visitedCells.contains(neighbourCoordinates)) {
                childParentMap.put(neighbourCoordinates, inspectingCell);
                visitedCells.add(neighbourCoordinates);
                cellsQueue.add(neighbourCoordinates);
            }
        }
    }

    private List<Coordinates> makePathIfFoundGoalObj() {
        if (endCoordinates != null) {
            return recreatePath(endCoordinates);
        }
        return null;
    }

    private List<Coordinates> recreatePath(Coordinates coordinates) {

        if (!coordinates.equals(startCoordinates)) {
            path.addFirst(coordinates);
            Coordinates previousCoordinates = childParentMap.get(coordinates);
            recreatePath(previousCoordinates);
        }
        return path;
    }
}
