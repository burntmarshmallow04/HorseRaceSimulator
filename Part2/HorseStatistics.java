package Part2;
import java.util.*;

public class HorseStatistics {
    private ArrayList<RaceStats> raceHistory;

    public HorseStatistics() {
        raceHistory = new ArrayList<>();
    }

    public void addStats(int raceLength, int distanceTravelled, int raceTime, boolean fell) {
        double averageSpeed = (double) distanceTravelled / ((double) raceTime / 10.0);
        boolean won = false;

        if (distanceTravelled == raceLength) {
            won = true;
        }

        RaceStats stats = new RaceStats(averageSpeed, won, fell);
        raceHistory.add(stats);
    }

    public double getAverageSpeed() {
        if (raceHistory.isEmpty()){
            return 0.0;
        }

        double totalSpeed = 0.0;
        for (RaceStats stats : raceHistory) {
            totalSpeed += stats.averageSpeed;
        }
        return totalSpeed / raceHistory.size();
    }

    public String getAverageSpeedAsString() {
        double averageSpeed = Math.round(getAverageSpeed() * 10.0) / 10.0;
        return averageSpeed + " m/s";
    }

    public double getWinRate() {
        if (raceHistory.isEmpty()) return 0.0;

        int wins = 0;
        for (RaceStats stats : raceHistory) {
            if (stats.won) wins++;
        }
        return (double) wins / raceHistory.size();
    }

    public String getWinPercentageAsString() {
        int winRatio = (int) Math.round(getWinRate() * 100.0);
        return winRatio + "%";
    }

    public double getFallRate() {
        if (raceHistory.isEmpty()) return 0.0;

        int falls = 0;
        for (RaceStats stats : raceHistory) {
            if (stats.fell) falls++;
        }
        return (double) falls / raceHistory.size();
    }

    public String getFallPercentageAsString() {
        int fallRatio = (int) Math.round(getFallRate() * 100.0);
        return fallRatio + "%";
    }
}
