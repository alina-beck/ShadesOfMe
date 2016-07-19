package ava.shadesofme.Dashboard;

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
import static org.mockito.Mockito.verify;

/** Integration and unit tests for DashboardFragment */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 16)
public class DashboardFragmentTest {

    private static final String VIEW_MODEL_NAME = "Dashboard";
    private DashboardFragment dashboardFragment;
    private DashboardViewModel mockDashboardViewModel = Mockito.mock(DashboardViewModel.class);

    @Before
    public void setUp() {
        dashboardFragment = new DashboardFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(VIEW_MODEL_NAME, mockDashboardViewModel);
        dashboardFragment.setArguments(bundle);
        SupportFragmentTestUtil.startVisibleFragment(dashboardFragment);
    }

    @Test
    public void isNotNull() {
        assertNotNull(dashboardFragment);
    }

    /** Integration with DashboardViewModel */

    @Test
    public void alertsViewModelOnInventoryButtonClick() {
        dashboardFragment.onClick(null);
        verify(mockDashboardViewModel).navigationButtonClicked();
    }
}
