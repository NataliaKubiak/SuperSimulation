package main;

import field.Simulation;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Simulation simulation = new Simulation();
        simulation.start();

        boolean flag = true;
        while (flag) {

            flag = simulation.nextTurn();
        }
        simulation.stop();
    }
}
