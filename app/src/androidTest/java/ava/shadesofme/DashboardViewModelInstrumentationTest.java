package ava.shadesofme;

import android.os.Parcel;
import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
public class DashboardViewModelInstrumentationTest {

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
    public void isPackedAndUnpackedAsParcelSuccessfully() {
        Parcel parcel = Parcel.obtain();
        dashboardViewModel.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        DashboardViewModel parceledViewModel = DashboardViewModel.CREATOR.createFromParcel(parcel);
        assertEquals(dashboardViewModel, parceledViewModel);
    }
}
