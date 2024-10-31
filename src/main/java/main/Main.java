package main;

import field.Field;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Field field = new Field();
        field.startSimulation();

        while (true) {

            field.nextTurn();
        }

//        System.out.println("\uD83D\uDE05");
    }
}
