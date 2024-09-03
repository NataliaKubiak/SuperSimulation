package main;

import entities.Cell;
import entities.Entity;
import entities.StaticObjects;
import field.Field;
import field.SearchService;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Field field = new Field();
        field.startSimulation();
//
//        while (true) {
//
//            Thread.sleep(1500);
//            map.nextTurn();
//        }

        HashMap<Cell, Entity> map = field.getMap();
        SearchService searchService = new SearchService(map);
        HashMap<Cell, Entity> filteredMap = searchService.filterMap();

        filteredMap.forEach((k, v) -> {
            if (v instanceof StaticObjects) {
                throw new IllegalArgumentException();
            }
            System.out.println("Coordinates: " + k + "\nEntity: " + v + "\n");
        });
    }
}
