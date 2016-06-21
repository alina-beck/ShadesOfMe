package ava.shadesofme;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.verify;

public class ItemTest {

    private Item item;
    private EquipmentManager equipmentManager = Mockito.mock(EquipmentManager.class);
    private GameStateManager gameStateManager = Mockito.mock(GameStateManager.class);
    private Item upgradeStage = Mockito.mock(Item.class);

    @Before
    public void setUp() {
        item = new Item(equipmentManager, gameStateManager, upgradeStage, 30, 60, 10, 3, 0);
    }

    @Test
    public void alertsEquipmentManagerWhenPickedUp() {
        item.pickUp();
        verify(equipmentManager).add(item);
    }

    @Test
    public void alertsLocationWhenPickedUp() {
        // TODO: or should it be the other way round?
        // TODO: remove from location only if pickUp was successful
    }

    @Test
    public void alertsGameStateManagerWhenUsed() {
        item.use();
        verify(gameStateManager).useItem(item);
    }

    @Test
    public void alertsEquipmentManagerWhenUsed() {
        // TODO: distinguish between reusable and single-use items
        item.use();
        verify(equipmentManager).remove(item);
    }

    @Test
    public void sendsNewItemToEquipmentManagerOnUpgrade() {
        item.upgrade();
        verify(equipmentManager).replace(item, upgradeStage);
    }

    @Test
    public void doesNotUpgradeUnlessAllConditionsAreMet() {
        // TODO: how to set and check for conditions?
    }

    @Test
    public void advancesTimeOnUpgrade() {
        item.upgrade();
        verify(gameStateManager).advanceBy(Mockito.anyInt());
    }
}
