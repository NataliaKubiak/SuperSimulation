package field;

import entities.*;

import java.util.HashMap;

public class TurnActions extends Actions {

    private HashMap<Cell, Entity> map;

    public TurnActions(HashMap<Cell, Entity> map) {
        this.map = map;
    }

    public void oneStepForAllCreatures() {
        HashMap<Cell, Entity> tempMap = new HashMap<>();
        tempMap.putAll(map);

        //swap values
        //hhh.put(a, hhh.put(b, hhh.get(a)));
        for (Cell xy : tempMap.keySet()) {
            if (tempMap.get(xy) instanceof Predator predator) {
                predator.makeMove();
                Cell newXY = predator.getCoordinates();
                map.put(xy, map.put(newXY, map.get(xy)));
            } else if (tempMap.get(xy) instanceof Herbivore herbivore) {
                herbivore.makeMove();
                Cell newXY = herbivore.getCoordinates();
                map.put(xy, map.put(newXY, map.get(xy)));
            }
        }
    }
}
