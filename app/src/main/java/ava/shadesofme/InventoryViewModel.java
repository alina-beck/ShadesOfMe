package ava.shadesofme;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class InventoryViewModel implements Observer {

    private int numberOfSlots;
    private String maxWeight;
    private String maxVolume;
    private String currentWeight;
    private String currentVolume;
    private GameManager gameManager;
    private List<Map<String, String>> items;

    public InventoryViewModel(GameManager gameManager) {
        Equipment equipment = gameManager.getEquipment();

        this.numberOfSlots = equipment.getTotalSlots();
        this.maxWeight = String.valueOf(equipment.getMaxTotalWeight());
        this.maxVolume = String.valueOf(equipment.getMaxTotalVolume());
        this.currentWeight = String.valueOf(equipment.getCurrentTotalWeight());
        this.currentVolume = String.valueOf(equipment.getCurrentTotalVolume());
        this.items = transformItemList(equipment.getItems());
        this.gameManager = gameManager;
    }

    private List<Map<String, String>> transformItemList(List<Item> items) {
        List<Map<String, String>> transformedItems = new ArrayList<>();
        for(Item item : items) {
            Map<String, String> transformedItem = new HashMap<>();
            transformedItem.put("name", item.getName());
            transformedItem.put("weight", String.valueOf(item.getWeight()));
            transformedItem.put("volume", String.valueOf(item.getVolume()));
            transformedItems.add(transformedItem);
        }
        return transformedItems;
    }

    @Override
    public void update(Observable observable, Object data) {
        if (observable instanceof Equipment) {
            Equipment equipment = (Equipment) observable;
            if (equipment.getTotalSlots() != getNumberOfSlots()) {
                setNumberOfSlots(equipment.getTotalSlots());
            }
            if (!String.valueOf(equipment.getMaxTotalWeight()).equals(getMaxWeight())) {
                setMaxWeight(String.valueOf(equipment.getMaxTotalWeight()));
            }
            if (!String.valueOf(equipment.getMaxTotalVolume()).equals(getMaxVolume())) {
                setMaxVolume(String.valueOf(equipment.getMaxTotalVolume()));
            }
            if (!String.valueOf(equipment.getCurrentTotalWeight()).equals(getCurrentWeight())) {
                setCurrentWeight(String.valueOf(equipment.getCurrentTotalWeight()));
            }
            if (!String.valueOf(equipment.getCurrentTotalVolume()).equals(getCurrentVolume())) {
                setCurrentVolume(String.valueOf(equipment.getCurrentTotalVolume()));
            }
        }
    }

    public String getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(String currentWeight) {
        this.currentWeight = currentWeight;
    }

    public String getMaxWeight() {
        return maxWeight;
    }

    public String getMaxVolume() {
        return maxVolume;
    }

    public int getNumberOfSlots() {
        return numberOfSlots;
    }

    public String getCurrentVolume() {
        return currentVolume;
    }

    public void setMaxWeight(String maxWeight) {
        this.maxWeight = maxWeight;
    }

    public void setMaxVolume(String maxVolume) {
        this.maxVolume = maxVolume;
    }

    public void setNumberOfSlots(int numberOfSlots) {
        this.numberOfSlots = numberOfSlots;
    }

    public void setCurrentVolume(String currentVolume) {
        this.currentVolume = currentVolume;
    }

    public List<Map<String, String>> getItems() {
        return items;
    }
}
