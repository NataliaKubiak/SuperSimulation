package main;

import field.Field;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Field field = new Field();
        field.startSimulation();

        boolean flag = true;
        while (flag) {

            flag = field.nextTurn();
        }
        field.stopSimulation();
    }
}
