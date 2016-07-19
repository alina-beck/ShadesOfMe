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
import ava.shadesofme.Dashboard.DashboardViewModel;
import ava.shadesofme.DataModels.Item;
import ava.shadesofme.DataModels.Location;
import ava.shadesofme.GameState.CurrentState;
import ava.shadesofme.GameState.Equipment;
import ava.shadesofme.GameState.Player;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/** Tests if ViewModels are correctly packed and unpacked as Parcelables */

@RunWith(AndroidJUnit4.class)
public class ParcelableTests {

    private DashboardViewModel dashboardViewModel;
    private ContentViewModel contentViewModel;

    /** Set up the DashboardViewModel, mocking the needed dependencies */

    private void setUpDashboardViewModel() {
        Location mockLocation = Mockito.mock(Location.class);
        CurrentState mockCurrentState = Mockito.mock(CurrentState.class);
        Player mockPlayer = Mockito.mock(Player.class);
        GameManager mockGameManager = Mockito.mock(GameManager.class);
        ContentViewModelDao mockContentViewModelDao = Mockito.mock(ContentViewModelDao.class);
        when(mockGameManager.getPlayer()).thenReturn(mockPlayer);
        when(mockGameManager.getCurrentState()).thenReturn(mockCurrentState);
        when(mockCurrentState.getCurrentLocation()).thenReturn(mockLocation);
        when(mockCurrentState.getCurrentTime()).thenReturn("12:00");
        when(mockLocation.getName()).thenReturn("Test Home");
        when(mockPlayer.getMaxSatiety()).thenReturn(100);
        when(mockPlayer.getMaxEnergy()).thenReturn(100);
        when(mockPlayer.getMaxHealth()).thenReturn(100);
        when(mockPlayer.getCurrentSatiety()).thenReturn(30);
        when(mockPlayer.getCurrentEnergy()).thenReturn(50);
        when(mockPlayer.getCurrentHealth()).thenReturn(80);
        dashboardViewModel = new DashboardViewModel(mockGameManager, mockContentViewModelDao);
    }

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

    /** Test for the DashboardViewModel */

    @Test
    public void dashboardViewModelIsParcelable() {
        setUpDashboardViewModel();
        Parcel parcel = Parcel.obtain();
        dashboardViewModel.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        DashboardViewModel parceledViewModel = DashboardViewModel.CREATOR.createFromParcel(parcel);
        assertEquals(dashboardViewModel, parceledViewModel);
    }

    /** Test for each of the ContentViewModels */

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
