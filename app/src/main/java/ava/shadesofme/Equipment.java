package ava.shadesofme;

import java.util.List;

public class Equipment {

    private int currentTotalWeight;
    private int currentTotalVolume;
    private int maxTotalWeight;
    private int maxTotalVolume;
    private List<EquipmentSlot> equipmentSlots;

    public Equipment(int maxTotalWeight, int maxTotalVolume, List<EquipmentSlot> equipmentSlots) {
        this.currentTotalWeight = 0;
        this.currentTotalVolume = 0;
        this.maxTotalWeight = maxTotalWeight;
        this.maxTotalVolume = maxTotalVolume;
        this.equipmentSlots = equipmentSlots;
    }

    public boolean add(Item item) {
        if (currentTotalWeight + item.getWeight() <= maxTotalWeight && currentTotalVolume + item.getVolume() <= maxTotalVolume) {
            List<EquipmentSlot> equipmentSlots = getEquipmentSlots();
            for (EquipmentSlot e : equipmentSlots) {
                if (e.getItem() == null && item.getWeight() <= e.getMaxWeight() && item.getVolume() <= e.getMaxVolume()) {
                    e.putItem(item);
                    currentTotalWeight += item.getWeight();
                    currentTotalVolume += item.getVolume();
                    return true;
                }
                else if (e.getItem() != null && e.getItem().equals(item)
                        && item.getStackable() > 1 && item.getStackable() > e.getCurrentStack()
                        && e.getStackable()) {
                    e.stackItem(item);
                    currentTotalWeight += item.getWeight();
                    currentTotalVolume += item.getVolume();
                    return true;
                }
            }
        }
        return false;
    }

    public void remove(Item item) {
        List<EquipmentSlot> equipmentSlots = getEquipmentSlots();
        for (EquipmentSlot e : equipmentSlots) {
            if (e.getItem().equals(item)) {
                e.removeItem();
                currentTotalWeight -= item.getWeight();
                currentTotalVolume -= item.getVolume();
                return;
            }
        }
    }

    public void replace(Item item, Item upgradeStage) {
        List<EquipmentSlot> equipmentSlots = getEquipmentSlots();
        for (EquipmentSlot e : equipmentSlots) {
            if (e.getItem().equals(item)) {
                e.removeItem();
                e.putItem(upgradeStage);
                return;
            }
        }
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

    public List<EquipmentSlot> getEquipmentSlots() {
        return equipmentSlots;
    }
}
