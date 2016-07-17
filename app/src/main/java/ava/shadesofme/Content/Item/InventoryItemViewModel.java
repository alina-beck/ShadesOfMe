package ava.shadesofme.Content.Item;

import android.databinding.Bindable;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Observable;

import ava.shadesofme.Content.ContentViewModel;
import ava.shadesofme.Content.ContentViewModelDao;
import ava.shadesofme.DataModels.Item;
import ava.shadesofme.GameManager;

public class InventoryItemViewModel extends ContentViewModel {

    private final String NAV_BUTTON_TEXT = "<";
    private String title;
    private String itemDescription;
    private String upgradeItem;
    private String useButtonText;
    private String upgradeButtonText;
    private GameManager gameManager;
    private Item item;
    private ContentViewModelDao contentViewModelDao;

    public InventoryItemViewModel(GameManager gameManager, Item item, ContentViewModelDao contentViewModelDao) {
        this.title = item.getName();
        this.itemDescription = item.getDescription();
        // TODO: take care of items without upgrade stage
        if (item.getUpgradeStage() != null) {
            this.upgradeItem = item.getUpgradeStage().getName();
        }
        this.useButtonText = "Use (" + String.valueOf(item.getUseTime()) + ")";
        this.upgradeButtonText = "Upgrade (" + String.valueOf(item.getUpgradeTime()) + ")";
        this.gameManager = gameManager;
        this.item = item;
        this.contentViewModelDao = contentViewModelDao;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getItemDescription());
        dest.writeString(getUpgradeItem());
        dest.writeString(getUseButtonText());
        dest.writeString(getUpgradeButtonText());
    }

    public static final Parcelable.Creator<InventoryItemViewModel> CREATOR = new Parcelable.Creator<InventoryItemViewModel>() {

        @Override
        public InventoryItemViewModel createFromParcel(Parcel in) {
            return new InventoryItemViewModel(in);
        }

        @Override
        public InventoryItemViewModel[] newArray(int size) {
            return new InventoryItemViewModel[size];
        }
    };

    private InventoryItemViewModel(Parcel in) {
        this.itemDescription = in.readString();
        this.upgradeItem = in.readString();
        this.useButtonText = in.readString();
        this.upgradeButtonText = in.readString();
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String getNavButtonText() {
        return NAV_BUTTON_TEXT;
    }

    @Bindable
    public String getItemDescription() {
        return itemDescription;
    }

    @Bindable
    public String getUpgradeItem() {
        return upgradeItem;
    }

    @Bindable
    public String getUseButtonText() {
        return useButtonText;
    }

    @Bindable
    public String getUpgradeButtonText() {
        return upgradeButtonText;
    }

    public void useButtonClicked() {
        gameManager.useItem(item);
        contentViewModelDao.buttonClicked("<");
    }

    public void upgradeButtonClicked() {
        gameManager.upgradeItem(item);
        contentViewModelDao.itemClicked(item.getUpgradeStage(), this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InventoryItemViewModel that = (InventoryItemViewModel) o;

        if (itemDescription != null ? !itemDescription.equals(that.itemDescription) : that.itemDescription != null)
            return false;
        if (upgradeItem != null ? !upgradeItem.equals(that.upgradeItem) : that.upgradeItem != null)
            return false;
        if (useButtonText != null ? !useButtonText.equals(that.useButtonText) : that.useButtonText != null)
            return false;
        return upgradeButtonText != null ? upgradeButtonText.equals(that.upgradeButtonText) : that.upgradeButtonText == null;

    }

    @Override
    public int hashCode() {
        int result = itemDescription != null ? itemDescription.hashCode() : 0;
        result = 31 * result + (upgradeItem != null ? upgradeItem.hashCode() : 0);
        result = 31 * result + (useButtonText != null ? useButtonText.hashCode() : 0);
        result = 31 * result + (upgradeButtonText != null ? upgradeButtonText.hashCode() : 0);
        return result;
    }

    @Override
    public void update(Observable observable, Object data) {
        // inherited from ContentViewModel
    }
}
