package ava.shadesofme.Content.Inventory;

import android.os.Bundle;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil;

import ava.shadesofme.BuildConfig;

import static org.junit.Assert.assertNotNull;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 16)
public class InventoryFragmentTest {

    private static final String INVENTORY = "Inventory";
    private InventoryFragment inventoryFragment;
    private InventoryViewModel mockInventoryViewModel = Mockito.mock(InventoryViewModel.class);

    @Before
    public void setUp() {
        inventoryFragment = new InventoryFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(INVENTORY, mockInventoryViewModel);
        inventoryFragment.setArguments(bundle);
        SupportFragmentTestUtil.startVisibleFragment(inventoryFragment);
    }

    @Test
    public void isNotNull() {
        assertNotNull(inventoryFragment);
    }
}
