package ava.shadesofme.Content.Item;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import ava.shadesofme.Content.ContentFragment;
import ava.shadesofme.R;
import ava.shadesofme.databinding.FragmentItemInventoryBinding;

public class InventoryItemFragment extends ContentFragment {

    private InventoryItemViewModel viewModel;

    public InventoryItemFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_inventory, container, false);

        viewModel = getArguments().getParcelable("Inventory Item");
        FragmentItemInventoryBinding binding = FragmentItemInventoryBinding.bind(view);
        binding.setInventoryItemVM(viewModel);

        LinearLayout.LayoutParams fragmentSize = calculateFragmentSize();
        binding.containerFragmentContent.setLayoutParams(fragmentSize);

        return view;
    }
}
