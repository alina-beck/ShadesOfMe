package ava.shadesofme.Dashboard;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ava.shadesofme.R;
import ava.shadesofme.databinding.FragmentDashboardBinding;

public class DashboardFragment extends Fragment implements View.OnClickListener {

    private DashboardViewModel viewModel;

    public DashboardFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        viewModel = getArguments().getParcelable("dashboard_view_model");
        FragmentDashboardBinding binding = FragmentDashboardBinding.bind(view);
        binding.setDashboardVM(viewModel);

        binding.buttonInventory.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        viewModel.inventoryButtonClicked();
    }
}
