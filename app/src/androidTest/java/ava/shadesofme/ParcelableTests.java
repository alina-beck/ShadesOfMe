package ava.shadesofme;

import android.os.Parcel;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import java.util.ArrayList;

import ava.shadesofme.Content.ContentViewModel;
import ava.shadesofme.Content.ContentViewModelDao;
import ava.shadesofme.Content.Inventory.InventoryViewModel;
import ava.shadesofme.Content.Item.InventoryItemViewModel;
import ava.shadesofme.DataModels.Item;
import ava.shadesofme.GameState.Equipment;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/** Tests if ViewModels are correctly packed and unpacked as Parcelables */

@RunWith(AndroidJUnit4.class)
public class ParcelableTests {

    private ContentViewModel contentViewModel;

    /** Set up each ContentViewModel, mocking the needed dependencies */

    private void setUpInventoryViewModel() {
        // mock equipment
        Equipment equipment = Mockito.mock(Equipment.class);
        when(equipment.getTotalSlots()).thenReturn(5);
        when(equipment.getMaxTotalWeight()).thenReturn(10);
        when(equipment.getMaxTotalVolume()).thenReturn(10);
        when(equipment.getCurrentTotalWeight()).thenReturn(3);
        when(equipment.getCurrentTotalVolume()).thenReturn(5);

        // mock items
        ArrayList<Item> items = new ArrayList<>();
        Item itemOne = Mockito.mock(Item.class);
        when(itemOne.getName()).thenReturn("Item One");
        when(itemOne.getWeight()).thenReturn(1);
        when(itemOne.getVolume()).thenReturn(1);
        items.add(itemOne);
        items.add(itemOne);
        when(equipment.getItems()).thenReturn(items);

        // mock gameManager
        GameManager gameManager = Mockito.mock(GameManager.class);
        when(gameManager.getEquipment()).thenReturn(equipment);

        contentViewModel = new InventoryViewModel(gameManager, null);
    }

    public void setUpInventoryItemViewModel() {
        // mock gameManager and contentViewModelDao
        GameManager mockGameManager = Mockito.mock(GameManager.class);
        ContentViewModelDao mockViewModelDao = Mockito.mock(ContentViewModelDao.class);

        // mock item
        Item mockItem = Mockito.mock(Item.class);
        Item mockUpgradeItem = Mockito.mock(Item.class);
        when(mockItem.getDescription()).thenReturn("some description");
        when(mockUpgradeItem.getName()).thenReturn("some upgrade item name");
        when(mockItem.getUpgradeStage()).thenReturn(mockUpgradeItem);
        when(mockItem.getUseTime()).thenReturn(10);
        when(mockItem.getUpgradeTime()).thenReturn(30);

        contentViewModel = new InventoryItemViewModel(mockGameManager, mockItem, mockViewModelDao);
    }

    /** Case to be tested */

    private void isPackedAndUnpackedAsParcelSuccessfully() {
        Parcel parcel = Parcel.obtain();
        contentViewModel.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        ContentViewModel parceledViewModel = ContentViewModel.CREATOR.createFromParcel(parcel);
        assertEquals(contentViewModel, parceledViewModel);
    }

    /** Test for each of the ViewModels */

    @Test
    public void inventoryViewModelIsParcelable() {
        setUpInventoryViewModel();
        isPackedAndUnpackedAsParcelSuccessfully();
    }

    @Test
    public void inventoryItemViewModelIsParcelable() {
        setUpInventoryItemViewModel();
        isPackedAndUnpackedAsParcelSuccessfully();
    }

}
