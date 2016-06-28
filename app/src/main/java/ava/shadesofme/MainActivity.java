package ava.shadesofme;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

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
}
