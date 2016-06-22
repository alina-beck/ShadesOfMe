package ava.shadesofme;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DashboardPresenterTest {

    private DashboardPresenter presenter;
    private DashboardActivity mockActivity = Mockito.mock(DashboardActivity.class);
    private Player mockPlayer = Mockito.mock(Player.class);
    private GameStateManager gameStateManager = Mockito.mock(GameStateManager.class);

    @Before
    public void setUp() {
        presenter = new DashboardPresenter(mockActivity, gameStateManager);
        when(gameStateManager.getPlayer()).thenReturn(mockPlayer);
    }

    private void setUpMockPlayerReturningCurrentStats() {
        when(mockPlayer.getCurrentSatiety()).thenReturn(30);
        when(mockPlayer.getCurrentEnergy()).thenReturn(30);
        when(mockPlayer.getCurrentHealth()).thenReturn(80);
    }

    @Test
    public void displayMaxSatietyOnCreate() {
        when(mockPlayer.getMaxSatiety()).thenReturn(100);
        presenter.onActivityCreated();
        verify(mockActivity).displayMaxSatiety(100);
    }

    @Test
    public void displayMaxEnergyOnCreate() {
        when(mockPlayer.getMaxEnergy()).thenReturn(100);
        presenter.onActivityCreated();
        verify(mockActivity).displayMaxEnergy(100);
    }

    @Test
    public void displayMaxHealthOnCreate() {
        when(mockPlayer.getMaxHealth()).thenReturn(100);
        presenter.onActivityCreated();
        verify(mockActivity).displayMaxHealth(100);
    }

    @Test
    public void displayInitialSatietyOnCreate() {
        setUpMockPlayerReturningCurrentStats();
        presenter.onActivityCreated();
        verify(mockActivity).displayCurrentSatiety(30);
    }

    @Test
    public void displayInitialEnergyOnCreate() {
        setUpMockPlayerReturningCurrentStats();
        presenter.onActivityCreated();
        verify(mockActivity).displayCurrentEnergy(30);
    }

    @Test
    public void displayInitialHealthOnCreate() {
        setUpMockPlayerReturningCurrentStats();
        presenter.onActivityCreated();
        verify(mockActivity).displayCurrentHealth(80);
    }

    @Test
    public void updateSatietyWhenAlerted() {
        setUpMockPlayerReturningCurrentStats();
        presenter.updatePlayerStats(mockPlayer);
        verify(mockActivity).displayCurrentSatiety(30);
    }

    @Test
    public void updateEnergyWhenAlerted() {
        setUpMockPlayerReturningCurrentStats();
        presenter.updatePlayerStats(mockPlayer);
        verify(mockActivity).displayCurrentEnergy(30);
    }

    @Test
    public void updateHealthWhenAlerted() {
        setUpMockPlayerReturningCurrentStats();
        presenter.updatePlayerStats(mockPlayer);
        verify(mockActivity).displayCurrentHealth(80);
    }
}
