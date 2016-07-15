package ava.shadesofme.Content;

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
    public void itemClicked(String itemName) {
        // TODO: think! is item in equipment - can be used? is item in location - can be picked up?
    }
}
