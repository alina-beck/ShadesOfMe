package ava.shadesofme;

public class Item {

    private EquipmentManager equipmentManager;
    private GameStateManager gameStateManager;
    private Item upgradeStage;

    public Item(EquipmentManager equipmentManager, GameStateManager gameStateManager, Item upgradeStage) {
        this.equipmentManager = equipmentManager;
        this.gameStateManager = gameStateManager;
        this.upgradeStage = upgradeStage;
    }

    public void pickUp() {
        equipmentManager.add(this);
    }

    public void use() {
        gameStateManager.useItem(this);
        equipmentManager.remove(this);
    }

    public void upgrade() {
        equipmentManager.replace(this, upgradeStage);
    }
}
