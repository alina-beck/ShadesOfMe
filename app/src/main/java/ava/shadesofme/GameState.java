package ava.shadesofme;

import java.util.Locale;

public class GameState {

    private String currentTime;
    private Location currentLocation;

    public GameState(String currentTime, Location currentLocation) {
        this.currentTime = currentTime;
        this.currentLocation = currentLocation;
    }

    public void advanceTimeBy(int minutes) {
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

    public String getCurrentTime() {
        return currentTime;
    }

    private void setCurrentTime(int hours, int minutes) {
        currentTime = String.format(Locale.getDefault(), "%02d:%02d", hours, minutes);
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }
}
