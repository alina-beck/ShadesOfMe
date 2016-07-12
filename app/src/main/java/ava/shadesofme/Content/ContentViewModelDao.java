package ava.shadesofme.Content;

import ava.shadesofme.GameManager;
import ava.shadesofme.Content.Inventory.InventoryViewModel;
import ava.shadesofme.MainActivity;

public class ContentViewModelDao {

    private static final String INVENTORY = "Inventory";
    private GameManager gameManager;
    private MainActivity activity;

    public ContentViewModelDao(GameManager gameManager, MainActivity activity) {
        this.gameManager = gameManager;
        this.activity = activity;
    }

    public void buttonClicked(String buttonTarget) {
        ContentViewModel viewModel = null;
        switch (buttonTarget) {
            case INVENTORY:
                viewModel = new InventoryViewModel(gameManager);
        }
        activity.setInnerFragment(buttonTarget, viewModel);
    }
}
