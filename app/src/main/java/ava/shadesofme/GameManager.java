package ava.shadesofme;

import java.util.ArrayList;
import java.util.Locale;

public class GameManager {

    private GameState gameState;
    private Player player;
    private EquipmentManager equipmentManager;

    public GameManager(GameState gameState, Player player, EquipmentManager equipmentManager) {
        this.gameState = gameState;
        this.player = player;
        this.equipmentManager = equipmentManager;
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
        equipmentManager.remove(item);
    }

    public void upgradeItem(Item item) {
        advanceTimeBy(item.getUpgradeTime());
        equipmentManager.replace(item, item.getUpgradeStage());
    }

    public void pickUpItem(Item item) {
        equipmentManager.add(item);
    }

    public GameState getGameState() {
        return  gameState;
    }

    public Player getPlayer() {
        return player;
    }
}
