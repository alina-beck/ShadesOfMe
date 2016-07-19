package ava.shadesofme;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.not;

/** Testing that basic navigation works (i.e. all display changes that don't involve a game state change),
 * only concerning inventory for now. */

@RunWith(AndroidJUnit4.class)
public class Espresso_NavigationTest {

    private static final String INVENTORY = "Inventory";
    private static final String BACK = "<";
    private static final String MAIN = "Main Screen";

    @Rule
    public ActivityTestRule<MainActivity> mainActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    /** Inventory button */

    @Test
    public void firstClickOnNavigationButtonOpensInventory() {
        onView(withText(INVENTORY)).perform(click());

        onView(withId(R.id.button_navigation)).check(matches(withText(BACK)));
        onView(withId(R.id.text_title)).check(matches(withText(INVENTORY)));
        onView(withId(R.id.grid_equipment_slots)).check(matches(isDisplayed()));
    }

    /** Back button */

    @Test
    public void secondClickOnNavigationButtonGoesBackToFirstScreen() {
        onView(withId(R.id.button_navigation)).perform(click());
        onView(withId(R.id.button_navigation)).perform(click());

        onView(withId(R.id.button_navigation)).check(matches(withText(INVENTORY)));
        onView(withId(R.id.text_title)).check(matches(withText(MAIN)));
        onView(withId(R.id.grid_equipment_slots)).check(doesNotExist());
    }

    /** Items in inventory */

    @Test
    public void clickOnItemOpensItemDetails() {
        onView(withId(R.id.button_navigation)).perform(click());
        onData(anything()).inAdapterView(withId(R.id.grid_equipment_slots)).atPosition(0).perform(click());

        onView(withId(R.id.text_title)).check(matches(not(withText(INVENTORY))));
        onView(withId(R.id.text_item_description)).check(matches(isDisplayed()));
    }

    @Test
    public void clickOnNavigationButtonInItemDetailGoesBackToInventory() {
        onView(withId(R.id.button_navigation)).perform(click());
        onData(anything()).inAdapterView(withId(R.id.grid_equipment_slots)).atPosition(0).perform(click());
        onView(withId(R.id.button_navigation)).perform(click());

        onView(withId(R.id.text_title)).check(matches(withText(INVENTORY)));
        onView(withId(R.id.grid_equipment_slots)).check(matches(isDisplayed()));
    }
}
