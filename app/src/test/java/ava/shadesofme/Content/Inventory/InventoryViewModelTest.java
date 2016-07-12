package ava.shadesofme.Content.Inventory;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import ava.shadesofme.Content.Inventory.InventoryViewModel;
import ava.shadesofme.DataModels.Item;
import ava.shadesofme.GameManager;
import ava.shadesofme.GameState.Equipment;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class InventoryViewModelTest {

    private InventoryViewModel inventoryViewModel;
    private GameManager mockGameManager = Mockito.mock(GameManager.class);
    private Equipment mockEquipment = Mockito.mock(Equipment.class);
    private Item mockItemOne = Mockito.mock(Item.class);
    private Item mockItemTwo = Mockito.mock(Item.class);
    private List<Item> mockItems = new ArrayList<>();

    @Before
    public void setUp() {
        when(mockEquipment.getMaxTotalWeight()).thenReturn(30);
        when(mockEquipment.getMaxTotalVolume()).thenReturn(30);
        when(mockEquipment.getTotalSlots()).thenReturn(2);
        when(mockEquipment.getCurrentTotalWeight()).thenReturn(10);
        when(mockEquipment.getCurrentTotalVolume()).thenReturn(10);
        when(mockGameManager.getEquipment()).thenReturn(mockEquipment);
        inventoryViewModel = new InventoryViewModel(mockGameManager);
    }

    private void setUpItemsInEquipment() {
        when(mockItemOne.getName()).thenReturn("Name One");
        when(mockItemOne.getWeight()).thenReturn(3);
        when(mockItemOne.getVolume()).thenReturn(3);
        when(mockItemTwo.getName()).thenReturn("Name Two");
        when(mockItemTwo.getWeight()).thenReturn(4);
        when(mockItemTwo.getVolume()).thenReturn(4);
        mockItems.add(mockItemOne);
        mockItems.add(mockItemTwo);
        when(mockEquipment.getItems()).thenReturn(mockItems);
        when(mockGameManager.getEquipment()).thenReturn(mockEquipment);
        inventoryViewModel = new InventoryViewModel(mockGameManager);
    }

    /**
     * Construction
     */

    @Test
    public void setsUpMaxWeightInConstructor() {
        assertEquals("30", inventoryViewModel.getMaxWeight());
    }

    @Test
    public void setsUpMaxVolumeInConstructor() {
        assertEquals("30", inventoryViewModel.getMaxVolume());
    }

    @Test
    public void setsUpMaxSlotsInConstructor() {
        assertEquals(2, inventoryViewModel.getNumberOfSlots());
    }

    @Test
    public void setsUpCurrentWeightInConstructor() {
        assertEquals("10", inventoryViewModel.getCurrentWeight());
    }

    @Test
    public void setsUpCurrentVolumeInConstructor() {
        assertEquals("10", inventoryViewModel.getCurrentVolume());
    }

    @Test
    public void setsUpListOfCarriedItemsInConstructor() {
        setUpItemsInEquipment();
        assertTrue(inventoryViewModel.getItems().get(0).containsValue("Name One"));
    }

    /**
     * Updates when Observable changes
     */

    @Test
    public void updatesMaxWeightWhenEquipmentChanges() {
        when(mockEquipment.getMaxTotalWeight()).thenReturn(35);
        inventoryViewModel.update(mockEquipment, null);
        assertEquals("35", inventoryViewModel.getMaxWeight());
    }

    @Test
    public void updatesMaxVolumeWhenEquipmentChanges() {
        when(mockEquipment.getMaxTotalVolume()).thenReturn(25);
        inventoryViewModel.update(mockEquipment, null);
        assertEquals("25", inventoryViewModel.getMaxVolume());
    }

    @Test
    public void updatesMaxSlotsWhenEquipmentChanges() {
        when(mockEquipment.getTotalSlots()).thenReturn(6);
        inventoryViewModel.update(mockEquipment, null);
        assertEquals(6, inventoryViewModel.getNumberOfSlots());
    }

    @Test
    public void updatesCurrentWeightWhenEquipmentChanges() {
        when(mockEquipment.getCurrentTotalWeight()).thenReturn(15);
        inventoryViewModel.update(mockEquipment, null);
        assertEquals("15", inventoryViewModel.getCurrentWeight());
    }

    @Test
    public void updatesCurrentVolumeWhenEquipmentChanges() {
        when(mockEquipment.getCurrentTotalVolume()).thenReturn(5);
        inventoryViewModel.update(mockEquipment, null);
        assertEquals("5", inventoryViewModel.getCurrentVolume());
    }

    @Test
    public void updatesItemListWhenEquipmentChanges() {
    // TODO: test this, find a better solution for setting up list for testing
    }

}
