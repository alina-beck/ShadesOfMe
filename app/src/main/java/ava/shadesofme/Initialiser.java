package ava.shadesofme;

import java.util.ArrayList;

import ava.shadesofme.Content.ContentViewModelDao;
import ava.shadesofme.Dashboard.DashboardViewModel;
import ava.shadesofme.DataModels.Item;
import ava.shadesofme.DataModels.Location;
import ava.shadesofme.GameState.CurrentState;
import ava.shadesofme.GameState.Equipment;
import ava.shadesofme.GameState.Player;

public class Initialiser {

    private MainActivity activity;
    private DashboardViewModel dashboardViewModel;
    private Player player;
    private Location home;
    private Location town;
    private GameManager gameManager;
    private ContentViewModelDao contentViewModelDao;

    public Initialiser(MainActivity dashboardActivity) {
        this.activity = dashboardActivity;
    }

    public void startGame() {
        initPlayer();
        initLocations();
        initGameStateManager();
        contentViewModelDao = new ContentViewModelDao(gameManager, activity);
        dashboardViewModel = new DashboardViewModel(gameManager, contentViewModelDao);
        activity.initDashboard(dashboardViewModel);
    }

    private void initPlayer() {
        this.player = new Player(30, 100, 50, 100, 80, 100);
    }

    private void initLocations() {
        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item(null, 10, 0, 5, 10, 15, 3, 3, 1));
        items.add(new Item(null, 20, 0, 10, 30, 50, 8, 8, 3));
        this.home = new Location("Home", items);

        items.add(new Item(null, 10, 0, 0, 0, 10, 1, 1, 10));
        this.town = new Location("Town", items);
    }

    private void initGameStateManager() {
        Equipment equipment = new Equipment(50, 50, 2);
        CurrentState currentState = new CurrentState("11:00", home);
        this.gameManager = new GameManager(currentState, player, equipment);
    }

}
