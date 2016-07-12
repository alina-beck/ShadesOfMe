package ava.shadesofme;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import ava.shadesofme.DataModels.Item;
import ava.shadesofme.DataModels.Location;
import ava.shadesofme.GameState.CurrentState;
import ava.shadesofme.GameState.Equipment;
import ava.shadesofme.GameState.Player;

import static org.junit.Assert.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GameManagerTest {

    private GameManager gameManager;
    private CurrentState mockCurrentState = Mockito.mock(CurrentState.class);
    private Player mockPlayer = Mockito.mock(Player.class);
    private Location mockLocation = Mockito.mock(Location.class);
    private Item mockItem = Mockito.mock(Item.class);
    private ArrayList<Item> mockItems = new ArrayList<>();
    private Equipment equipment = Mockito.mock(Equipment.class);

    @Before
    public void setUp() {
        gameManager = new GameManager(mockCurrentState, mockPlayer, equipment);
    }

    private void setUpListOfItemsForLocation() {
        mockItems.add(mockItem);
        mockItems.add(mockItem);
        mockItems.add(mockItem);
        when(mockLocation.getItems()).thenReturn(mockItems);
    }

    /**
     * Game Time
     */

    @Test
    public void updatesPlayerStatsWhenTimeAdvances() {
        gameManager.advanceTimeBy(30);
        verify(mockPlayer).updateStatsByTime(30);
    }

    @Test
    public void tellsGameStateToAdvanceTime() {
        gameManager.advanceTimeBy(30);
        verify(mockCurrentState).advanceTimeBy(30);
    }

    /**
     * Location
     */

    @Test
    public void currentLocationChangesOnGoToAction() {
        Location newLocation = Mockito.mock(Location.class);
        gameManager.goToLocation(newLocation, 30);
        verify(mockCurrentState).setCurrentLocation(newLocation);
    }

    @Test
    public void whenLocationIsSearchedReturnsListOfItems() {
        setUpListOfItemsForLocation();
        assertEquals(mockItems, gameManager.searchLocation(mockLocation));
    }

    @Test
    public void whenLocationIsSearchedTimeAdvances() {
        setUpListOfItemsForLocation();
        gameManager.searchLocation(mockLocation);
        verify(mockCurrentState).advanceTimeBy(45);
    }

    @Test
    public void whenSearchedLocationStateIsUpdated() {
        gameManager.searchLocation(mockLocation);
        verify(mockLocation).setSearched(true);
    }

    @Test
    public void searchTimeIsCalculatedFromNumberOfItems() {
        setUpListOfItemsForLocation();
        mockItems.add(Mockito.mock(Item.class));
        gameManager.searchLocation(mockLocation);
        verify(mockCurrentState).advanceTimeBy(50);
    }

    @Test
    public void whenLocationHasBeenSearchedReturnsItemsImmediately() {
        when(mockLocation.isSearched()).thenReturn(true);
        gameManager.searchLocation(mockLocation);
        verify(mockCurrentState, never()).advanceTimeBy(Mockito.anyInt());
    }


    /**
     * Item
     */

    @Test
    public void timeAdvancesOnItemUse() {
        when(mockItem.getUseTime()).thenReturn(30);
        gameManager.useItem(mockItem);
        verify(mockCurrentState).advanceTimeBy(30);
    }

    @Test
    public void alertsPlayerWhenItemIsPickedUp() {
        gameManager.useItem(mockItem);
        verify(mockPlayer).updateStatsWithItem(mockItem);
    }

    @Test
    public void alertsEquipmentManagerWhenItemIsPickedUp() {
        gameManager.pickUpItem(mockItem);
        verify(equipment).add(mockItem);
    }

    @Test
    public void removesItemFromLocationWhenSuccessfullyPickedUp() {
        // TODO: remove from location only if pickUpItem was successful
    }

    @Test
    public void alertsEquipmentManagerWhenItemIsUsed() {
        // TODO: distinguish between reusable and single-use items
        gameManager.useItem(mockItem);
        verify(equipment).remove(mockItem);
    }

    @Test
    public void sendsNewItemToEquipmentManagerOnItemUpgrade() {
        Item upgradeStage = Mockito.mock(Item.class);
        when(mockItem.getUpgradeStage()).thenReturn(upgradeStage);
        gameManager.upgradeItem(mockItem);
        verify(equipment).replace(mockItem, upgradeStage);
    }

    @Test
    public void doesNotUpgradeUnlessAllConditionsAreMet() {
        // TODO: how to set and check for conditions?
    }

    @Test
    public void advancesTimeOnUpgrade() {
        when(mockItem.getUpgradeTime()).thenReturn(30);
        gameManager.upgradeItem(mockItem);
        verify(mockCurrentState).advanceTimeBy(30);
    }

}
