package ava.shadesofme;

public class EquipmentSlot {

    private Item item;
    private int currentStack;
    private int maxWeight;
    private int maxVolume;
    private boolean isStackable;

    public EquipmentSlot(int maxWeight, int maxVolume, boolean isStackable) {
        this.currentStack = 0;
        this.maxWeight = maxWeight;
        this.maxVolume = maxVolume;
        this.isStackable = isStackable;
    }

    public void putItem(Item item) {
        this.item = item;
        this.currentStack = 1;
    }

    public Item getItem() {
        return item;
    }

    public void removeItem() {
        this.item = null;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public int getMaxVolume() {
        return maxVolume;
    }

    public void stackItem(Item mockItem) {
        this.currentStack += 1;
    }

    public int getCurrentStack() {
        return currentStack;
    }

    public boolean getStackable() {
        return isStackable;
    }
}
