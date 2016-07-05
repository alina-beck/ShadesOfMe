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
    private EquipmentSlot mockEquipmentSlot = Mockito.mock(EquipmentSlot.class);
    private List<EquipmentSlot> mockEquipmentSlots = new ArrayList<>();

    @Before
    public void setUp() {
        equipment = new Equipment(30, 30, mockEquipmentSlots);
    }

    private void setUpTwoEmptyEquipmentSlots() {
        mockEquipmentSlots.add(mockEquipmentSlot);
        mockEquipmentSlots.add(mockEquipmentSlot);
    }

    private void setUpItemsInEquipmentSlots() {
        when(mockEquipmentSlot.getItem()).thenReturn(mockItem);
    }

    private void setUpMockItemWeightAndVolume() {
        when(mockItem.getWeight()).thenReturn(5);
        when(mockItem.getVolume()).thenReturn(5);
    }

    private void setUpEquipmentSlotWithHighMaxWeightAndVolume() {
        when(mockEquipmentSlot.getMaxWeight()).thenReturn(10);
        when(mockEquipmentSlot.getMaxVolume()).thenReturn(10);
    }

    private void setUpEquipmentSlotWithLowMaxWeightAndVolume() {
        when(mockEquipmentSlot.getMaxWeight()).thenReturn(3);
        when(mockEquipmentSlot.getMaxVolume()).thenReturn(3);
    }

    @Test
    public void returnsTrueWhenItemWasPlacedInEquipmentSlot() {
        setUpTwoEmptyEquipmentSlots();
        assertTrue(equipment.add(mockItem));
    }

    @Test
    public void returnsFalseWhenNoEquipmentSlotIsFree() {
        setUpTwoEmptyEquipmentSlots();
        setUpItemsInEquipmentSlots();
        assertFalse(equipment.add(mockItem));
    }

    @Test
    public void tellsFreeEquipmentSlotToTakeItem() {
        setUpTwoEmptyEquipmentSlots();
        equipment.add(mockItem);
        verify(mockEquipmentSlot).putItem(mockItem);
    }

    @Test
    public void tellsEquipmentSlotThatHasItemToRemoveItem() {
        // TODO: what happens if multiple slots contain the same item?
        setUpTwoEmptyEquipmentSlots();
        setUpItemsInEquipmentSlots();

        equipment.remove(mockItem);
        verify(mockEquipmentSlot).removeItem();
    }

    @Test
    public void tellsEquipmentSlotThatHasItemToReplaceItem() {
        setUpTwoEmptyEquipmentSlots();
        setUpItemsInEquipmentSlots();
        Item mockUpgradeItem = Mockito.mock(Item.class);

        equipment.replace(mockItem, mockUpgradeItem);
        verify(mockEquipmentSlot).putItem(mockUpgradeItem);
    }

    @Test
    public void whenItemIsAddedTheCurrentWeightIncreases() {
        setUpTwoEmptyEquipmentSlots();
        setUpEquipmentSlotWithHighMaxWeightAndVolume();
        setUpMockItemWeightAndVolume();

        equipment.add(mockItem);
        assertEquals(5, equipment.getCurrentTotalWeight());
    }

    @Test
    public void whenItemIsAddedTheCurrentVolumeIncreases() {
        setUpTwoEmptyEquipmentSlots();
        setUpEquipmentSlotWithHighMaxWeightAndVolume();
        setUpMockItemWeightAndVolume();

        equipment.add(mockItem);
        assertEquals(5, equipment.getCurrentTotalVolume());
    }

    @Test
    public void whenItemIsRemovedTheCurrentWeightDecreases() {
        setUpTwoEmptyEquipmentSlots();
        setUpItemsInEquipmentSlots();
        setUpMockItemWeightAndVolume();

        equipment.setCurrentTotalWeight(10);
        equipment.remove(mockItem);
        assertEquals(5, equipment.getCurrentTotalWeight());
    }

    @Test
    public void whenItemIsRemovedTheCurrentVolumeDecreases() {
        setUpTwoEmptyEquipmentSlots();
        setUpItemsInEquipmentSlots();
        setUpMockItemWeightAndVolume();

        equipment.setCurrentTotalVolume(10);
        equipment.remove(mockItem);
        assertEquals(5, equipment.getCurrentTotalVolume());
    }

    @Test
    public void itemCanNotBeAddedWhenExceedingTotalMaxWeight() {
        setUpTwoEmptyEquipmentSlots();
        setUpMockItemWeightAndVolume();

        equipment.setCurrentTotalWeight(28);
        assertFalse(equipment.add(mockItem));
    }

    @Test
    public void itemCanNotBeAddedWhenExceedingTotalMaxVolume() {
        setUpTwoEmptyEquipmentSlots();
        setUpMockItemWeightAndVolume();

        equipment.setCurrentTotalVolume(28);
        assertFalse(equipment.add(mockItem));

    }

    @Test
    public void itemCanNotBeAddedToSlotWhenExceedingSlotsMaxWeight() {
        setUpTwoEmptyEquipmentSlots();
        setUpMockItemWeightAndVolume();
        setUpEquipmentSlotWithLowMaxWeightAndVolume();

        assertFalse(equipment.add(mockItem));

    }

    @Test
    public void itemCanNotBeAddedToSlotWhenExceedingSlotsMaxVolume() {
        setUpTwoEmptyEquipmentSlots();
        setUpMockItemWeightAndVolume();
        setUpEquipmentSlotWithLowMaxWeightAndVolume();

        assertFalse(equipment.add(mockItem));
    }

    @Test
    public void stackableItemsAreStackedInSameSlot() {
        // TODO: first check for same item and stackItem if possible, then add to new slot when stackItem is full
        setUpTwoEmptyEquipmentSlots();
        setUpEquipmentSlotWithHighMaxWeightAndVolume();
        setUpItemsInEquipmentSlots();
        when(mockItem.getStackable()).thenReturn(3);
        when(mockEquipmentSlot.getCurrentStack()).thenReturn(1);
        when(mockEquipmentSlot.getStackable()).thenReturn(true);

        equipment.add(mockItem);
        verify(mockEquipmentSlot).stackItem(mockItem);
    }

    @Test
    public void stackableItemsStackNeverHigherThanMaxAmount() {
        setUpTwoEmptyEquipmentSlots();
        setUpEquipmentSlotWithHighMaxWeightAndVolume();
        setUpItemsInEquipmentSlots();
        when(mockItem.getStackable()).thenReturn(3);
        when(mockEquipmentSlot.getCurrentStack()).thenReturn(3);
        when(mockEquipmentSlot.getStackable()).thenReturn(true);

        assertFalse(equipment.add(mockItem));
    }

    @Test
    public void stackableItemsCanNotBeStackedWhenSlotDoesNotAllowIt() {
        setUpTwoEmptyEquipmentSlots();
        setUpEquipmentSlotWithHighMaxWeightAndVolume();
        setUpItemsInEquipmentSlots();
        when(mockItem.getStackable()).thenReturn(3);
        when(mockEquipmentSlot.getCurrentStack()).thenReturn(1);
        when(mockEquipmentSlot.getStackable()).thenReturn(false);

        assertFalse(equipment.add(mockItem));
    }
}
