package ava.shadesofme;

import java.util.ArrayList;

public class Initialiser {

    private MainActivity activity;
    private DashboardViewModel dashboardViewModel;
    private Player player;
    private Location home;
    private Location town;
    private GameManager gameManager;

    public Initialiser(MainActivity dashboardActivity) {
        this.activity = dashboardActivity;
    }

    public void startGame() {
        initPlayer();
        initLocations();
        initGameStateManager();
        dashboardViewModel = new DashboardViewModel(gameManager);
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
        ArrayList<EquipmentSlot> equipmentSlots = new ArrayList<>();
        equipmentSlots.add(new EquipmentSlot(5, 5, false));
        equipmentSlots.add(new EquipmentSlot(20, 20, true));
        Equipment equipment = new Equipment(50, 50, equipmentSlots);
        GameState gameState = new GameState("11:00", home);
        this.gameManager = new GameManager(gameState, player, equipment);
    }

}
