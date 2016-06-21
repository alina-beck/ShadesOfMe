package ava.shadesofme;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GameStateManagerTest {

    private GameStateManager gameStateManager;
    private Player player = Mockito.mock(Player.class);
    private Location location = Mockito.mock(Location.class);
    private Item item = Mockito.mock(Item.class);

    @Before
    public void setUp() {
        gameStateManager = new GameStateManager("14:00", player, location);
    }

    @Test
    public void currentGameTimeAdvancesGivenNumberOfMinutes() {
        gameStateManager.advanceBy(30);
        assertEquals("14:30", gameStateManager.getCurrentTime());
    }

    @Test
    public void currentGameTimeAdvancesMoreThanSixtyMinutes() {
        gameStateManager.advanceBy(125);
        assertEquals("16:05", gameStateManager.getCurrentTime());
    }

    @Test
    public void currentGameTimeAdvancesPastTwentyFourHours() {
        gameStateManager.advanceBy(690);
        assertEquals("01:30", gameStateManager.getCurrentTime());
    }

    @Test
    public void updatesPlayerStatsWhenTimeAdvances() {
        gameStateManager.advanceBy(30);
        verify(player).updateStats(30);
    }

    @Test
    public void currentLocationChangesOnGoToAction() {
        Location newLocation = Mockito.mock(Location.class);
        gameStateManager.goTo(newLocation, 30);
        assertEquals(newLocation, gameStateManager.getCurrentLocation());
    }

    @Test
    public void timeAdvancesOnItemUse() {
        when(item.getUseTime()).thenReturn(30);
        gameStateManager.useItem(item);
        assertEquals("14:30", gameStateManager.getCurrentTime());
    }

    @Test
    public void satietyIsUpdatedOnItemUse() {
        gameStateManager.useItem(item);
        verify(player).updateSatiety(Mockito.anyInt());
    }

    @Test
    public void energyIsUpdatedOnItemUse() {
        gameStateManager.useItem(item);
        verify(player).updateEnergy(Mockito.anyInt());
    }

    @Test
    public void healthIsUpdatedOnItemUse() {
        gameStateManager.useItem(item);
        verify(player).updateEnergy(Mockito.anyInt());
    }
}
