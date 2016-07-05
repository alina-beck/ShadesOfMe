package ava.shadesofme;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class GameStateTest {

    private GameState gameState;
    private Location mockLocation = Mockito.mock(Location.class);
    private DashboardViewModel mockViewModel = Mockito.mock(DashboardViewModel.class);

    @Before
    public void setUp() {
        gameState = new GameState("14:00", mockLocation);
    }

    /**
     * Time
     */

    @Test
    public void currentGameTimeAdvancesGivenNumberOfMinutes() {
        gameState.advanceTimeBy(30);
        assertEquals("14:30", gameState.getCurrentTime());
    }

    @Test
    public void currentGameTimeAdvancesMoreThanSixtyMinutes() {
        gameState.advanceTimeBy(125);
        assertEquals("16:05", gameState.getCurrentTime());
    }

    @Test
    public void currentGameTimeAdvancesPastTwentyFourHours() {
        gameState.advanceTimeBy(690);
        assertEquals("01:30", gameState.getCurrentTime());
    }

    /**
     * Observable Stuff
     */

    @Test
    public void observersCanRegister() {
        gameState.addObserver(mockViewModel);
        assertEquals(1, gameState.countObservers());
    }

    @Test
    public void observersCanUnregister() {
        gameState.addObserver(mockViewModel);
        gameState.deleteObserver(mockViewModel);
        assertEquals(0, gameState.countObservers());
    }

    @Test
    public void observersAreNotifiedUponChange() {
        gameState.addObserver(mockViewModel);
        gameState.advanceTimeBy(30);
        verify(mockViewModel, times(1)).update(gameState, null);
    }
}
