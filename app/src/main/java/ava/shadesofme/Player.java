package ava.shadesofme;

import java.util.List;
import java.util.Observable;

public class Player extends Observable {

    private static final double SATIETY_DECREASE_RATE = 0.2;
    private static final double ENERGY_DECREASE_RATE = 0.12;
    private static final double HEALTH_REGENERATION_MARGIN = 0.9;
    private static final double HEALTH_DECREASE_RATE = 0.2;
    private static final double HEALTH_INCREASE_RATE = 0.12;

    private int currentSatiety;
    private int currentEnergy;
    private int currentHealth;
    private boolean isAlive;
    private int maxSatiety;
    private int maxEnergy;
    private int maxHealth;

    public Player(int startingSatiety, int maxSatiety, int startingEnergy, int maxEnergy, int startingHealth, int maxHealth) {
        this.currentSatiety = startingSatiety;
        this.currentEnergy = startingEnergy;
        this.currentHealth = startingHealth;
        this.isAlive = (startingHealth > 0);
        this.maxSatiety = maxSatiety;
        this.maxEnergy = maxEnergy;
        this.maxHealth = maxHealth;
    }

    public void updateStatsByTime(int minutes) {
        updateSatiety(calculateSatietyChangeFromTime(minutes));
        updateEnergy(calculateEnergyChangeFromTime(minutes));
        updateHealth(calculateHealthChangeFromTime(minutes));
    }

    public void updateStatsWithItem(Item item) {
        updateSatiety(item.getSatietyEffect());
        updateEnergy(item.getEnergyEffect());
        updateHealth(item.getHealthEffect());
    }

    /**
     * Calculating stat changes from a given time
     */

    private int calculateSatietyChangeFromTime(int minutes) {
        return -(int) Math.round(minutes * SATIETY_DECREASE_RATE);
    }

    private int calculateEnergyChangeFromTime(int minutes) {
        int energyDecrease;
        if (getCurrentSatiety() == 0) {
            energyDecrease = -(int) Math.round(minutes * ENERGY_DECREASE_RATE * 2);
        }
        else {
            energyDecrease = -(int) Math.round(minutes * ENERGY_DECREASE_RATE);
        }
        return energyDecrease;
    }

    private int calculateHealthChangeFromTime(int minutes) {
        int healthChange = 0;
        if (getCurrentSatiety() > (getMaxSatiety() * HEALTH_REGENERATION_MARGIN)
                && getCurrentEnergy() > (getMaxEnergy() * HEALTH_REGENERATION_MARGIN)) {
            healthChange = (int) Math.round(minutes * HEALTH_INCREASE_RATE);
        }
        else if (getCurrentSatiety() == 0 && getCurrentEnergy() == 0) {
            healthChange = -(int) Math.round(minutes * HEALTH_DECREASE_RATE);
        }
        return healthChange;
    }

    /**
     * Validating changes and updating stats
     */

    public void updateSatiety(int satietyChange) {
        int newSatietyLevel = getCurrentSatiety() + satietyChange;
        if (newSatietyLevel > getMaxSatiety()) {
            newSatietyLevel = getMaxSatiety();
        }
        else if (newSatietyLevel < 0) {
            newSatietyLevel = 0;
        }
        setCurrentSatiety(newSatietyLevel);
    }

    public void updateEnergy(int energyChange) {
        int newEnergyLevel = getCurrentEnergy() + energyChange;
        if (newEnergyLevel > getMaxEnergy()) {
            newEnergyLevel = getMaxEnergy();
        }
        else if (newEnergyLevel < 0) {
            newEnergyLevel = 0;
        }
        setCurrentEnergy(newEnergyLevel);
    }

    public void updateHealth(int healthChange) {
        int newHealthLevel = getCurrentHealth() + healthChange;
        if (newHealthLevel > getMaxHealth()) {
            newHealthLevel = getMaxHealth();
        }
        else if (newHealthLevel <= 0) {
            newHealthLevel = 0;
            setAlive(false);
        }
        setCurrentHealth(newHealthLevel);
    }

    /**
     * Getters and setters - remember to notify observers when relevant
     */

    public int getCurrentSatiety() {
        return currentSatiety;
    }

    public void setCurrentSatiety(int currentSatiety) {
        this.currentSatiety = currentSatiety;
        setChanged();
        notifyObservers();
    }

    public int getCurrentEnergy() {
        return currentEnergy;
    }

    public void setCurrentEnergy(int currentEnergy) {
        this.currentEnergy = currentEnergy;
        setChanged();
        notifyObservers();
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
        setChanged();
        notifyObservers();
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        this.isAlive = alive;
    }

    public int getMaxSatiety() {
        return maxSatiety;
    }

    public int getMaxEnergy() {
        return maxEnergy;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

}
