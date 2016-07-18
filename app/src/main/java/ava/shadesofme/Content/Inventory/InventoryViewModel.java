package ava.shadesofme.Content.Inventory;

import android.databinding.Bindable;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import ava.shadesofme.BR;
import ava.shadesofme.Content.ContentViewModel;
import ava.shadesofme.Content.ContentViewModelDao;
import ava.shadesofme.DataModels.Item;
import ava.shadesofme.GameManager;
import ava.shadesofme.GameState.Equipment;

/**
 * The InventoryViewModel holds all data that should be displayed when the Inventory is displayed.
 * It observes Equipment to stay up to date on changes and transforms the data (usually to Strings)
 * so that it can be bound to Views in the corresponding InventoryFragment.
 *
 * The InventoryViewModel is also responsible for forwarding user actions that are triggered in the InventoryFragment.
 */

public class InventoryViewModel extends ContentViewModel {

    private int numberOfSlots;
    private String maxWeight;
    private String maxVolume;
    private String currentWeight;
    private String currentVolume;
    private List<Map<String, String>> items;

    public InventoryViewModel(GameManager gameManager, ContentViewModelDao contentViewModelDao) {
        super("Inventory", "<", gameManager, contentViewModelDao);
        Equipment equipment = gameManager.getEquipment();
        this.numberOfSlots = equipment.getTotalSlots();
        this.maxWeight = String.valueOf(equipment.getMaxTotalWeight());
        this.maxVolume = String.valueOf(equipment.getMaxTotalVolume());
        this.currentWeight = String.valueOf(equipment.getCurrentTotalWeight());
        this.currentVolume = String.valueOf(equipment.getCurrentTotalVolume());
        this.items = transformItemList(equipment.getItems());
    }

    public void itemClicked(String itemName) {
        Item item = gameManager.getEquipment().getItemWithName(itemName);
        contentViewModelDao.itemClicked(item, this);
    }

    private List<Map<String, String>> transformItemList(List<Item> items) {
        List<Map<String, String>> transformedItems = new ArrayList<>();
        for(Item item : items) {
            Map<String, String> transformedItem = new HashMap<>();
            transformedItem.put("item_name", item.getName());
            transformedItem.put("item_weight", String.valueOf(item.getWeight()));
            transformedItem.put("item_volume", String.valueOf(item.getVolume()));
            transformedItems.add(transformedItem);
        }
        return transformedItems;
    }

    /** Update when Observable changes */

    @Override
    public void update(Observable observable, Object data) {
        if (observable instanceof Equipment) {
            Equipment equipment = (Equipment) observable;
            if (equipment.getTotalSlots() != getNumberOfSlots()) {
                setNumberOfSlots(equipment.getTotalSlots());
            }
            if (!String.valueOf(equipment.getMaxTotalWeight()).equals(getMaxWeight())) {
                setMaxWeight(String.valueOf(equipment.getMaxTotalWeight()));
            }
            if (!String.valueOf(equipment.getMaxTotalVolume()).equals(getMaxVolume())) {
                setMaxVolume(String.valueOf(equipment.getMaxTotalVolume()));
            }
            if (!String.valueOf(equipment.getCurrentTotalWeight()).equals(getCurrentWeight())) {
                setCurrentWeight(String.valueOf(equipment.getCurrentTotalWeight()));
            }
            if (!String.valueOf(equipment.getCurrentTotalVolume()).equals(getCurrentVolume())) {
                setCurrentVolume(String.valueOf(equipment.getCurrentTotalVolume()));
            }
        }
    }

    /** Bindable getters */

    @Bindable
    public int getNumberOfSlots() {
        return numberOfSlots;
    }

    @Bindable
    public String getMaxWeight() {
        return maxWeight;
    }

    @Bindable
    public String getMaxVolume() {
        return maxVolume;
    }

    @Bindable
    public String getCurrentWeight() {
        return currentWeight;
    }

    @Bindable
    public String getCurrentVolume() {
        return currentVolume;
    }

    @Bindable
    public List<Map<String, String>> getItems() {
        return items;
    }

    /** Setters */

    public void setNumberOfSlots(int numberOfSlots) {
        this.numberOfSlots = numberOfSlots;
        notifyPropertyChanged(ava.shadesofme.BR.numberOfSlots);
    }

    public void setMaxWeight(String maxWeight) {
        this.maxWeight = maxWeight;
        notifyPropertyChanged(ava.shadesofme.BR.maxWeight);
    }

    public void setMaxVolume(String maxVolume) {
        this.maxVolume = maxVolume;
        notifyPropertyChanged(ava.shadesofme.BR.maxVolume);
    }

    public void setCurrentWeight(String currentWeight) {
        this.currentWeight = currentWeight;
        notifyPropertyChanged(ava.shadesofme.BR.currentWeight);
    }

    public void setCurrentVolume(String currentVolume) {
        this.currentVolume = currentVolume;
        notifyPropertyChanged(ava.shadesofme.BR.currentVolume);
    }

    public void setItems(List<Item> items) {
        this.items = transformItemList(items);
        notifyPropertyChanged(ava.shadesofme.BR.items);
    }

    /** Parcelable implementation */

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(TYPE_INVENTORY);
        super.writeToParcel(dest, flags);
        dest.writeInt(getNumberOfSlots());
        dest.writeString(getMaxWeight());
        dest.writeString(getMaxVolume());
        dest.writeString(getCurrentWeight());
        dest.writeString(getCurrentVolume());
        dest.writeInt(getItems().size());
        dest.writeList(getItems());
    }

    public static final Parcelable.Creator<InventoryViewModel> CREATOR = new Parcelable.Creator<InventoryViewModel>() {

        @Override
        public InventoryViewModel createFromParcel(Parcel source) {
            source.readString(); // eliminate type - not needed in direct subclass creation
            return new InventoryViewModel(source);
        }

        @Override
        public InventoryViewModel[] newArray(int size) {
            return new InventoryViewModel[size];
        }
    };

    public InventoryViewModel(Parcel source) {
        super(source);
        this.numberOfSlots = source.readInt();
        this.maxWeight = source.readString();
        this.maxVolume = source.readString();
        this.currentWeight = source.readString();
        this.currentVolume = source.readString();
        List<Map<String, String>> itemsFromParcel = new ArrayList<>(source.readInt());
        source.readList(itemsFromParcel, null);
        this.items = itemsFromParcel;
    }

    /** Equals and hashcode methods */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InventoryViewModel that = (InventoryViewModel) o;

        if (numberOfSlots != that.numberOfSlots) return false;
        if (maxWeight != null ? !maxWeight.equals(that.maxWeight) : that.maxWeight != null)
            return false;
        if (maxVolume != null ? !maxVolume.equals(that.maxVolume) : that.maxVolume != null)
            return false;
        if (currentWeight != null ? !currentWeight.equals(that.currentWeight) : that.currentWeight != null)
            return false;
        if (currentVolume != null ? !currentVolume.equals(that.currentVolume) : that.currentVolume != null)
            return false;
        return items != null ? items.equals(that.items) : that.items == null;
    }

    @Override
    public int hashCode() {
        int result = numberOfSlots;
        result = 31 * result + (maxWeight != null ? maxWeight.hashCode() : 0);
        result = 31 * result + (maxVolume != null ? maxVolume.hashCode() : 0);
        result = 31 * result + (currentWeight != null ? currentWeight.hashCode() : 0);
        result = 31 * result + (currentVolume != null ? currentVolume.hashCode() : 0);
        result = 31 * result + (items != null ? items.hashCode() : 0);
        return result;
    }
}
