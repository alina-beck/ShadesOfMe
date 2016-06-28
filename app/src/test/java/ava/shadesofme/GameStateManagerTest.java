package ava.shadesofme;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GameStateManagerTest {

    private GameStateManager gameStateManager;
    private Player mockPlayer = Mockito.mock(Player.class);
    private Location mockLocation = Mockito.mock(Location.class);
    private Item mockItem = Mockito.mock(Item.class);
    private ArrayList<Item> mockItems = new ArrayList<>();
    private EquipmentManager equipmentManager = Mockito.mock(EquipmentManager.class);

    @Before
    public void setUp() {
        gameStateManager = new GameStateManager("14:00", mockPlayer, mockLocation, equipmentManager);
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
    public void currentGameTimeAdvancesGivenNumberOfMinutes() {
        gameStateManager.advanceTimeBy(30);
        assertEquals("14:30", gameStateManager.getCurrentTime());
    }

    @Test
    public void currentGameTimeAdvancesMoreThanSixtyMinutes() {
        gameStateManager.advanceTimeBy(125);
        assertEquals("16:05", gameStateManager.getCurrentTime());
    }

    @Test
    public void currentGameTimeAdvancesPastTwentyFourHours() {
        gameStateManager.advanceTimeBy(690);
        assertEquals("01:30", gameStateManager.getCurrentTime());
    }

    @Test
    public void updatesPlayerStatsWhenTimeAdvances() {
        gameStateManager.advanceTimeBy(30);
        verify(mockPlayer).updateStats(30);
    }

    /**
     * Location
     */

    @Test
    public void currentLocationChangesOnGoToAction() {
        Location newLocation = Mockito.mock(Location.class);
        gameStateManager.goToLocation(newLocation, 30);
        assertEquals(newLocation, gameStateManager.getCurrentLocation());
    }

    @Test
    public void whenLocationIsSearchedReturnsListOfItems() {
        setUpListOfItemsForLocation();
        assertEquals(mockItems, gameStateManager.searchLocation(mockLocation));
    }

    @Test
    public void whenLocationIsSearchedTimeAdvances() {
        setUpListOfItemsForLocation();
        gameStateManager.searchLocation(mockLocation);
        assertEquals("14:45", gameStateManager.getCurrentTime());
    }

    @Test
    public void whenSearchedLocationStateIsUpdated() {
        gameStateManager.searchLocation(mockLocation);
        verify(mockLocation).setSearched(true);
    }

    @Test
    public void searchTimeIsCalculatedFromNumberOfItems() {
        setUpListOfItemsForLocation();
        mockItems.add(Mockito.mock(Item.class));
        gameStateManager.searchLocation(mockLocation);
        assertEquals("14:50", gameStateManager.getCurrentTime());
    }

    @Test
    public void whenLocationHasBeenSearchedReturnsItemsImmediately() {
        when(mockLocation.isSearched()).thenReturn(true);
        gameStateManager.searchLocation(mockLocation);
        assertEquals("14:00", gameStateManager.getCurrentTime());
    }


    /**
     * Item
     */

    @Test
    public void timeAdvancesOnItemUse() {
        when(mockItem.getUseTime()).thenReturn(30);
        gameStateManager.useItem(mockItem);
        assertEquals("14:30", gameStateManager.getCurrentTime());
    }

    @Test
    public void satietyIsUpdatedOnItemUse() {
        gameStateManager.useItem(mockItem);
        verify(mockPlayer).updateSatiety(Mockito.anyInt());
    }

    @Test
    public void energyIsUpdatedOnItemUse() {
        gameStateManager.useItem(mockItem);
        verify(mockPlayer).updateEnergy(Mockito.anyInt());
    }

    @Test
    public void healthIsUpdatedOnItemUse() {
        gameStateManager.useItem(mockItem);
        verify(mockPlayer).updateEnergy(Mockito.anyInt());
    }

    @Test
    public void alertsEquipmentManagerWhenItemIsPickedUp() {
        gameStateManager.pickUpItem(mockItem);
        verify(equipmentManager).add(mockItem);
    }

    @Test
    public void removesItemFromLocationWhenSuccessfullyPickedUp() {
        // TODO: remove from location only if pickUpItem was successful
    }

    @Test
    public void alertsEquipmentManagerWhenItemIsUsed() {
        // TODO: distinguish between reusable and single-use items
        gameStateManager.useItem(mockItem);
        verify(equipmentManager).remove(mockItem);
    }

    @Test
    public void sendsNewItemToEquipmentManagerOnItemUpgrade() {
        Item upgradeStage = Mockito.mock(Item.class);
        when(mockItem.getUpgradeStage()).thenReturn(upgradeStage);
        gameStateManager.upgradeItem(mockItem);
        verify(equipmentManager).replace(mockItem, upgradeStage);
    }

    @Test
    public void doesNotUpgradeUnlessAllConditionsAreMet() {
        // TODO: how to set and check for conditions?
    }

    @Test
    public void advancesTimeOnUpgrade() {
        when(mockItem.getUpgradeTime()).thenReturn(30);
        gameStateManager.upgradeItem(mockItem);
        assertEquals("14:30", gameStateManager.getCurrentTime());
    }

}
