package main;

import field.Field;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Field field = new Field();
        field.startSimulation();

//        while (true) {
//
//            Thread.sleep(1500);
//            field.nextTurn();
//        }

        field.searchActions();

    }
}
