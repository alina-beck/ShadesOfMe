package ava.shadesofme;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

public class GameStateManagerTest {

    private GameStateManager gameStateManager;
    private Player player = Mockito.mock(Player.class);
    private Location location = Mockito.mock(Location.class);

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
}
