package ava.shadesofme;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class EquipmentSlotTest {

    private EquipmentSlot equipmentSlot;
    private Item mockItem = Mockito.mock(Item.class);

    @Before
    public void setUp() {
        equipmentSlot = new EquipmentSlot(5, 5, true);
    }

    @Test
    public void replacesStoredItemWithNewItem() {
        // TODO: or only put item when slot is empty (manager removes other item first) and return false when filled?
        equipmentSlot.putItem(mockItem);
        assertEquals(mockItem, equipmentSlot.getItem());
    }

    @Test
    public void removesStoredItem() {
        equipmentSlot.putItem(mockItem);
        equipmentSlot.removeItem();
        assertEquals(null, equipmentSlot.getItem());
    }

    @Test
    public void whenItemIsPutInSlotStackIsSetToOne() {
        equipmentSlot.putItem(mockItem);
        assertEquals(1, equipmentSlot.getCurrentStack());
    }

    @Test
    public void whenItemIsStackedCurrentStackIncreasesByOne() {
        equipmentSlot.putItem(mockItem);
        equipmentSlot.stackItem(mockItem);
        assertEquals(2, equipmentSlot.getCurrentStack());
    }
}
