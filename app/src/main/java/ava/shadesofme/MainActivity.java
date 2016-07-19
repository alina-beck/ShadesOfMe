package ava.shadesofme;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ava.shadesofme.Content.ContentFragment;
import ava.shadesofme.Content.ContentViewModel;
import ava.shadesofme.Content.Inventory.InventoryFragment;
import ava.shadesofme.Content.Item.InventoryItemFragment;
import ava.shadesofme.Dashboard.DashboardFragment;
import ava.shadesofme.Dashboard.DashboardViewModel;

/** Starting point for the game - the MainActivity is responsible for starting the Initialiser.
 *
 * The MainActivity acts as container for DashboardFragment and ContentFragments
 * and manages the navigation between ContentFragments when prompted. */

public class MainActivity extends AppCompatActivity {

    // ViewModel names
    public static final String DASHBOARD = "Dashboard";
    public static final String INVENTORY = "Inventory";
    public static final String INVENTORY_ITEM = "Inventory Item";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Initialiser initialiser = new Initialiser(this);
        initialiser.startGame();
    }

    public void initDashboardFragment(DashboardViewModel dashboardViewModel) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(DASHBOARD, dashboardViewModel);
        DashboardFragment dashboardFragment = new DashboardFragment();
        dashboardFragment.setArguments(bundle);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.container_fragment_dashboard, dashboardFragment);
        ft.commit();
    }

    public void setContentFragment(String name, ContentViewModel viewModel) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(name, viewModel);
        ContentFragment fragment;
        switch (name) {
            case INVENTORY:
                fragment = new InventoryFragment();
                break;
            case INVENTORY_ITEM:
                fragment = new InventoryItemFragment();
                break;
            default:
                // TODO: throw and handle exception
                System.out.println("no such fragment");
                return;
        }
        fragment.setArguments(bundle);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.container_fragment_dashboard, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }
}
