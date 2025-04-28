package Part1;

/**
 * Write a description of class Horse here.
 * 
 * @author Phung Weng Yen 
 * @version 1 
 */
public class Horse
{
    //Fields of class Horse
    private String name;
    private char symbol;
    private int distance;
    private double confidence;
    private boolean fallen;

    //Constructor of class Horse
    /**
     * Constructor for objects of class Horse
     */
    public Horse(char horseSymbol, String horseName, double horseConfidence)
    {
        this.symbol = horseSymbol;
        this.name = horseName;
        this.confidence = horseConfidence;
        this.distance = 0;
        this.fallen = false;

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
        setConfidence(confidence);
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
        if (newConfidence < 0.0 || newConfidence > 1.0) {
            throw new IllegalArgumentException("Confidence must be between 0.0 and 1.0");       
        }else{
            confidence = Math.round(newConfidence * 100.0) / 100.0;
        }
    }
    
    public void setSymbol(char newSymbol)
    {
        symbol = newSymbol;
    }

    public void setWinConfidence(){
        setConfidence(confidence+0.05);
    }

}
