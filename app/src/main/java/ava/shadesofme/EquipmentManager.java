package ava.shadesofme;

import java.util.List;

public class EquipmentManager {

    private Player player;

    public EquipmentManager(Player player) {
        this.player = player;
    }

    public boolean add(Item item) {
        List<EquipmentSlot> equipmentSlots = player.getEquipmentSlots();
        for (EquipmentSlot e : equipmentSlots) {
            if (e.getItem() == null) {
                e.putItem(item);
                return true;
            }
        }
        return false;
    }

    public void remove(Item item) {
        List<EquipmentSlot> equipmentSlots = player.getEquipmentSlots();
        for (EquipmentSlot e : equipmentSlots) {
            if (e.getItem().equals(item)) {
                e.removeItem();
                return;
            }
        }
    }

    public void replace(Item item, Item upgradeStage) {
        List<EquipmentSlot> equipmentSlots = player.getEquipmentSlots();
        for (EquipmentSlot e : equipmentSlots) {
            if (e.getItem().equals(item)) {
                e.putItem(upgradeStage);
                return;
            }
        }
    }
}
