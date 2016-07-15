package ava.shadesofme.Dashboard;

import android.os.Parcel;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import ava.shadesofme.Content.ContentViewModelDao;
import ava.shadesofme.DataModels.Location;
import ava.shadesofme.GameManager;
import ava.shadesofme.GameState.CurrentState;
import ava.shadesofme.GameState.Player;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
public class DashboardViewModelInstrumentationTest {

    private DashboardViewModel dashboardViewModel;
    private GameManager mockGameManager = Mockito.mock(GameManager.class);
    private ContentViewModelDao mockContentViewModelDao = Mockito.mock(ContentViewModelDao.class);
    private CurrentState mockCurrentState = Mockito.mock(CurrentState.class);
    private Player mockPlayer = Mockito.mock(Player.class);
    private Location mockLocation = Mockito.mock(Location.class);

    @Before
    public void setUp() {
        when(mockGameManager.getPlayer()).thenReturn(mockPlayer);
        when(mockGameManager.getCurrentState()).thenReturn(mockCurrentState);
        when(mockCurrentState.getCurrentLocation()).thenReturn(mockLocation);
        when(mockCurrentState.getCurrentTime()).thenReturn("12:00");
        when(mockLocation.getName()).thenReturn("Test Home");
        when(mockPlayer.getMaxSatiety()).thenReturn(100);
        when(mockPlayer.getMaxEnergy()).thenReturn(100);
        when(mockPlayer.getMaxHealth()).thenReturn(100);
        when(mockPlayer.getCurrentSatiety()).thenReturn(30);
        when(mockPlayer.getCurrentEnergy()).thenReturn(50);
        when(mockPlayer.getCurrentHealth()).thenReturn(80);
        dashboardViewModel = new DashboardViewModel(mockGameManager, mockContentViewModelDao);
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
