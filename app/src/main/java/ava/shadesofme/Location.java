package ava.shadesofme;

import java.util.ArrayList;

public class Location {

    private ArrayList<Item> items;
    private boolean isSearched;

    public Location(ArrayList<Item> items) {
        this.items = items;
        this.isSearched = false;
    }

    public boolean isSearched() {
        return isSearched;
    }

    public void setSearched(boolean searched) {
        this.isSearched = searched;
    }

    public ArrayList<Item> getItems() {
        return items;
    }
}
