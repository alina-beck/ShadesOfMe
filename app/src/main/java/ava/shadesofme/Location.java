package ava.shadesofme;

import java.util.ArrayList;

public class Location {

    private ArrayList<Item> items;
    private GameStateManager gameStateManager;
    private boolean isSearched;

    public Location(ArrayList<Item> items, GameStateManager gameStateManager) {
        this.items = items;
        this.gameStateManager = gameStateManager;
        this.isSearched = false;
    }

    public ArrayList<Item> search() {
        if (!isSearched) {
            int searchTime = 30 + (items.size() * 5);
            gameStateManager.advanceBy(searchTime);
            isSearched = true;
        }
        return items;
    }

    public boolean isSearched() {
        return isSearched;
    }

    public void setSearched(boolean searched) {
        this.isSearched = searched;
    }
}
