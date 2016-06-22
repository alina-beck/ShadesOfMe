package ava.shadesofme;

public class Item {

    private EquipmentManager equipmentManager;
    private GameStateManager gameStateManager;
    private Item upgradeStage;
    private int useTime;
    private int upgradeTime;
    private int satietyEffect;
    private int energyEffect;
    private int healthEffect;
    private int weight;
    private int volume;
    private int stackable;

    public Item(EquipmentManager equipmentManager, GameStateManager gameStateManager, Item upgradeStage,
                int useTime, int upgradeTime, int satietyEffect, int energyEffect, int healthEffect,
                int weight, int volume, int stackable) {
        this.equipmentManager = equipmentManager;
        this.gameStateManager = gameStateManager;
        this.upgradeStage = upgradeStage;
        this.useTime = useTime;
        this.upgradeTime = upgradeTime;
        this.satietyEffect = satietyEffect;
        this.energyEffect = energyEffect;
        this.healthEffect = healthEffect;
        this.weight = weight;
        this.volume = volume;
        this.stackable = stackable;
    }

    public void pickUp() {
        equipmentManager.add(this);
    }

    public void use() {
        gameStateManager.useItem(this);
        equipmentManager.remove(this);
    }

    public void upgrade() {
        gameStateManager.advanceBy(upgradeTime);
        equipmentManager.replace(this, upgradeStage);
    }

    public int getUseTime() {
        return useTime;
    }

    public int getSatietyEffect() {
        return satietyEffect;
    }

    public int getEnergyEffect() {
        return energyEffect;
    }

    public int getHealthEffect() {
        return healthEffect;
    }

    public int getWeight() {
        return weight;
    }

    public int getVolume() {
        return volume;
    }

    public int getStackable() {
        return stackable;
    }
}
