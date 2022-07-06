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


    private CheckBox cb_arts;
    private CheckBox cb_politics;
    private CheckBox cb_business;
    private CheckBox cb_sports;
    private CheckBox cb_entrepreneurs;
    private CheckBox cb_travel;
    private Switch notification_switch;

    @Before
    public void setUp() throws Exception {
        // ----------------------------------
        //CategoriesCheckBoxes
        // ----------------------------------

        cb_arts = mNotificationsActivity.findViewById(R.id.artsCB);
        cb_politics = mNotificationsActivity.findViewById(R.id.politicsCB);
        cb_business = mNotificationsActivity.findViewById(R.id.businessCB);
        cb_sports = mNotificationsActivity.findViewById(R.id.sportsCB);
        cb_entrepreneurs = mNotificationsActivity.findViewById(R.id.entrepreneursCB);
        cb_travel = mNotificationsActivity.findViewById(R.id.travelCB);

        //Text
        EditText textInputEditText = mNotificationsActivity.findViewById(R.id.editTextSearchNotification);

        //Switch notifications
        notification_switch = mNotificationsActivity.findViewById(R.id.notificationSwitch);

    }

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