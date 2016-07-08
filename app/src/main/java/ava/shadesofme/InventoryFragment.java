package ava.shadesofme;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ava.shadesofme.databinding.FragmentInventoryBinding;

public class InventoryFragment extends InnerFragment implements View.OnClickListener {

    private InventoryViewModel viewModel;

    public InventoryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inventory, container, false);

        viewModel = getArguments().getParcelable("Inventory");
        FragmentInventoryBinding binding = FragmentInventoryBinding.bind(view);
        binding.setInventoryVM(viewModel);

        return view;
    }

    @Override
    public void onClick(View v) {

    }
}
