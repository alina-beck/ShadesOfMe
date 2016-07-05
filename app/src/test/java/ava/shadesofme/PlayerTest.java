package ava.shadesofme;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PlayerTest {

    private Player player;
    private DashboardViewModel mockViewModel = Mockito.mock(DashboardViewModel.class);
    private Item mockItem = Mockito.mock(Item.class);

    @Before
    public void setUp() {
        player = new Player(50, 100, 50, 100, 50, 100);
    }

    /**
     * Stat Updates
     */

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
        player.updateStatsByTime(30);
        assertEquals(44, player.getCurrentSatiety());
    }

    @Test
    public void decreaseEnergyAccordingToTimePassed() {
        player.updateStatsByTime(30);
        assertEquals(46, player.getCurrentEnergy());
    }

    @Test
    public void energyDecreasesAtDoubleRateWhenSatietyIsZero() {
        player.setCurrentSatiety(0);
        player.updateStatsByTime(30);
        assertEquals(43, player.getCurrentEnergy());
    }

    @Test
    public void healthDoesGenerallyNotIncreaseOrDecreaseOverTime() {
        player.updateStatsByTime(30);
        assertEquals(50, player.getCurrentHealth());
    }

    @Test
    public void healthIncreasesOverTimeWhenEnergyAndSatietyAreAboveNinety() {
        //updates Satiety and Energy before Health, so starting values must be high to pass test
        player.setCurrentSatiety(99);
        player.setCurrentEnergy(99);
        player.updateStatsByTime(30);
        assertEquals(54, player.getCurrentHealth());
    }

    @Test
    public void healthDecreasesOverTimeWhenEnergyAndSatietyAreZero() {
        player.setCurrentSatiety(0);
        player.setCurrentEnergy(0);
        player.updateStatsByTime(30);
        assertEquals(44, player.getCurrentHealth());
    }

    @Test
    public void satietyIsUpdatedOnItemUse() {
        when(mockItem.getSatietyEffect()).thenReturn(20);
        player.updateStatsWithItem(mockItem);
        assertEquals(70, player.getCurrentSatiety());
    }

    @Test
    public void energyIsUpdatedOnItemUse() {
        when(mockItem.getEnergyEffect()).thenReturn(20);
        player.updateStatsWithItem(mockItem);
        assertEquals(70, player.getCurrentEnergy());
    }

    @Test
    public void healthIsUpdatedOnItemUse() {
        when(mockItem.getHealthEffect()).thenReturn(20);
        player.updateStatsWithItem(mockItem);
        assertEquals(70, player.getCurrentHealth());
    }

    /**
     * Observable Stuff
     */

    @Test
    public void observersCanRegister() {
        player.addObserver(mockViewModel);
        assertEquals(1, player.countObservers());
    }

    @Test
    public void observersCanUnregister() {
        player.addObserver(mockViewModel);
        player.deleteObserver(mockViewModel);
        assertEquals(0, player.countObservers());
    }

    @Test
    public void observersAreNotifiedUponChange() {
        player.addObserver(mockViewModel);
        player.updateStatsByTime(30);
        verify(mockViewModel, times(3)).update(player, null);
    }
}
