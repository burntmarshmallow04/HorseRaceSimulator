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
    private JFrame parentFrame;

    public RaceGUI(JFrame parentFrame, int raceLength, int numHorses) {
        this.parentFrame = parentFrame;
        this.raceLength = raceLength;
        this.horses = new ArrayList<>();
        this.laneLabels = new ArrayList<>();

        racePanel = new JPanel();
        racePanel.setLayout(new GridLayout(numHorses, 1));

        for (int i = 0; i < numHorses; i++) {
            JLabel laneLabel = new JLabel("Lane " + (i + 1) + ": ");
            laneLabel.setFont(new Font("Arial", Font.PLAIN, 17));
            laneLabels.add(laneLabel);
            racePanel.add(laneLabel);
        }
    }

    public void addHorse(HorseGUI horse) {
        horses.add(horse);
    }

    public void startRace() {
        for (HorseGUI horse : horses) {
            horse.goBackToStart();
        }
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
            if (Math.random() < theHorse.getConfidence()) {
                theHorse.moveForward();
            }
            if (Math.random() < (0.05 * theHorse.getConfidence() * theHorse.getConfidence())) {
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
        laneDisplay.append("| (" + horse.getConfidence() + ")");
        return laneDisplay.toString();
    }

    public JPanel getRacePanel() {
        return racePanel;
    }

    private void announceWinner(HorseGUI winner) {
        JOptionPane.showMessageDialog(parentFrame, "And the winner is... " + winner.getName() + "!");
    }

    private void announceNoWinner() {
        JOptionPane.showMessageDialog(parentFrame, "All horses have fallen! There are no winners!");
    }
}
