package Part2;

import javax.swing.*;
import java.awt.*;

public class GUI {
    private final JFrame frame;
    private final JPanel panel;

    private final HorseGUI[] horses = new HorseGUI[4];
    private String selectedTrackShape;
    private RaceGUI race;
    
    public GUI() {
        frame = new JFrame("Horse Race Simulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 700);
        frame.setResizable(false);

        panel = new JPanel(new GridLayout(0, 1));
        frame.add(panel);
    }

    public void TrackAndWeatherCustomisation() {
        // Create a new panel for track and weather customizations
        JPanel trackAndWeatherPanel = new JPanel();
        trackAndWeatherPanel.setLayout(new GridLayout(3, 1, 10, 10));  // Layout for track and weather panels
        trackAndWeatherPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));  // Padding
    
        // Create Track Customization Panel and Weather Panel
        TrackCustomisationPanel trackCustomisationPanel = new TrackCustomisationPanel();
        WeatherPanel weatherPanel = new WeatherPanel();
    
        // Add the panels to the trackAndWeatherPanel
        trackAndWeatherPanel.add(trackCustomisationPanel);
        trackAndWeatherPanel.add(weatherPanel);
    
        // Submit button for track and weather customization
        JButton nextButton = new JButton("Next");
        nextButton.setPreferredSize(new Dimension(150, 40));
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(nextButton);
        trackAndWeatherPanel.add(buttonPanel);
    
        // Action for when the "Next" button is clicked
        nextButton.addActionListener(e -> {
            // Get the track customisation details
            String trackShape = trackCustomisationPanel.getTrackShape();
            String selectedWeather = weatherPanel.getWeatherCondition();
            int laneCount = trackCustomisationPanel.getLaneCount();
            selectedTrackShape = trackShape;
    
            // Clear the frame and setup the next step
            frame.getContentPane().removeAll();
            frame.revalidate();
            frame.repaint();
    
            // Call Race Customisation with the lane count dynamically updated
            RaceCustomisation(laneCount, selectedWeather);
        });
    
        // Add the panel to the frame and refresh the display
        frame.add(trackAndWeatherPanel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
        frame.setVisible(true);
    }

    public void RaceCustomisation(int laneCount, String selectedWeather) {
        frame.getContentPane().removeAll();  // Clear the current panel (if any)
        frame.repaint();
    
        // Clear and set up the main panel for horse customization only
        panel.removeAll();
        panel.setLayout(new BorderLayout()); 
        JPanel horseCustomisationPanel = new JPanel();
        horseCustomisationPanel.setLayout(new GridLayout(1, laneCount, 10, 10));  // Adjust grid based on selected lane count
        horseCustomisationPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));  // Padding
    
        // Create customization panels dynamically based on lane count
        for (int i = 0; i < laneCount; i++) {
            HorseCustomisationPanel customisationPanel = new HorseCustomisationPanel();
            horseCustomisationPanel.add(customisationPanel);
        }
    
        panel.add(horseCustomisationPanel, BorderLayout.CENTER);
    
        // Submit button for horse customization
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton nextButton = new JButton("Next");
        nextButton.setPreferredSize(new Dimension(150, 40));
        buttonPanel.add(nextButton);
    
        panel.add(buttonPanel, BorderLayout.SOUTH);
    
        // Action for when the "Next" button is clicked
        nextButton.addActionListener(e -> {
            // Initialize the horse objects based on the customized details
            for (int i = 0; i < laneCount; i++) {
                HorseCustomisationPanel panel = (HorseCustomisationPanel) horseCustomisationPanel.getComponent(i);
                String name = panel.getHorseName();
                String symbol = panel.getHorseSymbol();
                String breed = panel.getHorseBreed();
                String equipment = panel.getHorseEquipment();
    
                // Create HorseGUI objects dynamically for the selected lanes
                horses[i] = new HorseGUI(name, symbol, breed, equipment);
            }
    
            // Proceed to the next step: Track and Weather Customization
            frame.getContentPane().removeAll();
            frame.revalidate();
            frame.repaint();

            setupRacePanel(laneCount, selectedWeather);
        });

        // Add the panel to the frame and refresh the display
        frame.add(panel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    private void setupRacePanel(int laneCount, String selectedWeather) {
        race = new RaceGUI(frame,50, laneCount, selectedWeather);  // Example race length of 1000
        for (int i = 0; i < laneCount; i++) {
            race.addHorse(horses[i]);  // Add each horse to the RaceGUI
        }

        // Add the existing race panel to the frame (instead of creating a new one)
        frame.getContentPane().removeAll();
        frame.add(race.getRacePanel(), BorderLayout.CENTER);  // Use getRacePanel() to retrieve the pre-built race panel
        frame.revalidate();
        frame.repaint();

        // Start the race
        race.startRace();
    }
}
