package ava.shadesofme.Content.Item;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import ava.shadesofme.Content.ContentViewModelDao;
import ava.shadesofme.DataModels.Item;
import ava.shadesofme.GameManager;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class InventoryItemViewModelTest {

    private static final String ITEM_DESCRIPTION = "This is a test item for some beautiful testing.";
    private static final String UPGRADE_ITEM_NAME = "TestUpgradeItemName";

    private InventoryItemViewModel inventoryItemViewModel;
    private GameManager mockGameManager = Mockito.mock(GameManager.class);
    private Item mockItem = Mockito.mock(Item.class);
    private Item mockUpgradeItem = Mockito.mock(Item.class);
    private ContentViewModelDao mockViewModelDao = Mockito.mock(ContentViewModelDao.class);

    @Before
    public void setUp() {
        when(mockItem.getDescription()).thenReturn(ITEM_DESCRIPTION);
        when(mockUpgradeItem.getName()).thenReturn(UPGRADE_ITEM_NAME);
        when(mockItem.getUpgradeStage()).thenReturn(mockUpgradeItem);
        when(mockItem.getUseTime()).thenReturn(10);
        when(mockItem.getUpgradeTime()).thenReturn(30);
        inventoryItemViewModel = new InventoryItemViewModel(mockGameManager, mockItem, mockViewModelDao);
    }

    @Test
    public void setsUpItemDescriptionInConstructor() {
        assertEquals(ITEM_DESCRIPTION, inventoryItemViewModel.getItemDescription());
    }

    @Test
    public void setsUpUpgradeItemNameInConstructor() {
        assertEquals(UPGRADE_ITEM_NAME, inventoryItemViewModel.getUpgradeItem());
    }

    @Test
    public void setsTextForUseButtonIncludingUseTimeInConstructor() {
        assertEquals("Use (10)", inventoryItemViewModel.getUseButtonText());
    }

    @Test
    public void setsTextForUpgradeButtonIncludingTimeInConstructor() {
        assertEquals("Upgrade (30)", inventoryItemViewModel.getUpgradeButtonText());
    }

    @Test
    public void alertsGameManagerWhenUseButtonClicked() {
        inventoryItemViewModel.useButtonClicked();
        verify(mockGameManager).useItem(mockItem);
    }

    @Test
    public void alertsViewModelDaoWhenUseButtonClicked() {
        inventoryItemViewModel.useButtonClicked();
        verify(mockViewModelDao).buttonClicked("<");
    }

    @Test
    public void alertsGameManagerWhenUpgradeButtonClicked() {
        inventoryItemViewModel.upgradeButtonClicked();
        verify(mockGameManager).upgradeItem(mockItem);
    }

    @Test
    public void alertsViewModelDaoWhenUpgradeButtonClicked() {
        inventoryItemViewModel.upgradeButtonClicked();
        verify(mockViewModelDao).itemClicked(mockUpgradeItem, inventoryItemViewModel);
    }

}
