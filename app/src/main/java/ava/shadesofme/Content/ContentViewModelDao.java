package ava.shadesofme.Content;

import ava.shadesofme.Content.Item.InventoryItemViewModel;
import ava.shadesofme.DataModels.Item;
import ava.shadesofme.GameManager;
import ava.shadesofme.Content.Inventory.InventoryViewModel;
import ava.shadesofme.MainActivity;

public class ContentViewModelDao {

    private static final String INVENTORY = "Inventory";
    private static final String BACK = "<";
    private GameManager gameManager;
    private MainActivity activity;

    public ContentViewModelDao(GameManager gameManager, MainActivity activity) {
        this.gameManager = gameManager;
        this.activity = activity;
    }

    // not unit tested because this is a factory
    public void buttonClicked(String buttonTarget) {
        ContentViewModel viewModel;
        switch (buttonTarget) {
            case INVENTORY:
                viewModel = new InventoryViewModel(gameManager, this);
                break;
            case BACK:
                activity.onBackPressed();
                return;
            default:
                // TODO: throw and handle exception
                System.out.println("no such view model");
                return;
        }
        activity.setContentFragment(buttonTarget, viewModel);
    }

    // not unit tested because this is a factory
    // TODO: handle item clicks from item view model (upgrading item must remove first item fragment from backstack)
    public void itemClicked(Item item, ContentViewModel sourceViewModel) {
        ContentViewModel viewModel = null;
        if (sourceViewModel instanceof InventoryViewModel) {
            viewModel = new InventoryItemViewModel(gameManager, item, this);
        }
        activity.setContentFragment("Inventory Item", viewModel);
    }
}
