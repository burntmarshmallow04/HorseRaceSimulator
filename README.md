Author: Phung Weng Yen
Date: 28 April 2025

# HorseRaceSimulator

This project simulates a horse race, where you can run the simulation in two modes: **text-based** and **graphical**. 

In the **text-based** mode, the race is simulated in the terminal. 
The **graphical** version provides a graphical user interface (GUI) that uses Swing and also features customisable races and horses, horse statistics updates.

---

## Project Setup Instructions

### Clone the repository

First, clone the repository to your local machine:

```
git clone https://github.com/burntmarshmallow04/HorseRaceSimulator.git
cd HorseRaceSimulator
```

## Dependencies 
This project requires Java JDK 8 or higher and JavaFX for the graphical simulation. Ensure that your Java JDK installation is properly configured on your PATH to use the `javac` and `java` commands from any terminal.
```
java -version
```

## Compiling and running the program
Navigate to the root directory of the project and run:
```
javac Part1/*.java Part2/*.java
```
## Running the text based version
```
java Part1.Main
```
This will compile the `Main.java` file and execute the compiled class.
## Running the graphical simulation
```
java Part2.MainGUI
```
This will compile the `MainGUI.java` file and execute the compiled class.

## Usage

**Track and Weather Customisation**: 
- Before starting the race, the users get to customise the track in terms of track shape, number of lanes and the weather condition. The number of lanes will affect the nunber of horses in the race whereas the weather condition affects the speed and risk of falling of the horses.

**Horse Customisation**:
- After customising the track and weather conditions, users can customise the horses in the race in terms of the horse name, symbol, breed, and equipment. The breed of the horses affect their initial speed whereas the equipmen would affect their confidence. 

**Racing Animation**: Animation of the horses moving along the track is displyed when the race starts.

**Finish Race**: After the race, the statistics of the horses incuding the average speed, finish time, wins and win rate is shown in a panel. Users can also reset and start the race again to few updated statistics including confidence which is displayed at the side of the track and updated in real-time. 


