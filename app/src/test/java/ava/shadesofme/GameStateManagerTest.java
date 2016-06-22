package ava.shadesofme;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GameStateManagerTest {

    private GameStateManager gameStateManager;
    private Player mockPlayer = Mockito.mock(Player.class);
    private Location mockLocation = Mockito.mock(Location.class);
    private Item mockItem = Mockito.mock(Item.class);
    private DashboardPresenter mockDashboardPresenter = Mockito.mock(DashboardPresenter.class);

    @Before
    public void setUp() {
        gameStateManager = new GameStateManager("14:00", mockPlayer, mockLocation, mockDashboardPresenter);
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
    public void alertsPresenterToUpdateTimeDisplay() {
        gameStateManager.advanceBy(30);
        verify(mockDashboardPresenter).updateTime("14:30");
    }

    @Test
    public void updatesPlayerStatsWhenTimeAdvances() {
        gameStateManager.advanceBy(30);
        verify(mockPlayer).updateStats(30);
    }

    @Test
    public void alertsPresenterToUpdatePlayerStatsDisplay() {
        gameStateManager.advanceBy(30);
        verify(mockDashboardPresenter).updatePlayerStats(mockPlayer);
    }

    @Test
    public void currentLocationChangesOnGoToAction() {
        Location newLocation = Mockito.mock(Location.class);
        gameStateManager.goTo(newLocation, 30);
        assertEquals(newLocation, gameStateManager.getCurrentLocation());
    }

    @Test
    public void timeAdvancesOnItemUse() {
        when(mockItem.getUseTime()).thenReturn(30);
        gameStateManager.useItem(mockItem);
        assertEquals("14:30", gameStateManager.getCurrentTime());
    }

    @Test
    public void satietyIsUpdatedOnItemUse() {
        gameStateManager.useItem(mockItem);
        verify(mockPlayer).updateSatiety(Mockito.anyInt());
    }

    @Test
    public void energyIsUpdatedOnItemUse() {
        gameStateManager.useItem(mockItem);
        verify(mockPlayer).updateEnergy(Mockito.anyInt());
    }

    @Test
    public void healthIsUpdatedOnItemUse() {
        gameStateManager.useItem(mockItem);
        verify(mockPlayer).updateEnergy(Mockito.anyInt());
    }
}
