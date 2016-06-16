package ava.shadesofme;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameStateManagerTest {

    private GameStateManager gameStateManager;

    @Before
    public void setUp() {
        gameStateManager = new GameStateManager();
        gameStateManager.setCurrentTime("14:00");
    }

    @Test
    public void currentGameTimeAdvancesGivenNumberOfMinutes() {
        gameStateManager.advanceBy(30);
        assertEquals("14:30", gameStateManager.currentTime);
    }

    @Test
    public void currentGameTimeAdvancesMoreThanSixtyMinutes() {
        gameStateManager.advanceBy(125);
        assertEquals("16:05", gameStateManager.currentTime);
    }

    @Test
    public void currentGameTimeAdvancesPastTwentyFourHours() {
        gameStateManager.advanceBy(690);
        assertEquals("01:30", gameStateManager.currentTime);
    }
}
