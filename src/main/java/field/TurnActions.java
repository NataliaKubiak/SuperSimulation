package field;

import entities.Coordinates;
import entities.Creature;
import entities.Entity;

import java.util.HashMap;

public class TurnActions extends Actions {

    private HashMap<Coordinates, Entity> map;

    public TurnActions(HashMap<Coordinates, Entity> map) {
        this.map = map;
    }

    public void oneStepForAllCreatures() {
        HashMap<Coordinates, Entity> tempMap = new HashMap<>();
        tempMap.putAll(map);

        //swap values
        //hhh.put(a, hhh.put(b, hhh.get(a)));
        for (Coordinates xy : tempMap.keySet()) {
            if (tempMap.get(xy) instanceof Creature creature) {
                creature.makeMove();
                Coordinates newXY = creature.getCoordinates();
                map.put(xy, map.put(newXY, map.get(xy)));
            }
        }
    }
}
