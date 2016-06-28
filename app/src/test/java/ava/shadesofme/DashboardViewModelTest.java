package ava.shadesofme;

import android.os.Parcel;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class DashboardViewModelTest {

    private DashboardViewModel dashboardViewModel;
    private GameStateManager mockGameStateManager = Mockito.mock(GameStateManager.class);
    private Player mockPlayer = Mockito.mock(Player.class);
    private Location mockLocation = Mockito.mock(Location.class);


    @Before
    public void setUp() {
        when(mockGameStateManager.getPlayer()).thenReturn(mockPlayer);
        when(mockGameStateManager.getCurrentLocation()).thenReturn(mockLocation);
        when(mockGameStateManager.getCurrentTime()).thenReturn("12:00");
        when(mockLocation.getName()).thenReturn("Test Home");
        when(mockPlayer.getMaxSatiety()).thenReturn(100);
        when(mockPlayer.getMaxEnergy()).thenReturn(100);
        when(mockPlayer.getMaxHealth()).thenReturn(100);
        when(mockPlayer.getCurrentSatiety()).thenReturn(30);
        when(mockPlayer.getCurrentEnergy()).thenReturn(50);
        when(mockPlayer.getCurrentHealth()).thenReturn(80);
        dashboardViewModel = new DashboardViewModel(mockGameStateManager);
    }

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

}
