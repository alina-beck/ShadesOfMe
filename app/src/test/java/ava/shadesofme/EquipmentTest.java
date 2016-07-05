package ava.shadesofme;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class EquipmentTest {

    private Equipment equipment;
    private Item mockItem = Mockito.mock(Item.class);
    private Item mockItemTwo = Mockito.mock(Item.class);
    private Item mockItemThree = Mockito.mock(Item.class);
    private List<Item> mockItems = new ArrayList<>();

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
     * Placing items in equipment slots
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
     * Handling stackable items
     */

//    @Test
//    public void stackableItemsAreStackedInSameSlot() {
//        // TODO: first check for same item and stackItem if possible, then add to new slot when stackItem is full
//        setUpTwoEmptyEquipmentSlots();
//        setUpEquipmentSlotWithHighMaxWeightAndVolume();
//        setUpMockItemsInEquipment();
//        when(mockItem.getStackable()).thenReturn(3);
//        when(mockEquipmentSlot.getCurrentStack()).thenReturn(1);
//        when(mockEquipmentSlot.getStackable()).thenReturn(true);
//
//        equipment.add(mockItem);
//        verify(mockEquipmentSlot).stackItem(mockItem);
//    }
//
//    @Test
//    public void stackableItemsStackNeverHigherThanMaxAmount() {
//        setUpTwoEmptyEquipmentSlots();
//        setUpEquipmentSlotWithHighMaxWeightAndVolume();
//        setUpMockItemsInEquipment();
//        when(mockItem.getStackable()).thenReturn(3);
//        when(mockEquipmentSlot.getCurrentStack()).thenReturn(3);
//        when(mockEquipmentSlot.getStackable()).thenReturn(true);
//
//        assertFalse(equipment.add(mockItem));
//    }
}
