package ava.shadesofme.Content;

import android.databinding.BaseObservable;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Observable;
import java.util.Observer;

import ava.shadesofme.Content.Inventory.InventoryViewModel;
import ava.shadesofme.Content.Item.InventoryItemViewModel;
import ava.shadesofme.GameManager;

/** ContentViewModels are responsible for holding different kinds of content of the game, in a format that can be bound to Views
 * using the Android data binding library (usually Strings).
 *
 * ContentViewModels observe relevant GameState classes - when a state changes, the affected Views are updated thanks to data binding.
 *
 * Each ContentViewModel has a corresponding ContentFragment.
 * ContentViewModels implement Parcelable so that they can be passed to their Fragment when it is instantiated.
 * The abstract class offers a basic implementation of Parcelable to avoid duplicate code.
 *
 * The abstract class holds a title and navigationButtonText, to be used by the DashboardViewModel whenever the ContentViewModel is active.
 */

public abstract class ContentViewModel extends BaseObservable implements Parcelable, Observer {

    protected static final String TYPE_INVENTORY = "Inventory";
    protected static final String TYPE_INVENTORY_ITEM = "InventoryItem";

    protected String title;
    protected String navButtonText;
    protected GameManager gameManager;
    protected ContentViewModelDao contentViewModelDao;

    public ContentViewModel(String title, String navButtonText, GameManager gameManager, ContentViewModelDao contentViewModelDao) {
        this.title = title;
        this.navButtonText = navButtonText;
        this.gameManager = gameManager;
        this.contentViewModelDao = contentViewModelDao;
    }

    /** Observer */

    @Override
    public void update(Observable observable, Object data) {
        // empty default implementation - in case a subclass does not need to observe anything
    }

    /** Getters */

    public String getTitle() {
        return title;
    }

    public String getNavButtonText() {
        return navButtonText;
    }

    /** Parcelable implementation */

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // TODO (probably needed?): make GameManager and ContentViewModelDao parcelable and include them here. Or make them singletons?
        dest.writeString(getTitle());
        dest.writeString(getNavButtonText());
    }

    public static final Creator<ContentViewModel> CREATOR = new Creator<ContentViewModel>() {
        @Override
        public ContentViewModel createFromParcel(Parcel source) {
            return ContentViewModel.createConcreteViewModel(source);
        }

        @Override
        public ContentViewModel[] newArray(int size) {
            return new ContentViewModel[size];
        }
    };

    public ContentViewModel(Parcel in) {
        this.title = in.readString();
        this.navButtonText = in.readString();
    }

    private static ContentViewModel createConcreteViewModel(Parcel source) {

        switch(source.readString()) {
            case TYPE_INVENTORY:
                return new InventoryViewModel(source);
            case TYPE_INVENTORY_ITEM:
                return new InventoryItemViewModel(source);
            default:
                // TODO: handle null values
                return null;
        }
    }

    @Override
    public String toString() {
        return "ContentViewModel: " + getTitle();
    }

}
