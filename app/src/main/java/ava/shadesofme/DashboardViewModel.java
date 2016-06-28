package ava.shadesofme;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Observable;
import java.util.Observer;

public class DashboardViewModel extends BaseObservable implements Parcelable, Observer {

    private String currentLocation;
    private String currentTime;
    private String maxSatiety;
    private String maxEnergy;
    private String maxHealth;
    private String currentSatiety;
    private String currentEnergy;
    private String currentHealth;
    private GameStateManager gameStateManager;

    public DashboardViewModel(GameStateManager gameStateManager) {
        this.currentLocation = gameStateManager.getCurrentLocation().getName();
        this.currentTime = gameStateManager.getCurrentTime();

        Player player = gameStateManager.getPlayer();
        this.maxSatiety = String.valueOf(player.getMaxSatiety());
        this.maxEnergy = String.valueOf(player.getMaxEnergy());
        this.maxHealth = String.valueOf(player.getMaxHealth());
        this.currentSatiety = String.valueOf(player.getCurrentSatiety());
        this.currentEnergy = String.valueOf(player.getCurrentEnergy());
        this.currentHealth = String.valueOf(player.getCurrentHealth());
        this.gameStateManager = gameStateManager;
    }

    /**
     * Updates after Observable changes
     */

    @Override
    public void update(Observable observable, Object data) {
        if (observable instanceof Player) {
            Player player = (Player) observable;
            if (!String.valueOf(player.getCurrentSatiety()).equals(currentSatiety)) {
                setCurrentSatiety(String.valueOf(player.getCurrentSatiety()));
            }
            if (!String.valueOf(player.getCurrentEnergy()).equals(currentEnergy)) {
                setCurrentEnergy(String.valueOf(player.getCurrentEnergy()));
            }
            if (!String.valueOf(player.getCurrentHealth()).equals(currentHealth)) {
                setCurrentHealth(String.valueOf(player.getCurrentHealth()));
            }
        }
    }

    /**
     * Bindable Getters
     */

    @Bindable
    public String getCurrentLocation() {
        return currentLocation;
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

    /**
     *  Bindable Setters --> remember to always call notifyPropertyChanged(BR.currentHealth);
     */

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

    /**
     *  Parcelable stuff, including equals and hashcode
     */

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(currentLocation);
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
        this.currentLocation = in.readString();
        this.currentTime = in.readString();
        this.maxSatiety = in.readString();
        this.maxEnergy = in.readString();
        this.maxHealth = in.readString();
        this.currentSatiety = in.readString();
        this.currentEnergy = in.readString();
        this.currentHealth = in.readString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DashboardViewModel that = (DashboardViewModel) o;

        if (currentLocation != null ? !currentLocation.equals(that.currentLocation) : that.currentLocation != null)
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
        int result = currentLocation != null ? currentLocation.hashCode() : 0;
        result = 31 * result + (currentTime != null ? currentTime.hashCode() : 0);
        result = 31 * result + (maxSatiety != null ? maxSatiety.hashCode() : 0);
        result = 31 * result + (maxEnergy != null ? maxEnergy.hashCode() : 0);
        result = 31 * result + (maxHealth != null ? maxHealth.hashCode() : 0);
        result = 31 * result + (currentSatiety != null ? currentSatiety.hashCode() : 0);
        result = 31 * result + (currentEnergy != null ? currentEnergy.hashCode() : 0);
        result = 31 * result + (currentHealth != null ? currentHealth.hashCode() : 0);
        return result;
    }

    public void restButtonClicked() {
        gameStateManager.advanceTimeBy(30);
    }

}
