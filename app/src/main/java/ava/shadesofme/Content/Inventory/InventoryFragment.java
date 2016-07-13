package ava.shadesofme.Content.Inventory;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import ava.shadesofme.Content.ContentFragment;
import ava.shadesofme.R;
import ava.shadesofme.databinding.FragmentInventoryBinding;

public class InventoryFragment extends ContentFragment implements View.OnClickListener {

    private InventoryViewModel viewModel;

    public InventoryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inventory, container, false);

        viewModel = getArguments().getParcelable("Inventory");
        FragmentInventoryBinding binding = FragmentInventoryBinding.bind(view);
        binding.setInventoryVM(viewModel);

        LinearLayout.LayoutParams fragmentSize = calculateFragmentSize();
        binding.containerFragmentContent.setLayoutParams(fragmentSize);

        return view;
    }

    @Override
    public void onClick(View v) {

    }
}
