package ava.shadesofme;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class EquipmentManagerTest {

    private EquipmentManager equipmentManager;
    private Item mockItem = Mockito.mock(Item.class);
    private Player mockPlayer = Mockito.mock(Player.class);
    private EquipmentSlot mockEquipmentSlot = Mockito.mock(EquipmentSlot.class);
    private List<EquipmentSlot> mockEquipmentSlots = new ArrayList<>();

    @Before
    public void setUp() {
        equipmentManager = new EquipmentManager(mockPlayer);
        mockEquipmentSlots.add(mockEquipmentSlot);
        mockEquipmentSlots.add(mockEquipmentSlot);
    }

    @Test
    public void returnsTrueWhenItemWasPlacedInEquipmentSlot() {
        when(mockPlayer.getEquipmentSlots()).thenReturn(mockEquipmentSlots);
        assertTrue(equipmentManager.add(mockItem));
    }

    @Test
    public void returnsFalseWhenNoEquipmentSlotIsFree() {
        when(mockEquipmentSlot.getItem()).thenReturn(mockItem);
        assertFalse(equipmentManager.add(mockItem));
    }

    @Test
    public void tellsFreeEquipmentSlotToTakeItem() {
        when(mockPlayer.getEquipmentSlots()).thenReturn(mockEquipmentSlots);
        equipmentManager.add(mockItem);
        verify(mockEquipmentSlot).putItem(mockItem);
    }

    @Test
    public void tellsEquipmentSlotThatHasItemToRemoveItem() {
        when(mockPlayer.getEquipmentSlots()).thenReturn(mockEquipmentSlots);
        when(mockEquipmentSlot.getItem()).thenReturn(mockItem);
        equipmentManager.remove(mockItem);
        verify(mockEquipmentSlot).removeItem();
    }

    @Test
    public void tellsEquipmentSlotThatHasItemToReplaceItem() {
        when(mockPlayer.getEquipmentSlots()).thenReturn(mockEquipmentSlots);
        when(mockEquipmentSlot.getItem()).thenReturn(mockItem);
        Item mockUpgradeItem = Mockito.mock(Item.class);

        equipmentManager.replace(mockItem, mockUpgradeItem);
        verify(mockEquipmentSlot).putItem(mockUpgradeItem);
    }
}
