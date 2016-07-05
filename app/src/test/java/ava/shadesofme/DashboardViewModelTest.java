package ava.shadesofme;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DashboardViewModelTest {

    private DashboardViewModel dashboardViewModel;
    private GameManager mockGameManager = Mockito.mock(GameManager.class);
    private GameState mockGameState = Mockito.mock(GameState.class);
    private Player mockPlayer = Mockito.mock(Player.class);
    private Location mockLocation = Mockito.mock(Location.class);


    @Before
    public void setUp() {
        when(mockGameManager.getPlayer()).thenReturn(mockPlayer);
        when(mockGameManager.getGameState()).thenReturn(mockGameState);
        when(mockGameState.getCurrentLocation()).thenReturn(mockLocation);
        when(mockGameState.getCurrentTime()).thenReturn("12:00");
        when(mockLocation.getName()).thenReturn("Test Home");
        when(mockPlayer.getMaxSatiety()).thenReturn(100);
        when(mockPlayer.getMaxEnergy()).thenReturn(100);
        when(mockPlayer.getMaxHealth()).thenReturn(100);
        when(mockPlayer.getCurrentSatiety()).thenReturn(30);
        when(mockPlayer.getCurrentEnergy()).thenReturn(50);
        when(mockPlayer.getCurrentHealth()).thenReturn(80);
        dashboardViewModel = new DashboardViewModel(mockGameManager);
    }

    /**
     * Construction
     */

    @Test
    public void setsUpCurrentLocationInConstructor() {
        assertEquals("Test Home", dashboardViewModel.getCurrentLocation());
    }

    @Test
    public void setsUpCurrentTimeInConstructor() {
        assertEquals("12:00", dashboardViewModel.getCurrentTime());
    }

    @Test
    public void setsUpMaxSatietyInConstructor() {
        assertEquals("100", dashboardViewModel.getMaxSatiety());
    }

    @Test
    public void setsUpMaxEnergyInConstructor() {
        assertEquals("100", dashboardViewModel.getMaxEnergy());
    }

    @Test
    public void setsUpMaxHealthInConstructor() {
        assertEquals("100", dashboardViewModel.getMaxHealth());
    }

    @Test
    public void setsUpCurrentSatietyInConstructor() {
        assertEquals("30", dashboardViewModel.getCurrentSatiety());
    }

    @Test
    public void setsUpCurrentEnergyInConstructor() {
        assertEquals("50", dashboardViewModel.getCurrentEnergy());
    }

    @Test
    public void setsUpCurrentHealthInConstructor() {
        assertEquals("80", dashboardViewModel.getCurrentHealth());
    }

    /**
     * Reactions to user input
     */

    @Test
    public void tellsGameStateManagerToUpdateWhenRestClicked() {
        dashboardViewModel.restButtonClicked();
        verify(mockGameManager).advanceTimeBy(30);
    }

    /**
     * Updates when Observable changes
     */

    @Test
    public void updatesCurrentSatietyWhenPlayerStatsChange() {
        when(mockPlayer.getCurrentSatiety()).thenReturn(10);
        dashboardViewModel.update(mockPlayer, null);
        assertEquals("10", dashboardViewModel.getCurrentSatiety());
    }

    @Test
    public void updatesCurrentEnergyWhenPlayerStatsChange() {
        when(mockPlayer.getCurrentEnergy()).thenReturn(10);
        dashboardViewModel.update(mockPlayer, null);
        assertEquals("10", dashboardViewModel.getCurrentEnergy());
    }

    @Test
    public void updatesCurrentHealthWhenPlayerStatsChange() {
        when(mockPlayer.getCurrentHealth()).thenReturn(10);
        dashboardViewModel.update(mockPlayer, null);
        assertEquals("10", dashboardViewModel.getCurrentHealth());
    }

}
