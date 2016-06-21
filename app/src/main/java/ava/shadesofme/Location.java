package ava.shadesofme;

import java.util.ArrayList;

public class Location {

    private ArrayList<Item> items;
    private GameStateManager gameStateManager;

    public Location(ArrayList<Item> items, GameStateManager gameStateManager) {
        this.items = items;
        this.gameStateManager = gameStateManager;
    }

    public ArrayList<Item> search() {
        int searchTime = 30 + (items.size() * 5);
        gameStateManager.advanceBy(searchTime);
        return items;
    }
}
