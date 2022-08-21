package com.francescomalagrino.mynews.Activities;

import android.app.Instrumentation;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.francescomalagrino.mynews.Controllers.Activities.NotificationsActivity;
import com.francescomalagrino.mynews.Controllers.Activities.SearchActivity;
import com.francescomalagrino.mynews.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class NotificationsActivityTest {
    @Rule
    public ActivityScenarioRule<NotificationsActivity> mMainActivityActivityTestRule =
            new ActivityScenarioRule<NotificationsActivity>(NotificationsActivity.class);

    //reference to NotificationsActivity
    private NotificationsActivity mNotificationsActivity = null;

    Instrumentation.ActivityMonitor mMonitor = getInstrumentation().addMonitor(NotificationsActivity.class.getName(), null, false);
    private Object swipeUp;


    @Test
    public void testViewsDisplayOnActivity() {
        onView(withId(R.id.editTextSearchNotification)).check(matches(isDisplayed()));

        onView(withId(R.id.artsCB)).perform(click()).check(matches(isDisplayed()));
        onView(withId(R.id.politicsCB)).perform(click()).check(matches(isDisplayed()));
        onView(withId(R.id.businessCB)).perform(click()).check(matches(isDisplayed()));
        onView(withId(R.id.sportsCB)).perform(click()).check(matches(isDisplayed()));
        onView(withId(R.id.entrepreneursCB)).perform(click()).check(matches(isDisplayed()));
        onView(withId(R.id.travelCB)).perform(click()).check(matches(isDisplayed()));
    }

    @Test
    public void searchTopicToBeNotify() {
        onView(withId(R.id.editTextSearchNotification))
                .perform(typeText("StayHome StaySafe"))
                .check(matches(isDisplayed()));
    }
}