package ava.shadesofme;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {

    private Player player;

    @Before
    public void setUp() {
        player = new Player(50, 100, 50, 100, 50, 100);
    }

    @Test
    public void satietyDecreasesByGivenAmount() {
        player.updateSatiety(-20);
        assertEquals(30, player.getCurrentSatiety());
    }

    @Test
    public void energyDecreasesByGivenAmount() {
        player.updateEnergy(-20);
        assertEquals(30, player.getCurrentEnergy());
    }

    @Test
    public void healthDecreasesByGivenAmount() {
        player.updateHealth(-20);
        assertEquals(30, player.getCurrentHealth());
    }

    @Test
    public void satietyIncreasesByGivenAmount() {
        player.updateSatiety(20);
        assertEquals(70, player.getCurrentSatiety());
    }

    @Test
    public void energyIncreasesByGivenAmount() {
        player.updateEnergy(20);
        assertEquals(70, player.getCurrentEnergy());
    }

    @Test
    public void healthIncreasesByGivenAmount() {
        player.updateHealth(20);
        assertEquals(70, player.getCurrentHealth());
    }

    @Test
    public void satietyNeverDropsBelowZero() {
        player.updateSatiety(-70);
        assertEquals(0, player.getCurrentSatiety());
    }

    @Test
    public void energyNeverDropsBelowZero() {
        player.updateEnergy(-70);
        assertEquals(0, player.getCurrentEnergy());
    }

    @Test
    public void whenHealthDropsToOrBelowZeroPlayerDies() {
        player.updateHealth(-70);
        assertFalse(player.isAlive());
    }

    @Test
    public void satietyIsNeverHigherThanMaximum() {
        player.updateSatiety(70);
        assertEquals(player.getMaxSatiety(), player.getCurrentSatiety());
    }

    @Test
    public void energyIsNeverHigherThanMaximum() {
        player.updateEnergy(70);
        assertEquals(player.getMaxEnergy(), player.getCurrentEnergy());
    }

    @Test
    public void healthIsNeverHigherThanMaximum() {
        player.updateHealth(70);
        assertEquals(player.getMaxHealth(), player.getCurrentHealth());
    }

    @Test
    public void decreaseSatietyAccordingToTimePassed() {
        player.updateStats(30);
        assertEquals(44, player.getCurrentSatiety());
    }

    @Test
    public void decreaseEnergyAccordingToTimePassed() {
        player.updateStats(30);
        assertEquals(46, player.getCurrentEnergy());
    }

    @Test
    public void energyDecreasesAtDoubleRateWhenSatietyIsZero() {
        player.setCurrentSatiety(0);
        player.updateStats(30);
        assertEquals(43, player.getCurrentEnergy());
    }

    @Test
    public void healthDoesGenerallyNotIncreaseOrDecreaseOverTime() {
        player.updateStats(30);
        assertEquals(50, player.getCurrentHealth());
    }

    @Test
    public void healthIncreasesOverTimeWhenEnergyAndSatietyAreAboveNinety() {
        //updates Satiety and Energy before Health, so starting values must be high to pass test
        player.setCurrentSatiety(99);
        player.setCurrentEnergy(99);
        player.updateStats(30);
        assertEquals(54, player.getCurrentHealth());
    }

    @Test
    public void healthDecreasesOverTimeWhenEnergyAndSatietyAreZero() {
        player.setCurrentSatiety(0);
        player.setCurrentEnergy(0);
        player.updateStats(30);
        assertEquals(44, player.getCurrentHealth());
    }
}
