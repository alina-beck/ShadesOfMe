package ava.shadesofme;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ava.shadesofme.Content.ContentFragment;
import ava.shadesofme.Content.ContentViewModel;
import ava.shadesofme.Content.Inventory.InventoryFragment;
import ava.shadesofme.Dashboard.DashboardFragment;
import ava.shadesofme.Dashboard.DashboardViewModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Initialiser initialiser = new Initialiser(this);
        initialiser.startGame();
    }

    public void initDashboard(DashboardViewModel dashboardViewModel) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("dashboard_view_model", dashboardViewModel);
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
            case "Inventory":
                fragment = new InventoryFragment();
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
