package ava.shadesofme.GameState;

import java.util.Locale;
import java.util.Observable;

import ava.shadesofme.Content.ContentViewModel;
import ava.shadesofme.DataModels.Location;

public class CurrentState extends Observable {

    private String currentTime;
    private Location currentLocation;
    private ContentViewModel currentView;

    public CurrentState(String currentTime, Location currentLocation, ContentViewModel currentView) {
        this.currentTime = currentTime;
        this.currentLocation = currentLocation;
        this.currentView = currentView;
    }

    public void advanceTimeBy(int minutes) {
        int currentHours = getCurrentHours();
        int currentMinutes = getCurrentMinutes();

        currentMinutes += minutes;
        while(currentMinutes >= 60) {
            currentMinutes -= 60;
            currentHours ++;
        }
        while(currentHours >= 24) {
            currentHours -= 24;
        }

        setCurrentTime(currentHours, currentMinutes);
    }

    private int getCurrentHours() {
        String[] splitTime = currentTime.split(":");
        return Integer.parseInt(splitTime[0]);
    }

    private int getCurrentMinutes() {
        String[] splitTime = currentTime.split(":");
        return Integer.parseInt(splitTime[1]);
    }

    public String getCurrentTime() {
        return currentTime;
    }

    private void setCurrentTime(int hours, int minutes) {
        currentTime = String.format(Locale.getDefault(), "%02d:%02d", hours, minutes);
        setChanged();
        notifyObservers();
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
        setChanged();
        notifyObservers();
    }

    public ContentViewModel getCurrentView() {
        return currentView;
    }

    public void setCurrentView(ContentViewModel contentViewModel) {
        this.currentView = contentViewModel;
        setChanged();
        notifyObservers();
    }
}
