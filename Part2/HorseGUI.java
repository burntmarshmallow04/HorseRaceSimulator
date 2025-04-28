package Part2;

import java.util.HashMap;
import java.util.Map;

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
    private double finishTime;
    private int wins;
    private double speed;


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
        setEquipment(horseEquipment);
        this.finishTime = 0.0;
        this.wins = 0;
        setBreedSpeed();
    }

    private void setBreedSpeed() {
        // Define breed-to-speed mappings using a HashMap
        Map<String, Double> breedSpeedMap = new HashMap<>();
        breedSpeedMap.put("Thoroughbred", 12.5);
        breedSpeedMap.put("Arabian", 11.0);
        breedSpeedMap.put("Quarter Horse", 10.5);
        breedSpeedMap.put("Standardbred", 10.0);  // Example for another breed
        breedSpeedMap.put("Mongolian", 9.5);  // Another example breed

        // Set the horse's speed based on the breed
        this.speed = breedSpeedMap.getOrDefault(breed, 10.0);  // Default speed if breed is not found
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

    public double getSpeed() {
        if (finishTime == 0) {
            return 0.0; 
        }
        return distance/(finishTime/1000.0); // Assuming time is in seconds
    }

    // Return the finishing time
    public double getFinishTime() {
        return finishTime;
    }

    // Increment the wins counter when the horse wins a race
    public void incrementWins() {
        this.wins++;
    }

    public int getWins() {
        return wins;
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

        Map<String, String> equipmentSymbolMap = new HashMap<>();
        equipmentSymbolMap.put("Saddle", "𓂬");
        equipmentSymbolMap.put("Horseshoe", "℧");
        equipmentSymbolMap.put("Hat", "𐚁");
        
        // Update the symbol based on the selected equipment
        this.equipment = equipmentSymbolMap.getOrDefault(equipment, "");
    }
    

    public void setWinConfidence(){
        confidence += 0.05;
    }

    public void setDistanceTravelled(int distanceTravelled) {
        this.distance = distanceTravelled;
    }

    public void setFinishTime(double finishTime) {
        this.finishTime = finishTime;
    }

    public void setFallen(boolean fallen) {
        this.fallen = fallen;
    }

    public HorseStatistics getStats() {
        return stats;
    }

    public void addStats(int raceLength, int finishingTime) {
        stats.addStats(raceLength, distance, finishingTime, fallen);
    }
}
