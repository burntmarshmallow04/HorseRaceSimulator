package Part2;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

public class HorseDescription extends JPanel{
    private HorseGUI horse;
    private JLabel confidenceLabel;
    private JLabel averageSpeedLabel;
    private JLabel finishingTimeLabel;
    private JLabel winRateLabel;

    public HorseDescription(HorseGUI horse){
        this.horse = horse;
        setLayout(new GridLayout(5, 1));
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setBackground(Color.LIGHT_GRAY);
        
        JLabel horseNameLabel = new JLabel("Horse Name: " + horse.getName());
        horseNameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        horseNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(horseNameLabel);

        confidenceLabel = new JLabel("Confidence: " + horse.getConfidence());
        confidenceLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        confidenceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(confidenceLabel);

        averageSpeedLabel = new JLabel("Average Speed: " + horse.getStats().getAverageSpeedString());
        averageSpeedLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        averageSpeedLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(averageSpeedLabel);

        finishingTimeLabel = new JLabel("Finishing Time: " + horse.getStats().getFinishingTimeString());
        finishingTimeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        finishingTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(finishingTimeLabel);

        winRateLabel = new JLabel("Win Rate: " + horse.getStats().getWinRate());
        winRateLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        winRateLabel.setHorizontalAlignment(SwingConstants.CENTER);
    }

    public void updateHorseInfo(double confidence) {
        //update confidence
        confidenceLabel.setText("Confidence: " + confidence);
    
        //update statistics
        averageSpeedLabel.setText("Average Speed: " + this.horse.getStats().getAverageSpeedString());
        finishingTimeLabel.setText("Finishing Time: " + this.horse.getStats().getFinishingTimeString());
        winRateLabel.setText("Win Percentage: " + this.horse.getStats().getWinRate());
    }
    
}
