package main;

import field.Field;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Field map = new Field();
        map.startSimulation();

        while (true) {

            Thread.sleep(1500);
            map.nextTurn();
        }
    }
}
