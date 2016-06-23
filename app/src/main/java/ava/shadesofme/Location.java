package ava.shadesofme;

import java.util.ArrayList;

public class Location {

    private String name;
    private ArrayList<Item> items;
    private boolean isSearched;

    public Location(String name, ArrayList<Item> items) {
        this.name = name;
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

    public String getName() {
        return name;
    }
}
