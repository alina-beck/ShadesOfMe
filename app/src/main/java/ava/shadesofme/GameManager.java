package ava.shadesofme;

import java.util.ArrayList;

public class GameManager {

    private GameState gameState;
    private Player player;
    private Equipment equipment;

    public GameManager(GameState gameState, Player player, Equipment equipment) {
        this.gameState = gameState;
        this.player = player;
        this.equipment = equipment;
    }

    public void advanceTimeBy(int minutes) {
        gameState.advanceTimeBy(minutes);
        player.updateStatsByTime(minutes);
    }

    public void goToLocation(Location newLocation, int minutes) {
        advanceTimeBy(minutes);
        gameState.setCurrentLocation(newLocation);
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

    public GameState getGameState() {
        return  gameState;
    }

    public Player getPlayer() {
        return player;
    }

    public Equipment getEquipment() {
        return equipment;
    }
}
