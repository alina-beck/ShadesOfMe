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

import ava.shadesofme.Content.ContentViewModel;
import ava.shadesofme.Content.ContentViewModelDao;
import ava.shadesofme.DataModels.Item;
import ava.shadesofme.GameManager;
import ava.shadesofme.GameState.Equipment;

public class InventoryViewModel extends ContentViewModel {

    private final String TITLE = "Inventory";
    private final String NAV_BUTTON_TEXT = "<";
    private int numberOfSlots;
    private String maxWeight;
    private String maxVolume;
    private String currentWeight;
    private String currentVolume;
    private List<Map<String, String>> items;
    private GameManager gameManager;
    private ContentViewModelDao contentViewModelDao;

    public InventoryViewModel(GameManager gameManager, ContentViewModelDao contentViewModelDao) {
        Equipment equipment = gameManager.getEquipment();
        this.numberOfSlots = equipment.getTotalSlots();
        this.maxWeight = String.valueOf(equipment.getMaxTotalWeight());
        this.maxVolume = String.valueOf(equipment.getMaxTotalVolume());
        this.currentWeight = String.valueOf(equipment.getCurrentTotalWeight());
        this.currentVolume = String.valueOf(equipment.getCurrentTotalVolume());
        this.items = transformItemList(equipment.getItems());
        this.gameManager = gameManager;
        this.contentViewModelDao = contentViewModelDao;
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

    public void itemClicked(String itemName) {
        Item item = gameManager.getEquipment().getItemWithName(itemName);
        contentViewModelDao.itemClicked(item, this);
    }

    public String getTitle() {
        return TITLE;
    }

    @Override
    public String getNavButtonText() {
        return NAV_BUTTON_TEXT;
    }

    public String getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(String currentWeight) {
        this.currentWeight = currentWeight;
    }

    @Bindable
    public String getMaxWeight() {
        return maxWeight;
    }

    public String getMaxVolume() {
        return maxVolume;
    }

    public int getNumberOfSlots() {
        return numberOfSlots;
    }

    public String getCurrentVolume() {
        return currentVolume;
    }

    public void setMaxWeight(String maxWeight) {
        this.maxWeight = maxWeight;
    }

    public void setMaxVolume(String maxVolume) {
        this.maxVolume = maxVolume;
    }

    public void setNumberOfSlots(int numberOfSlots) {
        this.numberOfSlots = numberOfSlots;
    }

    public void setCurrentVolume(String currentVolume) {
        this.currentVolume = currentVolume;
    }

    public List<Map<String, String>> getItems() {
        return items;
    }

    /**
     * Parcelable stuff, including equals and hashcode
     */

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
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
        public InventoryViewModel createFromParcel(Parcel in) {
            return new InventoryViewModel(in);
        }

        @Override
        public InventoryViewModel[] newArray(int size) {
            return new InventoryViewModel[size];
        }
    };

    private InventoryViewModel(Parcel in) {
        this.numberOfSlots = in.readInt();
        this.maxWeight = in.readString();
        this.maxVolume = in.readString();
        this.currentWeight = in.readString();
        this.currentVolume = in.readString();
        List<Map<String, String>> itemsFromParcel = new ArrayList<>(in.readInt());
        in.readList(itemsFromParcel, null);
        this.items = itemsFromParcel;
    }

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
