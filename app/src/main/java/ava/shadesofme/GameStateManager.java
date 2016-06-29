package ava.shadesofme;

import java.util.ArrayList;
import java.util.Locale;

public class GameStateManager {

    private String currentTime;
    private Player player;
    private Location currentLocation;
    private EquipmentManager equipmentManager;

    public GameStateManager(String currentTime, Player player, Location location, EquipmentManager equipmentManager) {
        this.currentTime = currentTime;
        this.player = player;
        this.currentLocation = location;
        this.equipmentManager = equipmentManager;
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
        player.updateStatsByTime(minutes);

    }

    public void goToLocation(Location newLocation, int minutes) {
        advanceTimeBy(minutes);
        currentLocation = newLocation;
    }

    public ArrayList<Item> searchLocation(Location location) {
        if (!location.isSearched()) {
            int searchTime = 30 + (location.getItems().size() * 5);
            advanceTimeBy(searchTime);
            location.setSearched(true);
        }
        return location.getItems();
    }

    public void useItem(Item item) {
        advanceTimeBy(item.getUseTime());
        player.updateStatsWithItem(item);
        equipmentManager.remove(item);
    }

    public void upgradeItem(Item item) {
        advanceTimeBy(item.getUpgradeTime());
        equipmentManager.replace(item, item.getUpgradeStage());
    }

    public void pickUpItem(Item item) {
        equipmentManager.add(item);
    }

    public Player getPlayer() {
        return player;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public Location getCurrentLocation() {
        return currentLocation;
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
