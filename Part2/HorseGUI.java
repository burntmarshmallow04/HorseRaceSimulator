package Part2;

/**
 * Write a description of class Horse here.
 * 
 * @author Phung Weng Yen 
 * @version 1 
 */
public class HorseGUI
{
    //Fields of class Horse
    private String name;
    private String breed;
    private String symbol;
    private int distance;
    private double confidence;
    private boolean fallen;
    private String equipment;

    private HorseStatistics stats;

    //Constructor of class Horse
    /**
     * Constructor for objects of class Horse
     */
    public HorseGUI(String horseName, String horseSymbol, String horseBreed, String horseEquipment) {
        this.name = horseName;
        this.symbol = horseSymbol;
        this.breed = horseBreed;
        this.confidence = 0.5;
        this.distance = 0;
        this.fallen = false;
        this.stats = new HorseStatistics();
        this.equipment = horseEquipment;
    }
    

    public void fall()
    {
        fallen = true;

        if(confidence <0.0){
            confidence = 0.0;
        }else{
            confidence -= 0.1;
        }
    }
    
    public String getName()
    {
        return name;
    }

    public double getConfidence()
    {
        return confidence;
    }
    
    public int getDistanceTravelled()
    {
        return distance;
    }
    
    public String getBreed()
    {
        return breed;
    }
    
    public String getSymbol()
    {
        return symbol;
    }
    
    // public String getColour() {
    //     return colour;
    // }
    
    public String getEquipment() {
        return equipment;
    }
    
    public void goBackToStart()
    {
        distance = 0;
    }
    
    public boolean hasFallen()
    {
        return fallen;
    }

    public void moveForward()
    {
        distance++;
    }

    public void setConfidence(double newConfidence)
    {
        confidence = newConfidence;
    }
    
    public void setSymbol(String newSymbol)
    {
        symbol = newSymbol;
    }

    // public void setColour(String colour) {
    //     this.colour = colour;
    // }
    
    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }
    

    public void setWinConfidence(){
        confidence += 0.05;
    }

    public void setDistanceTravelled(int distanceTravelled) {
        this.distance = distanceTravelled;
    }

    public HorseStatistics getStats() {
        return stats;
    }

    public void addStats(int raceLength, int finishingTime) {
        stats.addStats(raceLength, distance, finishingTime, fallen);
    }
}
