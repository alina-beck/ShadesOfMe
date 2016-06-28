package ava.shadesofme;

import android.os.Parcel;
import android.os.Parcelable;

public class DashboardViewModel implements Parcelable {

    private String currentLocation;
    private String currentTime;
    private int maxSatiety;
    private int maxEnergy;
    private int maxHealth;
    private int currentSatiety;
    private int currentEnergy;
    private int currentHealth;

    public DashboardViewModel(GameStateManager gameStateManager) {
        this.currentLocation = gameStateManager.getCurrentLocation().getName();
        this.currentTime = gameStateManager.getCurrentTime();

        Player player = gameStateManager.getPlayer();
        this.maxSatiety = player.getMaxSatiety();
        this.maxEnergy = player.getMaxEnergy();
        this.maxHealth = player.getMaxHealth();
        this.currentSatiety = player.getCurrentSatiety();
        this.currentEnergy = player.getCurrentEnergy();
        this.currentHealth = player.getCurrentHealth();
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public int getMaxSatiety() {
        return maxSatiety;
    }

    public int getMaxEnergy() {
        return maxEnergy;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getCurrentSatiety() {
        return currentSatiety;
    }

    public int getCurrentEnergy() {
        return currentEnergy;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(currentLocation);
        dest.writeString(currentTime);
        dest.writeInt(maxSatiety);
        dest.writeInt(maxEnergy);
        dest.writeInt(maxHealth);
        dest.writeInt(currentSatiety);
        dest.writeInt(currentEnergy);
        dest.writeInt(currentHealth);
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
        this.maxSatiety = in.readInt();
        this.maxEnergy = in.readInt();
        this.maxHealth = in.readInt();
        this.currentSatiety = in.readInt();
        this.currentEnergy = in.readInt();
        this.currentHealth = in.readInt();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DashboardViewModel that = (DashboardViewModel) o;

        if (maxSatiety != that.maxSatiety) return false;
        if (maxEnergy != that.maxEnergy) return false;
        if (maxHealth != that.maxHealth) return false;
        if (currentSatiety != that.currentSatiety) return false;
        if (currentEnergy != that.currentEnergy) return false;
        if (currentHealth != that.currentHealth) return false;
        if (currentLocation != null ? !currentLocation.equals(that.currentLocation) : that.currentLocation != null)
            return false;
        return currentTime != null ? currentTime.equals(that.currentTime) : that.currentTime == null;

    }

    @Override
    public int hashCode() {
        int result = currentLocation != null ? currentLocation.hashCode() : 0;
        result = 31 * result + (currentTime != null ? currentTime.hashCode() : 0);
        result = 31 * result + maxSatiety;
        result = 31 * result + maxEnergy;
        result = 31 * result + maxHealth;
        result = 31 * result + currentSatiety;
        result = 31 * result + currentEnergy;
        result = 31 * result + currentHealth;
        return result;
    }
}
