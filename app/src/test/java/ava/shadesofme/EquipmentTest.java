package ava.shadesofme;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class EquipmentTest {

    private Equipment equipment;
    private Item mockItem = Mockito.mock(Item.class);
    private Item mockItemTwo = Mockito.mock(Item.class);
    private Item mockItemThree = Mockito.mock(Item.class);
    private List<Item> mockItems = new ArrayList<>();
    private InventoryViewModel mockViewModel = Mockito.mock(InventoryViewModel.class);

    /**
     * Setup
     */

    @Before
    public void setUp() {
        equipment = new Equipment(30, 30, 2);
    }

    private void setUpMockItemsInEquipment() {
        mockItems.add(mockItem);
        mockItems.add(mockItemTwo);
        equipment.setItems(mockItems);
    }

    private void setUpMockItemWeightAndVolume() {
        when(mockItem.getWeight()).thenReturn(5);
        when(mockItem.getVolume()).thenReturn(5);
    }

    /**
     * Placing items in equipment
     */

    @Test
    public void returnsTrueWhenItemWasPlacedInEquipment() {
        assertTrue(equipment.add(mockItem));
    }

    @Test
    public void returnsFalseWhenItemCannotBePlacedInEquipment() {
        when(mockItem.getWeight()).thenReturn(500);
        assertFalse(equipment.add(mockItem));
    }

    @Test
    public void whenItemIsAddedTheCurrentWeightIncreases() {
        setUpMockItemWeightAndVolume();

        equipment.add(mockItem);
        assertEquals(5, equipment.getCurrentTotalWeight());
    }

    @Test
    public void whenItemIsAddedTheCurrentVolumeIncreases() {
        setUpMockItemWeightAndVolume();

        equipment.add(mockItem);
        assertEquals(5, equipment.getCurrentTotalVolume());
    }

    @Test
    public void itemCanNotBeAddedWhenExceedingTotalMaxWeight() {
        setUpMockItemWeightAndVolume();

        equipment.setCurrentTotalWeight(28);
        assertFalse(equipment.add(mockItem));
    }

    @Test
    public void itemCanNotBeAddedWhenExceedingTotalMaxVolume() {
        setUpMockItemWeightAndVolume();

        equipment.setCurrentTotalVolume(28);
        assertFalse(equipment.add(mockItem));
    }

    @Test
    public void itemCanNotBetAddedWhenExceedingTotalSlots() {
        setUpMockItemsInEquipment();
        assertFalse(equipment.add(mockItemThree));
    }

    /**
     * Removing and replacing items in equipment
     */

    @Test
    public void itemIsRemovedFromEquipment() {
        setUpMockItemsInEquipment();
        equipment.remove(mockItemTwo);
        assertFalse(mockItems.contains(mockItemTwo));
    }

    @Test
    public void upgradesItemByReplacingIt() {
        // TODO: check for conditions before upgrading, rename method to upgrade
        setUpMockItemsInEquipment();
        Item mockUpgradeItem = Mockito.mock(Item.class);

        equipment.replace(mockItem, mockUpgradeItem);
        assertTrue(mockItems.contains(mockUpgradeItem));
    }

    @Test
    public void whenItemIsRemovedTheCurrentWeightDecreases() {
        setUpMockItemsInEquipment();
        setUpMockItemWeightAndVolume();

        equipment.setCurrentTotalWeight(10);
        equipment.remove(mockItem);
        assertEquals(5, equipment.getCurrentTotalWeight());
    }

    @Test
    public void whenItemIsRemovedTheCurrentVolumeDecreases() {
        setUpMockItemsInEquipment();
        setUpMockItemWeightAndVolume();

        equipment.setCurrentTotalVolume(10);
        equipment.remove(mockItem);
        assertEquals(5, equipment.getCurrentTotalVolume());
    }

    /**
     * Handling stackable items
     */

    @Test
    public void stackableItemsAreStackedInSameSlot() {
        setUpMockItemsInEquipment();
        when(mockItem.getStackable()).thenReturn(3);
        assertTrue(equipment.add(mockItem));
    }

    @Test
    public void stackableItemsStackNeverHigherThanMaxAmount() {
        setUpMockItemsInEquipment();
        when(mockItem.getStackable()).thenReturn(3);
        equipment.add(mockItem);
        equipment.add(mockItem);
        assertFalse(equipment.add(mockItem));
    }

    /**
     * Observable stuff
     */

    @Test
    public void observersCanRegister() {
        equipment.addObserver(mockViewModel);
        assertEquals(1, equipment.countObservers());
    }

    @Test
    public void observersCanUnregister() {
        equipment.addObserver(mockViewModel);
        equipment.deleteObserver(mockViewModel);
        assertEquals(0, equipment.countObservers());
    }

    @Test
    public void observersAreNotifiedWhenItemIsAdded() {
        equipment.addObserver(mockViewModel);
        equipment.add(mockItem);
        verify(mockViewModel).update(equipment, null);
    }

    @Test
    public void observersAreNotifiedWhenItemIsRemoved() {
        setUpMockItemsInEquipment();
        equipment.addObserver(mockViewModel);
        equipment.remove(mockItem);
        verify(mockViewModel).update(equipment, null);
    }
}
