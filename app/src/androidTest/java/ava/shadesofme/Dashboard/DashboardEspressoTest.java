package ava.shadesofme.Dashboard;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ava.shadesofme.MainActivity;
import ava.shadesofme.R;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.core.IsNot.not;

@RunWith(AndroidJUnit4.class)
public class DashboardEspressoTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityTestRule = new ActivityTestRule<>(MainActivity.class);
    // TODO: mock Intent to verify that contents are distributed to views correctly

    /**
     * Activity is not destroyed after each test, resulting in AmbiguousViewMatcherException for random tests.
     * This is a known issue and will be addressed in the next release - wait for it.
     * TODO: update Espresso version to make tests reliable?
     */

    @Test
    public void currentLocationAndTimeAreDisplayed() {
        onView(ViewMatchers.withId(R.id.text_location)).check(matches(isDisplayed()));
        onView(withId(R.id.text_time)).check(matches(isDisplayed()));
    }

    @Test
    public void currentPlayerStatsAreDisplayed() {
        onView(withId(R.id.text_current_satiety)).check(matches(isDisplayed()));
        onView(withId(R.id.text_current_energy)).check(matches(isDisplayed()));
        onView(withId(R.id.text_current_health)).check(matches(isDisplayed()));
    }

    @Test
    public void maxPlayerStatsAreDisplayedAs100() {
        onView(withId(R.id.text_max_satiety)).check(matches(withText("100")));
        onView(withId(R.id.text_max_energy)).check(matches(withText("100")));
        onView(withId(R.id.text_max_health)).check(matches(withText("100")));
    }

    @Test
    public void clickOnInventoryButtonDisplaysInventoryAsLocation() {
        onView(withId(R.id.button_inventory)).perform(click());
        onView(withId(R.id.text_location)).check(matches(withText("Inventory")));
    }

    @Test
    public void clickOnInventoryButtonDisplaysBackButtonInstead() {
        onView(withText("Inventory")).perform(click());
        onView(withText("<")).check(matches(isDisplayed()));
    }

    @Test
    public void clickOnBackButtonDisplaysInventoryButtonInstead() {
        onView(withText("Inventory")).perform(click());
        onView(withText("<")).perform(click());
        onView(withId(R.id.button_inventory)).check(matches(withText("Inventory")));
    }

    @Test
    public void clickOnBackButtonReplacesInventoryTitle() {
        onView(withText("Inventory")).perform(click());
        onView(withText("<")).perform(click());
        onView(withId(R.id.text_location)).check(matches(not(withText("Inventory"))));
    }

    @Test
    public void clickOnInventoryButtonDisplaysWeightCapacity() {
        // testing one distinct feature to make sure InventoryFragment is displayed
        onView(withId(R.id.button_inventory)).perform(click());
        onView(withId(R.id.text_max_weight)).check(matches(withText("50")));
        onView(withId(R.id.text_max_weight)).check(matches(isDisplayed()));
    }

    @Test
    public void clickOnBackButtonHidesInventory() {
        // testing one distinct feature to make sure InventoryFragment is not displayed
        onView(withId(R.id.button_inventory)).perform(click());
        onView(withText("<")).perform(click());
        onView(withId(R.id.text_max_weight)).check(doesNotExist());
    }

    @Test
    public void clickOnItemDisplaysNameAsTitle() {
        onView(withId(R.id.button_inventory)).perform(click());
        onData(anything()).inAdapterView(withId(R.id.grid_equipment_slots)).atPosition(0).perform(click());
        onView(withId(R.id.text_location)).check(matches(not(withText("Inventory"))));
    }
}
