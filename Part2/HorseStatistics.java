package Part2;
import java.util.*;

public class HorseStatistics {
    private ArrayList<RaceStats> raceHistory;

    public HorseStatistics() {
        raceHistory = new ArrayList<>();
    }

    public void addStats(int raceLength, int distanceTravelled, int finishingTime, boolean fell) {
        double averageSpeed = (double) distanceTravelled / ((double) finishingTime / 10.0);
        RaceStats stats = new RaceStats(averageSpeed, finishingTime, fell);
        raceHistory.add(stats);
    }


    //average speed of horse
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

    public String getAverageSpeedString() {
        double averageSpeed = Math.round(getAverageSpeed() * 10.0) / 10.0;
        return averageSpeed + " m/s";
    }

    //finishing time of horse
    public double getFinishingTime() {
        if (raceHistory.isEmpty()){
            return 0.0;
        }

        double totalFinishingTime = 0.0;
        for (RaceStats stats : raceHistory) {
            totalFinishingTime += stats.finishingTime;
        }
        return totalFinishingTime / raceHistory.size();
    }

    public String getFinishingTimeString() {
        double finishingTime = Math.round(getFinishingTime() * 10.0) / 10.0;
        return finishingTime + " seconds";
    }
    

    //win rate of horse
    public double getWinRate() {
        int wins = 0;
        int totalRaces = raceHistory.size();

        for (RaceStats stats : raceHistory) {
            if (!stats.fell) {
                wins++;
            }
        }
        return (double) wins / (double) totalRaces;
    }
}
