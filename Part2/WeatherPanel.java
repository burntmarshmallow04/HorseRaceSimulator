package Part2;

import javax.swing.*;
import java.awt.*;

public class WeatherPanel extends JPanel {
    private SelectList weatherList;

    public WeatherPanel() {
        setLayout(new GridLayout(1, 1, 10, 10)); 
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        weatherList = new SelectList("Select Weather Condition", new String[]{"Sunny", "Rainy", "Snowy"});
        add(weatherList);
    }

    public String getWeatherCondition() {
        return weatherList.getSelectedItem();
    }
}