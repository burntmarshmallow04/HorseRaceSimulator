package Part2;

import javax.swing.*;
import java.awt.*;

public class LanePanel extends JPanel {
    private HorseGUI horse;
    private int raceLength;
    private String trackShape;

    public LanePanel(HorseGUI horse, int raceLength, String trackShape) {
        this.horse = horse;
        this.raceLength = raceLength;
        this.trackShape = trackShape;
    }

    public void updateLane() {
        // Update the horse's position based on the race progress
        horse.setDistanceTravelled(horse.getDistanceTravelled() + 5); // Adjust the increment for horse movement
        repaint();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        int lineWidth = 3;
        int startLineX = horse.getImage().getWidth() + lineWidth;
        int finishLineX = getWidth() - lineWidth;

        // Draw start and finish lines
        g.setColor(Color.BLACK);
        g.fillRect(startLineX, 0, lineWidth, getHeight());
        g.fillRect(finishLineX, 0, lineWidth, getHeight());

        // Rotate and label start/finish
        g2d.rotate(Math.PI / 2);
        g2d.drawString("Start", getHeight() / 2 - 13, -startLineX + 20);
        g2d.drawString("Finish", getHeight() / 2 - 17, -finishLineX + 20);
        g2d.dispose();

        int horseX = (int) (((double) horse.getDistanceTravelled() / (double) (raceLength)) * (getWidth() - horse.getImage().getWidth() - 3));
        int horseY = getHeight() / 2 - horse.getImage().getHeight() / 2;

        //draw track shape
        if ("Circular".equals(trackShape)) {
            horseY += (int) (50 * Math.sin(Math.toRadians(horseX * 360.0 / getWidth())));
        } else if ("Wavy".equals(trackShape)) {
            horseY += (int) (20 * Math.sin(Math.toRadians(horseX * 10)));
        }

        //display fallen horse
        if (horse.hasFallen()) {
            horseX += 5;
            horseY += 5;
            g.drawImage(horse.getFallenImage(), horseX, horseY, this);
        } else {
            g.drawImage(horse.getImage(), horseX, horseY, this);
        }

        g2d.dispose();
    }
}
