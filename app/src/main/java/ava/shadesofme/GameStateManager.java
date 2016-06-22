package ava.shadesofme;

import java.util.Locale;

public class GameStateManager {

    private String currentTime;
    private Player player;
    private Location currentLocation;

    public GameStateManager(String currentTime, Player player, Location location) {
        this.currentTime = currentTime;
        this.player = player;
        this.currentLocation = location;
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
        player.updateStats(minutes);
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

    public void goTo(Location newLocation, int minutes) {
        advanceBy(minutes);
        currentLocation = newLocation;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void useItem(Item item) {
        advanceBy(item.getUseTime());
        player.updateSatiety(item.getSatietyEffect());
        player.updateEnergy(item.getEnergyEffect());
        player.updateHealth(item.getHealthEffect());
    }

    public Player getPlayer() {
        return player;
    }
}
