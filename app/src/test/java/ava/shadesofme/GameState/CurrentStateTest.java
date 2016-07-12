package ava.shadesofme.GameState;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import ava.shadesofme.Dashboard.DashboardViewModel;
import ava.shadesofme.DataModels.Location;
import ava.shadesofme.GameState.CurrentState;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class CurrentStateTest {

    private CurrentState currentState;
    private Location mockLocation = Mockito.mock(Location.class);
    private DashboardViewModel mockViewModel = Mockito.mock(DashboardViewModel.class);

    @Before
    public void setUp() {
        currentState = new CurrentState("14:00", mockLocation);
    }

    /**
     * Time
     */

    @Test
    public void currentGameTimeAdvancesGivenNumberOfMinutes() {
        currentState.advanceTimeBy(30);
        assertEquals("14:30", currentState.getCurrentTime());
    }

    @Test
    public void currentGameTimeAdvancesMoreThanSixtyMinutes() {
        currentState.advanceTimeBy(125);
        assertEquals("16:05", currentState.getCurrentTime());
    }

    @Test
    public void currentGameTimeAdvancesPastTwentyFourHours() {
        currentState.advanceTimeBy(690);
        assertEquals("01:30", currentState.getCurrentTime());
    }

    /**
     * Observable Stuff
     */

    @Test
    public void observersCanRegister() {
        currentState.addObserver(mockViewModel);
        assertEquals(1, currentState.countObservers());
    }

    @Test
    public void observersCanUnregister() {
        currentState.addObserver(mockViewModel);
        currentState.deleteObserver(mockViewModel);
        assertEquals(0, currentState.countObservers());
    }

    @Test
    public void observersAreNotifiedUponChange() {
        currentState.addObserver(mockViewModel);
        currentState.advanceTimeBy(30);
        verify(mockViewModel, times(1)).update(currentState, null);
    }
}
