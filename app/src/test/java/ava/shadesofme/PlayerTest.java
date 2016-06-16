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
        assertEquals(30, player.currentSatiety);
    }

    @Test
    public void energyDecreasesByGivenAmount() {
        player.updateEnergy(-20);
        assertEquals(30, player.currentEnergy);
    }

    @Test
    public void healthDecreasesByGivenAmount() {
        player.updateHealth(-20);
        assertEquals(30, player.currentHealth);
    }

    @Test
    public void satietyIncreasesByGivenAmount() {
        player.updateSatiety(20);
        assertEquals(70, player.currentSatiety);
    }

    @Test
    public void energyIncreasesByGivenAmount() {
        player.updateEnergy(20);
        assertEquals(70, player.currentEnergy);
    }

    @Test
    public void healthIncreasesByGivenAmount() {
        player.updateHealth(20);
        assertEquals(70, player.currentHealth);
    }

    @Test
    public void satietyNeverDropsBelowZero() {
        player.updateSatiety(-70);
        assertEquals(0, player.currentSatiety);
    }

    @Test
    public void energyNeverDropsBelowZero() {
        player.updateEnergy(-70);
        assertEquals(0, player.currentEnergy);
    }

    @Test
    public void whenHealthDropsToOrBelowZeroPlayerDies() {
        player.updateHealth(-70);
        assertFalse(player.isAlive);
    }

    @Test
    public void satietyIsNeverHigherThanMaximum() {
        player.updateSatiety(70);
        assertEquals(player.maxSatiety, player.currentSatiety);
    }

    @Test
    public void energyIsNeverHigherThanMaximum() {
        player.updateEnergy(70);
        assertEquals(player.maxEnergy, player.currentEnergy);
    }

    @Test
    public void healthIsNeverHigherThanMaximum() {
        player.updateHealth(70);
        assertEquals(player.maxHealth, player.currentHealth);
    }
}