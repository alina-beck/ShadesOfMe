package ava.shadesofme;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class GameStateTest {

    private GameState gameState;
    private Location mockLocation = Mockito.mock(Location.class);

    @Before
    public void setUp() {
        gameState = new GameState("14:00", mockLocation);
    }

    @Test
    public void currentGameTimeAdvancesGivenNumberOfMinutes() {
        gameState.advanceTimeBy(30);
        assertEquals("14:30", gameState.getCurrentTime());
    }

    @Test
    public void currentGameTimeAdvancesMoreThanSixtyMinutes() {
        gameState.advanceTimeBy(125);
        assertEquals("16:05", gameState.getCurrentTime());
    }

    @Test
    public void currentGameTimeAdvancesPastTwentyFourHours() {
        gameState.advanceTimeBy(690);
        assertEquals("01:30", gameState.getCurrentTime());
    }
}
