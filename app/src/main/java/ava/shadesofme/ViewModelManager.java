package ava.shadesofme;

public class ViewModelManager {

    private static final String INVENTORY = "Inventory";
    private GameManager gameManager;
    private MainActivity activity;

    public ViewModelManager(GameManager gameManager, MainActivity activity) {
        this.gameManager = gameManager;
        this.activity = activity;
    }

    public void buttonClicked(String buttonTarget) {
        InnerViewModel viewModel = null;
        switch (buttonTarget) {
            case INVENTORY:
                viewModel = new InventoryViewModel(gameManager);
        }
        activity.setInnerFragment(buttonTarget, viewModel);
    }
}
