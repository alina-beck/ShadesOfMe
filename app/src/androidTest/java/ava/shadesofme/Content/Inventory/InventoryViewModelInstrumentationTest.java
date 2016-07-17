package ava.shadesofme.Content.Inventory;

import android.os.Parcel;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import java.util.ArrayList;

import ava.shadesofme.DataModels.Item;
import ava.shadesofme.GameManager;
import ava.shadesofme.GameState.Equipment;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
public class InventoryViewModelInstrumentationTest {

    private InventoryViewModel inventoryViewModel;

    @Before
    public void setUp() {
        Equipment equipment = Mockito.mock(Equipment.class);
        when(equipment.getTotalSlots()).thenReturn(5);
        when(equipment.getMaxTotalWeight()).thenReturn(10);
        when(equipment.getMaxTotalVolume()).thenReturn(10);
        when(equipment.getCurrentTotalWeight()).thenReturn(3);
        when(equipment.getCurrentTotalVolume()).thenReturn(5);
        ArrayList<Item> items = new ArrayList<>();
        Item itemOne = Mockito.mock(Item.class);
        when(itemOne.getName()).thenReturn("Item One");
        when(itemOne.getWeight()).thenReturn(1);
        when(itemOne.getVolume()).thenReturn(1);
        items.add(itemOne);
        items.add(itemOne);
        when(equipment.getItems()).thenReturn(items);
        GameManager gameManager = Mockito.mock(GameManager.class);
        when(gameManager.getEquipment()).thenReturn(equipment);
        inventoryViewModel = new InventoryViewModel(gameManager, null);
    }

    @Test
    public void isPackedAndUnpackedAsParcelSuccessfully() {
        Parcel parcel = Parcel.obtain();
        inventoryViewModel.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        InventoryViewModel parceledInventoryViewModel = InventoryViewModel.CREATOR.createFromParcel(parcel);
        assertEquals(inventoryViewModel, parceledInventoryViewModel);
    }
}
