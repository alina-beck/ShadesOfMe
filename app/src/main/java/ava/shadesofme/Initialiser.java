package ava.shadesofme;

import java.util.ArrayList;

public class Initialiser {

    private DashboardActivity activity;
    private Player player;
    private Location home;
    private Location town;
    private GameStateManager gameStateManager;

    public Initialiser(DashboardActivity dashboardActivity) {
        this.activity = dashboardActivity;
    }

    public DashboardPresenter startGame() {
        DashboardPresenter dashboardPresenter = new DashboardPresenter(activity);
        initPlayer();
        initLocations();
        initGameStateManager(dashboardPresenter);
        return dashboardPresenter;
    }

    private void initPlayer() {
        ArrayList<EquipmentSlot> equipmentSlots = new ArrayList<>();
        equipmentSlots.add(new EquipmentSlot(5, 5, false));
        equipmentSlots.add(new EquipmentSlot(20, 20, true));
        this.player = new Player(30, 100, 50, 100, 80, 100, equipmentSlots);
    }

    private void initLocations() {
        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item(null, 10, 0, 5, 10, 15, 3, 3, 1));
        items.add(new Item(null, 20, 0, 10, 30, 50, 8, 8, 3));
        this.home = new Location("Home", items);

        items.add(new Item(null, 10, 0, 0, 0, 10, 1, 1, 10));
        this.town = new Location("Town", items);
    }

    private void initGameStateManager(DashboardPresenter dashboardPresenter) {
        EquipmentManager equipmentManager = new EquipmentManager(player, 50, 50);
        this.gameStateManager = new GameStateManager("11:00", player, home, equipmentManager, dashboardPresenter);
        dashboardPresenter.setGameStateManager(gameStateManager);
    }

}
