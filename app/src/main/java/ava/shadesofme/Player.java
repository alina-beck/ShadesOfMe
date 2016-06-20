package ava.shadesofme;

public class Player {

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

    public void updateSatiety(int satietyChange) {
        currentSatiety = getCurrentSatiety() + satietyChange;
        if (getCurrentSatiety() > getMaxSatiety()) {
            currentSatiety = getMaxSatiety();
        }
        if (getCurrentSatiety() < 0) {
            currentSatiety = 0;
        }
    }

    public void updateEnergy(int energyChange) {
        currentEnergy = getCurrentEnergy() + energyChange;
        if (getCurrentEnergy() > getMaxEnergy()) {
            currentEnergy = getMaxEnergy();
        }
        if (getCurrentEnergy() < 0) {
            currentEnergy = 0;
        }
    }

    public void updateHealth(int healthChange) {
        currentHealth = getCurrentHealth() + healthChange;
        if (getCurrentHealth() > getMaxHealth()) {
            currentHealth = getMaxHealth();
        }
        isAlive = (getCurrentHealth() > 0);
    }

    private void updateSatietyByTime(int minutes) {
        int satietyDecrease = (int) Math.round(minutes * 0.2);
        updateSatiety(-satietyDecrease);
    }

    private void updateEnergyByTime(int minutes) {
        int energyDecrease;
        if (getCurrentSatiety() == 0) {
            energyDecrease = (int) Math.round(minutes * 0.12 * 2);
        }
        else {
            energyDecrease = (int) Math.round(minutes * 0.12);
        }
        updateEnergy(-energyDecrease);
    }

    public void updateHealthByTime(int minutes) {
        int healthChange = 0;
        if (getCurrentSatiety() > 90 && getCurrentEnergy() > 90) {
            healthChange = (int) Math.round(minutes * 0.12);
        }
        else if (getCurrentSatiety() == 0 && getCurrentEnergy() == 0) {
            healthChange = -(int) Math.round(minutes * 0.2);
        }
        updateHealth(healthChange);
    }

    public void updateStats(int minutes) {
        updateSatietyByTime(minutes);
        updateEnergyByTime(minutes);
        updateHealthByTime(minutes);
    }

    public int getCurrentSatiety() {
        return currentSatiety;
    }

    public void setCurrentSatiety(int currentSatiety) {
        this.currentSatiety = currentSatiety;
    }

    public int getCurrentEnergy() {
        return currentEnergy;
    }

    public void setCurrentEnergy(int currentEnergy) {
        this.currentEnergy = currentEnergy;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public boolean isAlive() {
        return isAlive;
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
