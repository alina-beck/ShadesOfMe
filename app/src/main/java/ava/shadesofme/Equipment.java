package ava.shadesofme;

import java.util.ArrayList;
import java.util.List;

public class Equipment {

    private int currentTotalWeight;
    private int currentTotalVolume;
    private int maxTotalWeight;
    private int maxTotalVolume;
    private int totalSlots;
    // TODO: make list observable - or whole equipment if needed?
    private List<Item> items;

    public Equipment(int maxTotalWeight, int maxTotalVolume, int totalSlots) {
        this.currentTotalWeight = 0;
        this.currentTotalVolume = 0;
        this.maxTotalWeight = maxTotalWeight;
        this.maxTotalVolume = maxTotalVolume;
        this.totalSlots = totalSlots;
        this.items = new ArrayList<>();
    }

    public boolean add(Item item) {
        if(item.getWeight() + getCurrentTotalWeight() <= getMaxTotalWeight()
                && item.getVolume() + getCurrentTotalVolume() <= getMaxTotalVolume()
                && getItems().size() < getTotalSlots()) {
            getItems().add(item);
            currentTotalWeight += item.getWeight();
            currentTotalVolume += item.getVolume();
            return true;
        }
        return false;
    }

    public void remove(Item item) {
        // TODO: make boolean
        if(getItems().contains(item)) {
            getItems().remove(item);
            currentTotalWeight -= item.getWeight();
            currentTotalVolume -= item.getVolume();
        }
    }

    public void replace(Item item, Item upgradeStage) {
        // TODO: make boolean
        remove(item);
        add(upgradeStage);
    }

    public int getCurrentTotalWeight() {
        return currentTotalWeight;
    }

    public int getCurrentTotalVolume() {
        return currentTotalVolume;
    }

    public void setCurrentTotalVolume(int currentTotalVolume) {
        this.currentTotalVolume = currentTotalVolume;
    }

    public void setCurrentTotalWeight(int currentTotalWeight) {
        this.currentTotalWeight = currentTotalWeight;
    }
    
    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public int getMaxTotalWeight() {
        return maxTotalWeight;
    }

    public int getMaxTotalVolume() {
        return maxTotalVolume;
    }

    public int getTotalSlots() {
        return totalSlots;
    }
}
