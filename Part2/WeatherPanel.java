package Part2;

import javax.swing.*;

public class WeatherPanel extends JPanel {
    private JLabel weatherLabel;
    private JComboBox<String> weatherList;

    public WeatherPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        weatherLabel = new JLabel("Select Weather:");
        String[] weather = {"Sunny", "Rainy", "Snowy"}; 
        weatherList = new JComboBox<>(weather); 

        add(weatherLabel);
        add(weatherList);
    }

    public String getWeather() {
        return (String) weatherList.getSelectedItem(); 
    }
}

        