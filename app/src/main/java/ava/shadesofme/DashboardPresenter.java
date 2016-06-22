package ava.shadesofme;

public class DashboardPresenter {

    private DashboardActivity activity;
    private GameStateManager gameStateManager;

    public DashboardPresenter(DashboardActivity activity, GameStateManager gameStateManager) {
        this.activity = activity;
        this.gameStateManager = gameStateManager;
    }
    public void onActivityCreated() {
        activity.displayCurrentTime(gameStateManager.getCurrentTime());

        Player player = gameStateManager.getPlayer();

        activity.displayMaxSatiety(player.getMaxSatiety());
        activity.displayMaxEnergy(player.getMaxEnergy());
        activity.displayMaxHealth(player.getMaxHealth());

        updatePlayerStats(player);
    }

    public void updatePlayerStats(Player mockPlayer) {
        activity.displayCurrentSatiety(mockPlayer.getCurrentSatiety());
        activity.displayCurrentEnergy(mockPlayer.getCurrentEnergy());
        activity.displayCurrentHealth(mockPlayer.getCurrentHealth());
    }

    public void updateTime(String newTime) {
        activity.displayCurrentTime(newTime);
    }
}
