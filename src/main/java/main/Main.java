package main;

import field.Field;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Field field = new Field();
        field.startSimulation();

        while (true) {

            field.nextTurn();
        }

//        field.searchActions();
//        System.out.println("");
    }
}
