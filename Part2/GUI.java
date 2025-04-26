package Part2;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Part1.Horse;
import Part1.Race;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GUI {
    private final JFrame frame;
    private final JPanel panel;

    private final double initialConfidence = 0.3;
    private final HorseGUI[] horses = new HorseGUI[3];
    private final String[] horseNames = {"Horse 1", "Horse 2", "Horse 3"};
    private final String[] horseColours = {"Brown", "Brown", "Brown"};
    private final String[] horseSaddles = {"No Saddle", "No Saddle", "No Saddle"};

    private int raceNummber = 1;
    private Race race;

    private JPanel racePanel;
    //private LanePanel[] lanePanels;
    private HorseDescription[] horseDescriptionPanels;
    private JLabel winnerLabel;
    private CustomButton startRaceButton;

    public GUI() {
        frame = new JFrame("Horse Race Simulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 700);
        frame.setResizable(false);
        frame.setVisible(true);

        panel = new JPanel(new GridLayout(0, 1));
        frame.add(panel);
    }

    public void HorseCustomisation(){
        frame.getContentPane().removeAll();  // Clear the current panel (if any)
        frame.repaint();
    
        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    
        // Create a new HorseCustomisationPanel to allow customization of horses
        HorseCustomisationPanel customisationPanel = new HorseCustomisationPanel();
    
        // Add the customisation panel to the main panel
        panel.add(customisationPanel);
    
        // Submit button to apply customisations
        JButton submitButton = new JButton("Next");
        submitButton.setPreferredSize(new Dimension(150, 40));
    
        submitButton.addActionListener(e -> {
            // Get the customisation values from the HorseCustomisationPanel
            String symbol = customisationPanel.getSymbol();
            String breed = customisationPanel.getBreed();
            String colour = customisationPanel.getColour();
            String saddle = customisationPanel.getSaddle();
    
            // Generate the horse appearance string
            String appearance = customisationPanel.getHorseAppearanceString();
    
            // Here, you would load the images for the horses based on the appearance string.
            BufferedImage horseImage = loadImageForAppearance(appearance);
            BufferedImage horseFallenImage = loadImageForFallenAppearance(appearance);
    
            // Create HorseGUI objects instead of Horse objects
            horses[0] = new HorseGUI(symbol.charAt(0), horseNames[0], initialConfidence, horseImage, horseFallenImage);
            horses[1] = new HorseGUI(symbol.charAt(1), horseNames[1], initialConfidence, horseImage, horseFallenImage);
            horses[2] = new HorseGUI(symbol.charAt(2), horseNames[2], initialConfidence, horseImage, horseFallenImage);
    
            // You might want to do something with the appearance string here, for example, set the image paths or other properties based on the appearance string.
    
            // Close the customisation panel
            frame.dispose();
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(submitButton);
    
        // Add the submit button to the panel
        panel.add(buttonPanel);

        // Add the main panel to the frame
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
    
    // Helper method to load the image for the horse's appearance
    private BufferedImage loadImageForAppearance(String appearance) {
        try {
            // You would load the image file based on the appearance string here
            // Replace the path with the actual image file path based on your project structure
            return ImageIO.read(new File("path_to_images/" + appearance + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    // Helper method to load the fallen image for the horse's appearance
    private BufferedImage loadImageForFallenAppearance(String appearance) {
        try {
            // You would load the fallen image file based on the appearance string here
            // Replace the path with the actual fallen image file path based on your project structure
            return ImageIO.read(new File("path_to_images/" + appearance + "_fallen.png"));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
    
}



