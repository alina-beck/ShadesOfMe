package ava.shadesofme.Dashboard;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import ava.shadesofme.Content.ContentViewModel;
import ava.shadesofme.Content.ContentViewModelDao;
import ava.shadesofme.DataModels.Item;
import ava.shadesofme.DataModels.Location;
import ava.shadesofme.GameManager;
import ava.shadesofme.GameState.CurrentState;
import ava.shadesofme.GameState.Player;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DashboardViewModelTest {

    private static final String LOCATION_NAME = "Test Home";
    private DashboardViewModel dashboardViewModel;
    private GameManager mockGameManager = Mockito.mock(GameManager.class);
    private ContentViewModelDao mockContentViewModelDao = Mockito.mock(ContentViewModelDao.class);
    private CurrentState mockCurrentState = Mockito.mock(CurrentState.class);
    private Player mockPlayer = Mockito.mock(Player.class);
    private Location mockLocation = Mockito.mock(Location.class);
    private Item mockItem = Mockito.mock(Item.class);
    private ContentViewModel mockContentViewModel = Mockito.mock(ContentViewModel.class);


    @Before
    public void setUp() {
        when(mockGameManager.getPlayer()).thenReturn(mockPlayer);
        when(mockGameManager.getCurrentState()).thenReturn(mockCurrentState);
        when(mockCurrentState.getCurrentLocation()).thenReturn(mockLocation);
        when(mockCurrentState.getCurrentTime()).thenReturn("12:00");
        when(mockCurrentState.getCurrentView()).thenReturn(mockContentViewModel);
        when(mockLocation.getName()).thenReturn(LOCATION_NAME);
        when(mockPlayer.getMaxSatiety()).thenReturn(100);
        when(mockPlayer.getMaxEnergy()).thenReturn(100);
        when(mockPlayer.getMaxHealth()).thenReturn(100);
        when(mockPlayer.getCurrentSatiety()).thenReturn(30);
        when(mockPlayer.getCurrentEnergy()).thenReturn(50);
        when(mockPlayer.getCurrentHealth()).thenReturn(80);
        when(mockContentViewModel.getTitle()).thenReturn("Test Title");
        dashboardViewModel = new DashboardViewModel(mockGameManager, mockContentViewModelDao);
    }

    /**
     * Construction
     */

    @Test
    public void setsUpCurrentTitleInConstructor() {
        assertEquals("Test Title", dashboardViewModel.getCurrentTitle());
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
     * Navigation and title changes
     */

    @Test
    public void showsNewTitleWhenViewModelChanges() {
        when(mockContentViewModel.getTitle()).thenReturn("Test Title");
        when(mockCurrentState.getCurrentView()).thenReturn(mockContentViewModel);
        dashboardViewModel.update(mockCurrentState, null);
        assertEquals("Test Title", dashboardViewModel.getCurrentTitle());
    }

    /**
     * Reactions to user actions
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

    @Test
    public void updatesCurrentTimeWhenGameStateChanges() {
        when(mockCurrentState.getCurrentTime()).thenReturn("17:00");
        dashboardViewModel.update(mockCurrentState, null);
        assertEquals("17:00", dashboardViewModel.getCurrentTime());
    }

}
