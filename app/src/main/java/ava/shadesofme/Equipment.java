package ava.shadesofme;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Equipment extends Observable {

    private int currentTotalWeight;
    private int currentTotalVolume;
    private int maxTotalWeight;
    private int maxTotalVolume;
    private int totalSlots;
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
        boolean fitsInEquipment = fitsInEquipment(item);
        boolean slotIsFree = slotIsFree();
        boolean canBeStacked = canBeStacked(item);

        if(fitsInEquipment && slotIsFree || canBeStacked) {
            getItems().add(item);
            currentTotalWeight += item.getWeight();
            currentTotalVolume += item.getVolume();
            setChanged();
            notifyObservers();
            return true;
        }
        return false;
    }

    public boolean remove(Item item) {
        if(getItems().contains(item)) {
            getItems().remove(item);
            currentTotalWeight -= item.getWeight();
            currentTotalVolume -= item.getVolume();
            setChanged();
            notifyObservers();
            return true;
        }
        return false;
    }

    public boolean replace(Item item, Item upgradeStage) {
        return remove(item) && add(upgradeStage);
    }

    private boolean fitsInEquipment(Item item) {
        return item.getWeight() + getCurrentTotalWeight() <= getMaxTotalWeight()
                && item.getVolume() + getCurrentTotalVolume() <= getMaxTotalVolume();
    }

    private boolean slotIsFree() {
        return getItems().size() < getTotalSlots();
    }

    private boolean canBeStacked(Item item) {
        if(getItems().contains(item)) {
            int itemStack = 0;
            for (Item i : getItems()) {
                if(item.equals(i)) {
                    itemStack++;
                }
            }
            if(itemStack % item.getStackable() != 0) {
                return true;
            }
        }
        return false;
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
