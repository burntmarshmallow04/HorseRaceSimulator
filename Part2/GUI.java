package Part2;

import javax.swing.*;
import java.awt.*;

public class GUI {
    private final JFrame frame;
    private final JPanel panel;
    private HorseGUI[] horses;
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

    //customisation of track and weather
    public void TrackAndWeatherCustomisation() {
        JPanel trackAndWeatherPanel = new JPanel();
        trackAndWeatherPanel.setLayout(new GridLayout(3, 1, 10, 10));  // Layout for track and weather panels
        trackAndWeatherPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));  // Padding
    
        //track and weather panels
        TrackCustomisationPanel trackCustomisationPanel = new TrackCustomisationPanel();
        WeatherPanel weatherPanel = new WeatherPanel();
    
        trackAndWeatherPanel.add(trackCustomisationPanel);
        trackAndWeatherPanel.add(weatherPanel);
    
        JButton nextButton = new JButton("Next");
        nextButton.setPreferredSize(new Dimension(150, 40));
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(nextButton);
        trackAndWeatherPanel.add(buttonPanel);
    
        nextButton.addActionListener(e -> {
            // Get the track customisation details
            String trackShape = trackCustomisationPanel.getTrackShape();
            String selectedWeather = weatherPanel.getWeather();
            int laneCount = trackCustomisationPanel.getLaneCount();
            selectedTrackShape = trackShape;
    
            frame.getContentPane().removeAll();
            frame.revalidate();
            frame.repaint();
    
            RaceCustomisation(laneCount, selectedWeather);
        });
    
        frame.add(trackAndWeatherPanel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
        frame.setVisible(true);
    }

    //customisation of horses
    public void RaceCustomisation(int laneCount, String selectedWeather) {
        frame.getContentPane().removeAll();  // Clear the current panel (if any)
        frame.repaint();
    
        horses = new HorseGUI[laneCount];

        panel.removeAll();
        panel.setLayout(new BorderLayout()); 
        JPanel horseCustomisationPanel = new JPanel();
        horseCustomisationPanel.setLayout(new GridLayout(1, laneCount, 10, 10));  // Adjust grid based on selected lane count
        horseCustomisationPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));  // Padding
    
        //customisation panels for each horse
        for (int i = 0; i < laneCount; i++) {
            HorseCustomisationPanel customisationPanel = new HorseCustomisationPanel();
            horseCustomisationPanel.add(customisationPanel);
        }
    
        panel.add(horseCustomisationPanel, BorderLayout.CENTER);
    
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton nextButton = new JButton("Next");
        nextButton.setPreferredSize(new Dimension(150, 40));
        buttonPanel.add(nextButton);
    
        panel.add(buttonPanel, BorderLayout.SOUTH);
    
        nextButton.addActionListener(e -> {
            for (int i = 0; i < laneCount; i++) {
                HorseCustomisationPanel panel = (HorseCustomisationPanel) horseCustomisationPanel.getComponent(i);
                String name = panel.getHorseName();
                String symbol = panel.getHorseSymbol();
                String breed = panel.getHorseBreed();
                String equipment = panel.getHorseEquipment();
    
                //horseGUI objects for each lane
                horses[i] = new HorseGUI(name, symbol, breed, equipment);
            }
    
            frame.getContentPane().removeAll();
            frame.revalidate();
            frame.repaint();

            setupRacePanel(laneCount, selectedWeather);
        });

        frame.add(panel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    private void setupRacePanel(int laneCount, String selectedWeather) {
        race = new RaceGUI(frame,50, laneCount, selectedWeather);
        for (int i = 0; i < laneCount; i++) {
            race.addHorse(horses[i]);  //each horse added to RaceGUI
        }

        frame.getContentPane().removeAll();
        frame.add(race.getRacePanel(), BorderLayout.CENTER); 
        frame.revalidate();
        frame.repaint();

        race.startRace();
    }
}
