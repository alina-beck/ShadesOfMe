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
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class DashboardEspressoTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void currentLocationIsDisplayed() {
        onView(withId(R.id.text_location)).check(matches(isDisplayed()));
    }

    @Test
    public void currentTimeIsDisplayed() {
        onView(withId(R.id.text_time)).check(matches(isDisplayed()));
    }

    @Test
    public void currentSatietyIsDisplayed() {
        onView(withId(R.id.text_current_satiety)).check(matches(isDisplayed()));
    }

    @Test
    public void currentEnergyIsDisplayed() {
        onView(withId(R.id.text_current_energy)).check(matches(isDisplayed()));
    }

    @Test
    public void currentHealthIsDisplayed() {
        onView(withId(R.id.text_current_health)).check(matches(isDisplayed()));
    }

    @Test
    public void maxSatietyIsDisplayedAs100() {
        onView(withId(R.id.text_max_satiety)).check(matches(withText("100")));
    }

    @Test
    public void maxEnergyIsDisplayedAs100() {
        onView(withId(R.id.text_max_energy)).check(matches(withText("100")));
    }

    @Test
    public void maxHealthIsDisplayedAs100() {
        onView(withId(R.id.text_max_health)).check(matches(withText("100")));
    }

    @Test
    public void inventoryButtonIsDisplayed() {
        onView(withId(R.id.button_inventory)).check(matches(isDisplayed()));
    }
}
