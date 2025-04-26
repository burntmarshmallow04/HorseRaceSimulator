package Part2;

import java.awt.image.BufferedImage;

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
    private char symbol;
    private int distance;
    private double confidence;
    private boolean fallen;
    private BufferedImage image;
    private BufferedImage fallenImage;
    private HorseStatistics stats;

    //Constructor of class Horse
    /**
     * Constructor for objects of class Horse
     */
    public HorseGUI(char horseSymbol, String horseName, double horseConfidence, BufferedImage horseImage, BufferedImage horseFallenImage)
    {
        this.image = horseImage;
        this.fallenImage = horseFallenImage;
        this.name = horseName;
        this.symbol = horseSymbol;
        this.confidence = horseConfidence;
        this.distance = 0;
        this.fallen = false;
        this.stats = new HorseStatistics();
    }
    
    //Other methods of class Horse
    public BufferedImage getImage(){
        return image;
    }

    public BufferedImage getFallenImage(){
        return fallenImage;
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
    
    public double getConfidence()
    {
        return confidence;
    }
    
    public int getDistanceTravelled()
    {
        return distance;
    }
    
    public String getName()
    {
        return name;
    }
    
    public char getSymbol()
    {
        return symbol;
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
    
    public void setSymbol(char newSymbol)
    {
        symbol = newSymbol;
    }

    public void setWinConfidence(){
        confidence += 0.05;
    }

    public HorseStatistics getStats() {
        return stats;
    }

    public void addStats(int raceLength, int finishingTime) {
        stats.addStats(raceLength, distance, finishingTime, fallen);
    }
}
