package ava.shadesofme;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/** Tests that all expected components are displayed when Game is started */

@RunWith(AndroidJUnit4.class)
public class Espresso_GameStartTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void titleAndTimeAreDisplayed() {
        // making sure that DashboardFragment is displayed
        onView(withId(R.id.text_title)).check(matches(isDisplayed()));
        onView(withId(R.id.text_time)).check(matches(isDisplayed()));
    }

    @Test
    public void locationDescriptionIsDisplayed() {
        // making sure the initial ContentFragment is a location
        // TODO: activate as soon as Locations are implemented
        // onView(withId(R.id.text_location_description)).check(matches(isDisplayed()));
    }
}
