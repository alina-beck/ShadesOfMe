package ava.shadesofme;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import ava.shadesofme.Content.Inventory.InventoryViewModel;
import ava.shadesofme.Dashboard.DashboardViewModel;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

/** Integration and unit tests for MainActivity */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 16)
public class MainActivityTest {

    private static final String INVENTORY = "Inventory";
    private MainActivity activity;
    private Initialiser mockInitialiser = Mockito.mock(Initialiser.class);
    private DashboardViewModel mockDashboardViewModel = Mockito.mock(DashboardViewModel.class);
    private InventoryViewModel mockInventoryViewModel = Mockito.mock(InventoryViewModel.class);

    @Before
    public void setUp() {
        activity = Robolectric.buildActivity(MainActivity.class).create().resume().get();
    }

    @Test
    public void isNotNull() {
        assertNotNull(activity);
    }

    /** Integration with Initialiser */

    @Test
    public void startsGameOnCreate() {
        verify(mockInitialiser).startGame();
    }

    /** Unit tests: adding Fragments */

    @Test
    public void addsDashboardFragmentToFragmentContainer() {
        activity.initDashboardFragment(mockDashboardViewModel);
        assertNotNull(activity.getSupportFragmentManager().getFragments());
    }

    @Test
    public void addsContentFragmentToFragmentContainer() {
        activity.setContentFragment(INVENTORY, mockInventoryViewModel);
        assertEquals(2, activity.getSupportFragmentManager().getFragments().size());
    }

}
