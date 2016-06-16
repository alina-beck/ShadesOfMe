package ava.shadesofme;

import java.util.Locale;

public class GameStateManager {

    public String currentTime;

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public void advanceBy(int minutes) {
        int currentHours = getCurrentHours();
        int currentMinutes = getCurrentMinutes();

        currentMinutes += minutes;
        while(currentMinutes >= 60) {
            currentMinutes -= 60;
            currentHours ++;
        }
        while(currentHours >= 24) {
            currentHours -= 24;
        }

        setCurrentTime(currentHours, currentMinutes);
    }

    private int getCurrentHours() {
        String[] splitTime = currentTime.split(":");
        return Integer.parseInt(splitTime[0]);
    }

    private int getCurrentMinutes() {
        String[] splitTime = currentTime.split(":");
        return Integer.parseInt(splitTime[1]);
    }

    private void setCurrentTime(int hours, int minutes) {
        currentTime = String.format(Locale.getDefault(), "%02d:%02d", hours, minutes);
    }
}
