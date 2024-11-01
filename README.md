
# Super Simulation Project

Welcome to the **Super Simulation Project**! This repository contains a program designed to let you watch emojis chase each other across the screen when you're too tired to do anything else. It also serves as a way for me to learn OOP program design. 🤓

This project is built with Java 17 and Gradle.

## Table of Contents

1. [Project Description](#project-description)
2. [Demo Video](#demo-video)
3. [Getting Started](#getting-started)
4. [Running the Project](#running-the-project)
5. [Project Structure](#project-structure)


## Project Description

**Simulation Project** is a turn-based 2D world simulation featuring herbivores 🐰 and predators 🐯. The environment includes resources like grass 🌱, which herbivores 🐰 feed on, as well as static objects such as trees 🌳 and rocks 🪨, which creatures cannot interact with.

Each time the simulation starts, the positions of all creatures and static objects are randomized. Creatures take turns moving across the map, guided by a Breadth-First Search Algorithm to find the shortest path to their respective targets: predators 🐯 pursue herbivores 🐰, while herbivores 🐰 seek out grass 🌱. Paths to targets are marked with “🐾” symbols each turn. At the end of each round, these paths are cleared, and new grass 🌱 and herbivores 🐰 appear on the map.

Predators 🐯 attack herbivores 🐰 upon reaching them. After the first attack, a herbivore’s 🐰 icon changes to “😅”, indicating it is injured. If attacked a second time, the herbivore 🐰 is removed from the map.

## Demo Video

Watch a short demonstration of the project in action below:

![Screen Recording 2024-11-01 at 18 40 41](https://github.com/user-attachments/assets/68ba5e5d-f2c8-45e7-9d8b-3dcc17c65720)

## Getting Started

### Prerequisites

Ensure you have the following installed:

- Java Development Kit (JDK) 17 or later
- Gradle

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/NataliaKubiak/SuperSimulation.git
   cd SuperSimulation
   ```

2. Assemble the Project (if using the Gradle wrapper):
   ```bash
   ./gradlew build
   ```
   Alternatively, you can open the `build.gradle` file in your IDE (like IntelliJ IDEA or Eclipse). From there, you can run the `jar` task to build the project.

## Running the Project

   ```bash
   java -jar build/libs/SuperSimulation-1.0-SNAPSHOT.jar
   ```

## Project Structure

![Screenshot 2024-11-01 at 18 23 43](https://github.com/user-attachments/assets/effb5577-92c8-4e96-8a9a-da1249f0b647)

![Screenshot 2024-11-01 at 18 34 16](https://github.com/user-attachments/assets/d9b88f15-bc9c-42d3-8611-944dc4b5e08d)

![Screenshot 2024-11-01 at 18 33 36](https://github.com/user-attachments/assets/817a9af8-7b9b-4eb9-aefd-1baf9e77addc)
