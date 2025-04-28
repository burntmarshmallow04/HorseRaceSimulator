package Part1;

public class Main {
    public static void main(String[] args) {
        Race myRace = new Race(30); 

        Horse h1 = new Horse('\u2658', "PIPPI LONGSTOCKING", 0.8);
        Horse h2 = new Horse('\u2655', "KOKOMO", 0.4);
        Horse h3 = new Horse('\u2660', "EL JEFE", 0.6);

        myRace.addHorse(h1, 1);
        myRace.addHorse(h2, 2);
        myRace.addHorse(h3, 3);

        myRace.startRace();
    }
}


