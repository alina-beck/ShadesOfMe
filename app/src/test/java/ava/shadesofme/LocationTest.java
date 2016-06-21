package ava.shadesofme;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;

public class LocationTest {

    private Location location;
    private ArrayList<Item> items = new ArrayList<>();
    private GameStateManager gameStateManager = Mockito.mock(GameStateManager.class);

    @Before
    public void setUp() {
        items.add(Mockito.mock(Item.class));
        items.add(Mockito.mock(Item.class));
        items.add(Mockito.mock(Item.class));
        this.location = new Location(items, gameStateManager);
    }

    @Test
    public void whenSearchedLocationReturnsListOfItems() {
        assertEquals(items, location.search());
    }

    @Test
    public void whenSearchedLocationTellsGameStateManagerToAdvanceTime() {
        location.search();
        verify(gameStateManager).advanceBy(45);
    }

    @Test
    public void searchTimeIsCalculatedFromNumberOfItems() {
        items.add(Mockito.mock(Item.class));
        location.search();
        verify(gameStateManager).advanceBy(50);
    }
}
