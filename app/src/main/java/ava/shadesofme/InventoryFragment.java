package ava.shadesofme;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class InventoryFragment extends Fragment implements View.OnClickListener {

    private InventoryViewModel viewModel;

    public InventoryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inventory, container, false);

        return view;
    }

    @Override
    public void onClick(View v) {

    }
}
