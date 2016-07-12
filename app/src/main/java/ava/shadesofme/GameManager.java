package ava.shadesofme;

import java.util.ArrayList;

import ava.shadesofme.DataModels.Item;
import ava.shadesofme.DataModels.Location;
import ava.shadesofme.GameState.CurrentState;
import ava.shadesofme.GameState.Equipment;
import ava.shadesofme.GameState.Player;

public class GameManager {

    private CurrentState currentState;
    private Player player;
    private Equipment equipment;

    public GameManager(CurrentState currentState, Player player, Equipment equipment) {
        this.currentState = currentState;
        this.player = player;
        this.equipment = equipment;
    }

    public void advanceTimeBy(int minutes) {
        currentState.advanceTimeBy(minutes);
        player.updateStatsByTime(minutes);
    }

    public void goToLocation(Location newLocation, int minutes) {
        advanceTimeBy(minutes);
        currentState.setCurrentLocation(newLocation);
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
        equipment.remove(item);
    }

    public void upgradeItem(Item item) {
        advanceTimeBy(item.getUpgradeTime());
        equipment.replace(item, item.getUpgradeStage());
    }

    public void pickUpItem(Item item) {
        equipment.add(item);
    }

    public CurrentState getCurrentState() {
        return currentState;
    }

    public Player getPlayer() {
        return player;
    }

    public Equipment getEquipment() {
        return equipment;
    }
}
