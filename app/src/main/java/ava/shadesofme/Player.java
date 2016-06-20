package ava.shadesofme;

public class Player {

    public int currentSatiety;
    public int currentEnergy;
    public int currentHealth;
    public boolean isAlive;
    public int maxSatiety;
    public int maxEnergy;
    public int maxHealth;

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
        currentSatiety += satietyChange;
        if (currentSatiety > maxSatiety) {
            currentSatiety = maxSatiety;
        }
        if (currentSatiety < 0) {
            currentSatiety = 0;
        }
    }

    public void updateEnergy(int energyChange) {
        currentEnergy += energyChange;
        if (currentEnergy > maxEnergy) {
            currentEnergy = maxEnergy;
        }
        if (currentEnergy < 0) {
            currentEnergy = 0;
        }
    }

    public void updateHealth(int healthChange) {
        currentHealth += healthChange;
        if (currentHealth > maxHealth) {
            currentHealth = maxHealth;
        }
        isAlive = (currentHealth > 0);
    }

    public void updateSatietyByTime(int minutes) {
        int satietyDecrease = (int) Math.round(minutes * 0.2);
        updateSatiety(-satietyDecrease);
    }

    public void updateEnergyByTime(int minutes) {
        int energyDecrease;
        if (currentSatiety == 0) {
            energyDecrease = (int) Math.round(minutes * 0.12 * 2);
        }
        else {
            energyDecrease = (int) Math.round(minutes * 0.12);
        }
        updateEnergy(-energyDecrease);
    }

    public void updateHealthByTime(int minutes) {
        int healthChange = 0;
        if (currentSatiety > 90 && currentEnergy > 90) {
            healthChange = (int) Math.round(minutes * 0.12);
        }
        else if (currentSatiety == 0 && currentEnergy == 0) {
            healthChange = -(int) Math.round(minutes * 0.2);
        }
        updateHealth(healthChange);
    }
}
