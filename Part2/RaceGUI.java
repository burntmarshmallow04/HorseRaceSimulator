package Part2;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RaceGUI {
    private int raceLength;
    private ArrayList<HorseGUI> horses;
    private JPanel racePanel;
    private ArrayList<JLabel> laneLabels;
    private Timer raceTimer;
    private long raceStartTime; // To track the start time of the race
    private String weatherCondition;
    private JLabel weatherLabel;
    private JFrame parentFrame;

    public RaceGUI(JFrame parentFrame, int raceLength, int numHorses, String weather) {
        this.parentFrame = parentFrame;
        this.raceLength = raceLength;
        this.horses = new ArrayList<>();
        this.laneLabels = new ArrayList<>();
        this.weatherCondition = weather;
    
        racePanel = new JPanel();
        racePanel.setLayout(new BorderLayout());
    
        //wether and lane panel
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

    
        //weather label
        weatherLabel = new JLabel("Weather: " + getWeatherIcon(), SwingConstants.CENTER);
        weatherLabel.setFont(new Font("Arial", Font.PLAIN, 18)); // Big emoji
        centerPanel.add(weatherLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        //lane panel
        for (int i = 0; i < numHorses; i++) {
            JLabel laneLabel = new JLabel("Lane " + (i + 1) + ": ");
            laneLabel.setFont(new Font("Arial", Font.PLAIN, 17));
            laneLabels.add(laneLabel);
            centerPanel.add(laneLabel);
        }
    
        JPanel centerWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 20));
        centerWrapper.add(centerPanel);
        racePanel.add(centerWrapper, BorderLayout.CENTER);
    
        //statistics and reset buttons
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
    
        //statistics button
        JButton showStatsButton = new JButton("Show Horse Statistics");
        showStatsButton.setFont(new Font("Arial", Font.PLAIN, 16));
        showStatsButton.addActionListener(e -> showStatistics());
        bottomPanel.add(showStatsButton); 
        //reset button
        JButton resetButton = new JButton("Reset Race");
        resetButton.setFont(new Font("Arial", Font.PLAIN, 16));
        resetButton.addActionListener(e -> resetRace());
        bottomPanel.add(resetButton);
        //start button
        JButton startButton = new JButton("Start Race");
        startButton.setFont(new Font("Arial", Font.PLAIN, 16));
        startButton.addActionListener(e -> startRace());
        bottomPanel.add(startButton);

        racePanel.add(bottomPanel, BorderLayout.SOUTH);
    }
    

    public void addHorse(HorseGUI horse) {
        horses.add(horse);
    }

    public void startRace() {
        for (HorseGUI horse : horses) {
            horse.goBackToStart();
        }
        raceStartTime = System.currentTimeMillis(); // Record the start time of the race
        raceTimer = new Timer(100, e -> updateRace());
        raceTimer.start();
    }


    private void updateRace() {
        for (HorseGUI horse : horses) {
            moveHorse(horse);
        }
        updateRaceDisplay();
    
        for (HorseGUI horse : horses) {
            if (raceWonBy(horse)) {
                raceTimer.stop();
                finishRace(horse);  // Record the finish time when the race is won
                announceWinner(horse);
                return;
            }
        }
    
        if (allHorsesFallen()) {
            raceTimer.stop();
            announceNoWinner();
        }
    }    

    private void moveHorse(HorseGUI theHorse) {
        if (!theHorse.hasFallen()) {
            double moveChance = theHorse.getConfidence();
            double fallChance = 0.05 * theHorse.getConfidence() * theHorse.getConfidence();
    
            if ("Rainy".equals(weatherCondition)) {
                moveChance *= 0.8;  // 20% slower
                fallChance *= 1.2;  // 20% more likely to fall
            } else if ("Snowy".equals(weatherCondition)) {
                moveChance *= 0.6;  // 40% slower
                fallChance *= 1.5;  // 50% more likely to fall
            }
        
            if (Math.random() < moveChance) {
                theHorse.moveForward();
            }
            if (Math.random() < fallChance) {
                theHorse.fall();
            }
        }
    }

    private boolean raceWonBy(HorseGUI theHorse) {
        return theHorse.getDistanceTravelled() == raceLength;
    }

    private boolean allHorsesFallen() {
        for (HorseGUI horse : horses) {
            if (!horse.hasFallen()) {
                return false;
            }
        }
        return true;
    }

    private void updateRaceDisplay() {
        for (int i = 0; i < horses.size(); i++) {
            HorseGUI horse = horses.get(i);
            laneLabels.get(i).setText("Lane " + (i + 1) + ": " + getLaneDisplay(horse));
        }
    }

    private String getLaneDisplay(HorseGUI horse) {
        StringBuilder laneDisplay = new StringBuilder("|");
        for (int i = 0; i < horse.getDistanceTravelled(); i++) {
            laneDisplay.append(" ");
        }
        if (horse.hasFallen()) {
            laneDisplay.append("⛌");
        } else {
            laneDisplay.append(horse.getSymbol());
        }
        for (int i = horse.getDistanceTravelled(); i < raceLength; i++) {
            laneDisplay.append(" ");
        }
        laneDisplay.append("|  Confidence: (" + String.format("%.2f", horse.getConfidence()) + ")");
        return laneDisplay.toString();
    }

    public JPanel getRacePanel() {
        return racePanel;
    }

    private String getWeatherIcon() {
        switch (weatherCondition) {
            case "Sunny":
                return "Sunny " + "\u2600"; 
            case "Rainy":
                return "Rainy " + "\u26C6";
            case "Snowy":
                return "Snowy " + "\u2744";
            default:
                return "Sunny " + "\u2600"; //sunny by default
        }
    }

    private void announceWinner(HorseGUI winner) {
        JOptionPane.showMessageDialog(parentFrame, "And the winner is... " + winner.getName() + "!");
        winner.incrementWins();  // Increment the win count for the winner
        winner.setFinishTime(System.currentTimeMillis() - raceStartTime);
    }

    private void announceNoWinner() {
        JOptionPane.showMessageDialog(parentFrame, "All horses have fallen! There are no winners!");
    }

    private void resetRace() {
        // Stop the race timer if it's running
        if (raceTimer != null && raceTimer.isRunning()) {
            raceTimer.stop();
        }

        // Reset each horse to the starting position
        for (HorseGUI horse : horses) {
            horse.goBackToStart();
            horse.setFallen(false); 
            horse.setFinishTime(0);  
            horse.setDistanceTravelled(0);
            horse.setConfidence(0.5);
        }

        // Update the race display to reflect the reset state
        updateRaceDisplay();
    }

    public void finishRace(HorseGUI horse) {
        long raceDuration = System.currentTimeMillis() - raceStartTime;  // Get the duration in milliseconds
        horse.setFinishTime(raceDuration);  // Set the finish time
    }
    

    private void showStatistics() {
        StringBuilder stats = new StringBuilder();
    
        for (HorseGUI horse : horses) {
            // Assuming HorseGUI has methods to get these values:
            double speed = horse.getSpeed();  // You may need to calculate this based on distance/time
            double finishTime = horse.getFinishTime();  // You may need to track the finish time
            double wins = horse.getWins();
    
            // Append individual stats
            stats.append(horse.getName()).append(":\n")
                .append("Average Speed: ").append(String.format("%.2f", speed)).append(" m/s\n")
                .append("Finish Time: ").append(String.format("%.2f", finishTime / 1000.0)).append(" seconds\n") // if time is in ms
                .append("Wins: ").append((int)wins).append("\n\n")
                .append("Win Rate: ").append(String.format("%.2f", (wins / horses.size()) * 100)).append("%\n\n");
        }
    
        // Display the statistics in a message dialog
        JOptionPane.showMessageDialog(parentFrame, stats.toString(), "Horse Statistics", JOptionPane.INFORMATION_MESSAGE);
    }
}
