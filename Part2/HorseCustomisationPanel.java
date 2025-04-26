package Part2;


import javax.swing.*;
import java.awt.*;

//This class is for customising the horse symbol, breed, colour and saddle
public class HorseCustomisationPanel extends JPanel {
    private SelectList symbolList;
    private SelectList breedList;
    private SelectList colourList;
    private SelectList saddleList;
    
    public HorseCustomisationPanel() {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        symbolList = new SelectList("Select Horse Symbol", new String[]{"\u265E", "\uD83D\uDC34", "\uD83E\uDD84"});
        breedList = new SelectList("Select Horse Breed", new String[]{"Arabian", "Thoroughbred", "Quarter Horse"});
        colourList = new SelectList("Select Horse Colour", new String[]{"Brown", "Black", "White"});
        saddleList = new SelectList("Select Saddle", new String[]{"No Saddle", "Blue Saddle", "Red Saddle"});

        add(symbolList);
        add(Box.createVerticalStrut(10));  // Add some vertical space between components
        add(breedList);
        add(Box.createVerticalStrut(10));
        add(colourList);
        add(Box.createVerticalStrut(10));
        add(saddleList);

    }

    public String getHorseAppearanceString() {
        String breed = (String) breedList.getSelectedItem();
        String symbol = (String) symbolList.getSelectedItem();
        String colour = (String) colourList.getSelectedItem();
        String saddle = (String) saddleList.getSelectedItem();
        String horseAppearanceString;

        if (breed.equals("Arabian")) {
            horseAppearanceString = "_arabian";
        } else if (breed.equals("Thoroughbred")){
            horseAppearanceString = "_thoroughbred";
        } else {
            horseAppearanceString = "_quarter_horse";
        }

        if (symbol.equals("\u265E")) {
            horseAppearanceString += "_knight";
        } else if (symbol.equals("\uD83D\uDC34")){
            horseAppearanceString += "_horse";
        } else {
            horseAppearanceString += "_unicorn";
        }

        if (colour.equals("Brown")) {
            horseAppearanceString += "_brown";
        } else if (colour.equals("Black")){
            horseAppearanceString += "_black";
        } else {
            horseAppearanceString += "_white";
        }   

        if (saddle.equals("No Saddle")) {
            horseAppearanceString += "_no_saddle";
        } else if (saddle.equals("Blue Saddle")){
            horseAppearanceString += "_blue_saddle";
        } else {
            horseAppearanceString += "_red_saddle";
        }

        return horseAppearanceString;
    }

    public String getSymbol() {
        return symbolList.getSelectedItem();
    }
    public String getBreed() {
        return breedList.getSelectedItem();
    }
    public String getColour() {
        return colourList.getSelectedItem();
    }
    public String getSaddle() {
        return saddleList.getSelectedItem();
    }
}
