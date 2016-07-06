package ava.shadesofme;

public class Item {

    private Item upgradeStage;
    private int useTime;
    private int upgradeTime;
    private int satietyEffect;
    private int energyEffect;
    private int healthEffect;
    private int weight;
    private int volume;
    private int stackable;
    private String name;

    public Item(Item upgradeStage, int useTime, int upgradeTime, int satietyEffect, int energyEffect, int healthEffect,
                int weight, int volume, int stackable) {
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

    public Item getUpgradeStage() {
        return upgradeStage;
    }

    public int getUpgradeTime() {
        return upgradeTime;
    }

    public String getName() {
        return name;
    }
}
