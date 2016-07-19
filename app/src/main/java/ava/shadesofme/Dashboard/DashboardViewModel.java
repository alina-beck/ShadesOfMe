package ava.shadesofme.Dashboard;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Observable;
import java.util.Observer;

import ava.shadesofme.GameManager;
import ava.shadesofme.GameState.CurrentState;
import ava.shadesofme.GameState.Player;
import ava.shadesofme.Content.ContentViewModelDao;

/** The DashboardViewModel is responsible for holding all data that should permanently be visible to the player,
 * in a format that can be bound to Views using Android data binding (usually Strings).
 *
 * It observes GameState objects so that the data (and in consequence the Views) stay up to date.
 * It implements Parcelable so that it can be passed to the corresponding DashboardFragment.
 */

public class DashboardViewModel extends BaseObservable implements Parcelable, Observer {

    private static final String BUTTON_INVENTORY = "Inventory";

    private String currentTitle;
    private String navigationButtonText;
    private String currentTime;
    private String maxSatiety;
    private String maxEnergy;
    private String maxHealth;
    private String currentSatiety;
    private String currentEnergy;
    private String currentHealth;
    private GameManager gameManager;
    private ContentViewModelDao contentViewModelDao;

    public DashboardViewModel(GameManager gameManager, ContentViewModelDao contentViewModelDao) {
        if(gameManager.getCurrentState().getCurrentView() != null) {
            this.currentTitle = gameManager.getCurrentState().getCurrentView().getTitle();
            this.navigationButtonText = gameManager.getCurrentState().getCurrentView().getNavigationButtonText();
        }
        else {
            this.currentTitle = "Main Screen";
            this.navigationButtonText = BUTTON_INVENTORY;
        }
        this.currentTime = gameManager.getCurrentState().getCurrentTime();
        Player player = gameManager.getPlayer();
        this.maxSatiety = String.valueOf(player.getMaxSatiety());
        this.maxEnergy = String.valueOf(player.getMaxEnergy());
        this.maxHealth = String.valueOf(player.getMaxHealth());
        this.currentSatiety = String.valueOf(player.getCurrentSatiety());
        this.currentEnergy = String.valueOf(player.getCurrentEnergy());
        this.currentHealth = String.valueOf(player.getCurrentHealth());
        this.gameManager = gameManager;
        this.contentViewModelDao = contentViewModelDao;
    }

    public void restButtonClicked() {
        gameManager.advanceTimeBy(30);
    }

    public void navigationButtonClicked() {
        contentViewModelDao.buttonClicked(getNavigationButtonText());
    }

    /** Update when Observable changes */

    @Override
    public void update(Observable observable, Object data) {
        if(observable instanceof Player) {
            Player player = (Player) observable;
            if(!String.valueOf(player.getCurrentSatiety()).equals(currentSatiety)) {
                setCurrentSatiety(String.valueOf(player.getCurrentSatiety()));
            }
            if(!String.valueOf(player.getCurrentEnergy()).equals(currentEnergy)) {
                setCurrentEnergy(String.valueOf(player.getCurrentEnergy()));
            }
            if(!String.valueOf(player.getCurrentHealth()).equals(currentHealth)) {
                setCurrentHealth(String.valueOf(player.getCurrentHealth()));
            }
        }

        else if(observable instanceof CurrentState) {
            CurrentState currentState = (CurrentState) observable;
            if(!currentState.getCurrentTime().equals(currentTime)) {
                setCurrentTime(currentState.getCurrentTime());
            }
            if(!currentState.getCurrentView().getTitle().equals(currentTitle)) {
                setCurrentTitle(currentState.getCurrentView().getTitle());
                setNavigationButtonText(currentState.getCurrentView().getNavigationButtonText());
            }
        }
    }

    /** Bindable Getters */

    @Bindable
    public String getCurrentTitle() {
        return currentTitle;
    }

    @Bindable
    public String getCurrentTime() {
        return currentTime;
    }

    @Bindable
    public String getMaxSatiety() {
        return maxSatiety;
    }

    @Bindable
    public String getMaxEnergy() {
        return maxEnergy;
    }

    @Bindable
    public String getMaxHealth() {
        return maxHealth;
    }

    @Bindable
    public String getCurrentSatiety() {
        return currentSatiety;
    }

    @Bindable
    public String getCurrentEnergy() {
        return currentEnergy;
    }

    @Bindable
    public String getCurrentHealth() {
        return currentHealth;
    }

    @Bindable
    public String getNavigationButtonText() {
        return navigationButtonText;
    }

    /** Setters */

    public void setCurrentTitle(String currentTitle) {
        this.currentTitle = currentTitle;
        notifyPropertyChanged(ava.shadesofme.BR.currentTitle);
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
        notifyPropertyChanged(ava.shadesofme.BR.currentTime);
    }

    public void setCurrentSatiety(String currentSatiety) {
        this.currentSatiety = currentSatiety;
        notifyPropertyChanged(ava.shadesofme.BR.currentSatiety);
    }

    public void setCurrentEnergy(String currentEnergy) {
        this.currentEnergy = currentEnergy;
        notifyPropertyChanged(ava.shadesofme.BR.currentEnergy);
    }

    public void setCurrentHealth(String currentHealth) {
        this.currentHealth = currentHealth;
        notifyPropertyChanged(ava.shadesofme.BR.currentHealth);
    }

    public void setNavigationButtonText(String navigationButtonText) {
        this.navigationButtonText = navigationButtonText;
        notifyPropertyChanged(ava.shadesofme.BR.navigationButtonText);
    }

    /** Parcelable implementation */

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(currentTitle);
        dest.writeString(currentTime);
        dest.writeString(maxSatiety);
        dest.writeString(maxEnergy);
        dest.writeString(maxHealth);
        dest.writeString(currentSatiety);
        dest.writeString(currentEnergy);
        dest.writeString(currentHealth);
    }

    public static final Parcelable.Creator<DashboardViewModel> CREATOR = new Parcelable.Creator<DashboardViewModel>() {
        public DashboardViewModel createFromParcel(Parcel in) {
            return new DashboardViewModel(in);
        }

        public DashboardViewModel[] newArray(int size) {
            return new DashboardViewModel[size];
        }
    };

    private DashboardViewModel(Parcel in) {
        this.currentTitle = in.readString();
        this.currentTime = in.readString();
        this.maxSatiety = in.readString();
        this.maxEnergy = in.readString();
        this.maxHealth = in.readString();
        this.currentSatiety = in.readString();
        this.currentEnergy = in.readString();
        this.currentHealth = in.readString();
    }

    /** Equals and hashcode methods */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DashboardViewModel that = (DashboardViewModel) o;

        if (currentTitle != null ? !currentTitle.equals(that.currentTitle) : that.currentTitle != null)
            return false;
        if (currentTime != null ? !currentTime.equals(that.currentTime) : that.currentTime != null)
            return false;
        if (maxSatiety != null ? !maxSatiety.equals(that.maxSatiety) : that.maxSatiety != null)
            return false;
        if (maxEnergy != null ? !maxEnergy.equals(that.maxEnergy) : that.maxEnergy != null)
            return false;
        if (maxHealth != null ? !maxHealth.equals(that.maxHealth) : that.maxHealth != null)
            return false;
        if (currentSatiety != null ? !currentSatiety.equals(that.currentSatiety) : that.currentSatiety != null)
            return false;
        if (currentEnergy != null ? !currentEnergy.equals(that.currentEnergy) : that.currentEnergy != null)
            return false;
        return currentHealth != null ? currentHealth.equals(that.currentHealth) : that.currentHealth == null;
    }

    @Override
    public int hashCode() {
        int result = currentTitle != null ? currentTitle.hashCode() : 0;
        result = 31 * result + (currentTime != null ? currentTime.hashCode() : 0);
        result = 31 * result + (maxSatiety != null ? maxSatiety.hashCode() : 0);
        result = 31 * result + (maxEnergy != null ? maxEnergy.hashCode() : 0);
        result = 31 * result + (maxHealth != null ? maxHealth.hashCode() : 0);
        result = 31 * result + (currentSatiety != null ? currentSatiety.hashCode() : 0);
        result = 31 * result + (currentEnergy != null ? currentEnergy.hashCode() : 0);
        result = 31 * result + (currentHealth != null ? currentHealth.hashCode() : 0);
        return result;
    }
}
