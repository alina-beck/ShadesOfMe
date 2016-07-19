package ava.shadesofme.Content;

import ava.shadesofme.Content.Item.InventoryItemViewModel;
import ava.shadesofme.Dashboard.DashboardFragment;
import ava.shadesofme.Dashboard.DashboardViewModel;
import ava.shadesofme.DataModels.Item;
import ava.shadesofme.GameManager;
import ava.shadesofme.Content.Inventory.InventoryViewModel;
import ava.shadesofme.MainActivity;
import ava.shadesofme.R;

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
                gameManager.getEquipment().addObserver(viewModel);
                break;
            case BACK:
                activity.onBackPressed();
                if (activity.getSupportFragmentManager().findFragmentById(R.id.container_fragment_dashboard) instanceof ContentFragment) {
                    viewModel = ((ContentFragment) activity.getSupportFragmentManager().findFragmentById(R.id.container_fragment_dashboard)).getViewModel();
                    if (viewModel != null) {
                        gameManager.updateView(viewModel);
                    }
                }
                else {
                    // this will become obsolete when game initialisation is done correctly
                    DashboardViewModel dashboardViewModel = ((DashboardFragment) activity.getSupportFragmentManager().findFragmentById(R.id.container_fragment_dashboard)).getViewModel();
                    dashboardViewModel.setNavigationButtonText("Inventory");
                    dashboardViewModel.setCurrentTitle("Main Screen");
                }
                return;
            default:
                // TODO: throw and handle exception
                System.out.println("no such view model");
                return;
        }
        gameManager.updateView(viewModel);
        activity.setContentFragment(buttonTarget, viewModel);
    }

    // not unit tested because this is a factory
    // TODO: handle item clicks from item view model (upgrading item must remove first item fragment from backstack)
    public void itemClicked(Item item, ContentViewModel sourceViewModel) {
        ContentViewModel viewModel = null;
        if (sourceViewModel instanceof InventoryViewModel) {
            viewModel = new InventoryItemViewModel(gameManager, item, this);
        }
        gameManager.updateView(viewModel);
        activity.setContentFragment("Inventory Item", viewModel);
    }
}
