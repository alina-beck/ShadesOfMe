package ava.shadesofme;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ava.shadesofme.databinding.FragmentDashboardBinding;

public class DashboardFragment extends Fragment {

    public DashboardFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        FragmentDashboardBinding binding = FragmentDashboardBinding.bind(view);
        DashboardViewModel dashboardViewModel = getArguments().getParcelable("dashboard_view_model");
        binding.setDashboardVM(dashboardViewModel);

        return view;
    }
}
