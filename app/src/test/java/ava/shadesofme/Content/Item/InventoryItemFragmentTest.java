package ava.shadesofme.Content.Item;

import android.os.Bundle;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil;

import ava.shadesofme.BuildConfig;
import ava.shadesofme.DataModels.Item;

import static org.junit.Assert.assertNotNull;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 16)
public class InventoryItemFragmentTest {

    private static final String ITEM = "TestItem";
    private InventoryItemFragment inventoryItemFragment;
    private InventoryItemViewModel mockViewModel = Mockito.mock(InventoryItemViewModel.class);
    private Item mockItem = Mockito.mock(Item.class);

    @Before
    public void setUp() {
        inventoryItemFragment = new InventoryItemFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ITEM, mockViewModel);
        inventoryItemFragment.setArguments(bundle);
        SupportFragmentTestUtil.startVisibleFragment(inventoryItemFragment);
    }

    @Test
    public void isNotNull() {
        assertNotNull(inventoryItemFragment);
    }

}
