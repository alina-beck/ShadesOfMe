package ava.shadesofme.Content.Item;

import android.os.Parcel;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import ava.shadesofme.Content.ContentViewModelDao;
import ava.shadesofme.DataModels.Item;
import ava.shadesofme.GameManager;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
public class InventoryItemViewModelInstrumentationTest {

    private InventoryItemViewModel inventoryItemViewModel;

    @Before
    public void setUp() {
        GameManager mockGameManager = Mockito.mock(GameManager.class);
        Item mockItem = Mockito.mock(Item.class);
        Item mockUpgradeItem = Mockito.mock(Item.class);
        ContentViewModelDao mockViewModelDao = Mockito.mock(ContentViewModelDao.class);
        when(mockItem.getDescription()).thenReturn("some description");
        when(mockUpgradeItem.getName()).thenReturn("some upgrade item name");
        when(mockItem.getUpgradeStage()).thenReturn(mockUpgradeItem);
        when(mockItem.getUseTime()).thenReturn(10);
        when(mockItem.getUpgradeTime()).thenReturn(30);
        inventoryItemViewModel = new InventoryItemViewModel(mockGameManager, mockItem, mockViewModelDao);
    }

    @Test
    public void isPackedAnUnpackedAsParcelSuccessfully() {
        Parcel parcel = Parcel.obtain();
        inventoryItemViewModel.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        InventoryItemViewModel parceledViewModel = InventoryItemViewModel.CREATOR.createFromParcel(parcel);
        assertEquals(inventoryItemViewModel, parceledViewModel);
    }
}
