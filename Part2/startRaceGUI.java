package Part2;

import javax.swing.*;

import Part1.Horse;
import Part1.Race;

import java.awt.*;
import java.awt.event.*;

public class startRaceGUI {
    private Race myRace;  // Reference to the Race object
    private Horse h1, h2, h3;  // The horses in the race
    
    private JLabel lane1, lane2, lane3;  
    private JPanel trackPanel;

    public static void main(String[] args) {
        new startRaceGUI().GUI();
    }

    public void GUI() {
        // Set up the JFrame (main window)
        JFrame frame = new JFrame("Horse Race Simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);  // Larger frame size
        
        // Set up the panel and layout
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Track length input
        JLabel trackLengthLabel = new JLabel("Track Length:");
        JTextField trackLengthField = new JTextField("30"); // default value
        
        // Select number of lanes
        JLabel laneCountLabel = new JLabel("Select number of lanes:");
        JComboBox<Integer> laneSelect = new JComboBox<>(new Integer[]{1, 2, 3});

        // Horse symbol inputs
        JLabel horse1Label = new JLabel("Horse 1 Symbol:");
        JTextField horse1SymbolField = new JTextField("♘"); // default value
        JLabel horse2Label = new JLabel("Horse 2 Symbol:");
        JTextField horse2SymbolField = new JTextField("♕"); // default value
        JLabel horse3Label = new JLabel("Horse 3 Symbol:");
        JTextField horse3SymbolField = new JTextField("♤"); // default value

        // Start Race button
        JButton startButton = new JButton("Start Race");
        
        // Add components to the panel
        panel.add(trackLengthLabel);
        panel.add(trackLengthField);
        panel.add(laneCountLabel);
        panel.add(laneSelect);
        panel.add(horse1Label);
        panel.add(horse1SymbolField);
        panel.add(horse2Label);
        panel.add(horse2SymbolField);
        panel.add(horse3Label);
        panel.add(horse3SymbolField);
        panel.add(new JLabel());  // Placeholder
        panel.add(startButton);

        // Set up the track panel for displaying the race
        trackPanel = new JPanel(new GridLayout(3, 1));  // 3 lanes, 1 row per horse
        lane1 = new JLabel("Lane 1: " + horse1SymbolField.getText());
        lane2 = new JLabel("Lane 2: " + horse2SymbolField.getText());
        lane3 = new JLabel("Lane 3: " + horse3SymbolField.getText());
        trackPanel.add(lane1);
        trackPanel.add(lane2);
        trackPanel.add(lane3);

        // Add track panel to the frame
        frame.add(trackPanel, BorderLayout.CENTER);
        frame.add(panel, BorderLayout.SOUTH);

        // Button ActionListener to start the race
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get track length and horse symbols from the input fields
                int trackLength;
                try {
                    trackLength = Integer.parseInt(trackLengthField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter a valid number for track length.");
                    return;
                }
                
                String symbol1 = horse1SymbolField.getText();
                String symbol2 = horse2SymbolField.getText();
                String symbol3 = horse3SymbolField.getText();

                // Create horses with their symbols
                h1 = new Horse(symbol1.charAt(0), "PIPPI LONGSTOCKING", 0.8);
                h2 = new Horse(symbol2.charAt(0), "KOKOMO", 0.6);
                h3 = new Horse(symbol3.charAt(0), "EL JEFE", 0.7);
                
                // Create the race and add horses to the race
                myRace = new Race(trackLength);
                myRace.addHorse(h1, 1);
                myRace.addHorse(h2, 2);
                myRace.addHorse(h3, 3);
                
                // Start the race (it will run in a separate thread)
                startRaceInThread();
            }
        });

        frame.setVisible(true);
    }

    private void startRaceInThread() {
        // Use a separate thread for the race simulation to prevent blocking the GUI thread
        new Thread(() -> {
            myRace.startRace();
            // After the race ends, determine the winner and display the result
            String winner = determineWinner();
            // Update the GUI to show the winner
            SwingUtilities.invokeLater(() -> {
                JOptionPane.showMessageDialog(null, "The winner is: " + winner);
            });
        }).start();
    }

    private String determineWinner() {
        // Compare the positions of all horses to find the one with the greatest position
        Horse winnerHorse = h1;
        
        // Check horse 2
        if (h2.getDistanceTravelled() > winnerHorse.getDistanceTravelled()) {
            winnerHorse = h2;
        }
        
        // Check horse 3
        if (h3.getDistanceTravelled() > winnerHorse.getDistanceTravelled()) {
            winnerHorse = h3;
        }
        
        return winnerHorse.getName();  // Return the winner's name
    }
}
