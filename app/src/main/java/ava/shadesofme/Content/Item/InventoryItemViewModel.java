package ava.shadesofme.Content.Item;

import android.databinding.Bindable;
import android.os.Parcel;
import android.os.Parcelable;

import ava.shadesofme.Content.ContentViewModel;
import ava.shadesofme.Content.ContentViewModelDao;
import ava.shadesofme.DataModels.Item;
import ava.shadesofme.GameManager;

/** InventoryItemViewModels hold item data that should be displayed for Items opened from the inventory.
 * It takes the concerned Item in the constructor and transforms the relevant data (usually into Strings)
 * so that it can be bound to Views in the corresponding InventoryItemFragment using Android data binding.
 *
 * InventoryItemViewModels are also responsible for forwarding user actions that are triggered in the corresponding InventoryItemFragment.
 */

public class InventoryItemViewModel extends ContentViewModel {

    private String itemDescription;
    private String upgradeItem;
    private String useButtonText;
    private String upgradeButtonText;
    private Item item;

    public InventoryItemViewModel(GameManager gameManager, Item item, ContentViewModelDao contentViewModelDao) {
        super(item.getName(), "<", gameManager, contentViewModelDao);
        this.itemDescription = item.getDescription();
        // TODO: take care of items without upgrade stage
        if (item.getUpgradeStage() != null) {
            this.upgradeItem = item.getUpgradeStage().getName();
        }
        this.useButtonText = "Use (" + String.valueOf(item.getUseTime()) + ")";
        this.upgradeButtonText = "Upgrade (" + String.valueOf(item.getUpgradeTime()) + ")";
        this.item = item;
    }

    public void useButtonClicked() {
        gameManager.useItem(item);
        contentViewModelDao.buttonClicked("<");
    }

    public void upgradeButtonClicked() {
        gameManager.upgradeItem(item);
        contentViewModelDao.itemClicked(item.getUpgradeStage(), this);
    }

    /** Bindable getters */

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

    /** Parcelable implementation */

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(TYPE_INVENTORY_ITEM);
        super.writeToParcel(dest, flags);
        dest.writeString(getItemDescription());
        dest.writeString(getUpgradeItem());
        dest.writeString(getUseButtonText());
        dest.writeString(getUpgradeButtonText());
        // TODO (possibly): include item
    }

    public static final Parcelable.Creator<InventoryItemViewModel> CREATOR = new Parcelable.Creator<InventoryItemViewModel>() {

        @Override
        public InventoryItemViewModel createFromParcel(Parcel source) {
            source.readString(); // eliminate type - not needed in direct subclass creation
            return new InventoryItemViewModel(source);
        }

        @Override
        public InventoryItemViewModel[] newArray(int size) {
            return new InventoryItemViewModel[size];
        }
    };

    public InventoryItemViewModel(Parcel source) {
        super(source);
        this.itemDescription = source.readString();
        this.upgradeItem = source.readString();
        this.useButtonText = source.readString();
        this.upgradeButtonText = source.readString();
    }

    /** Equals and hashcode methods */

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
}
