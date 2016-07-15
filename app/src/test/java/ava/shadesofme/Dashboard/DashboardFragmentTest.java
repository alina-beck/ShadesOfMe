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

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 16)
public class DashboardFragmentTest {

    private static final String DASHBOARD_VIEW_MODEL = "dashboard_view_model";
    private DashboardFragment dashboardFragment;
    private DashboardViewModel mockDashboardViewModel = Mockito.mock(DashboardViewModel.class);

    @Before
    public void setUp() {
        dashboardFragment = new DashboardFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(DASHBOARD_VIEW_MODEL, mockDashboardViewModel);
        dashboardFragment.setArguments(bundle);
        SupportFragmentTestUtil.startVisibleFragment(dashboardFragment);
    }

    @Test
    public void isNotNull() {
        assertNotNull(dashboardFragment);
    }

    @Test
    public void alertsViewModelOnInventoryButtonClick() {
        dashboardFragment.onClick(null);
        verify(mockDashboardViewModel).inventoryButtonClicked();
    }
}
