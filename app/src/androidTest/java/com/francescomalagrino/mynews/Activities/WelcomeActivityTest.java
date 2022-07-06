package com.francescomalagrino.mynews.Activities;


import android.app.Activity;
import android.app.Instrumentation;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.francescomalagrino.mynews.Controllers.Activities.MainActivity;
import com.francescomalagrino.mynews.Controllers.Activities.WelcomeActivity;
import com.francescomalagrino.mynews.R;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class WelcomeActivityTest {


    @Rule
    public ActivityScenarioRule<WelcomeActivity> mWelcomeActivityActivityTestRule =
            new ActivityScenarioRule<WelcomeActivity>(WelcomeActivity.class);

    //reference to welcomeActivity
    private WelcomeActivity mWelcomeActivity = null;
    //Activity monitor
    Instrumentation.ActivityMonitor mMonitor = getInstrumentation().addMonitor(MainActivity.class.getName(), null, false);


    @Test
    public void testLaunchMainActivityOnButtonClicked() {

        assertNotNull(mWelcomeActivity.findViewById(R.id.start_btn));
        //perform click on btn using espresso(UI)
        onView(withId(R.id.start_btn)).perform(click());

        //timeOut in milliseconds
        Activity mainActivity = getInstrumentation().waitForMonitorWithTimeout(mMonitor, 5000);
        assertNotNull(mainActivity);
        // mainActivity.finish();

    }

    @After
    public void tearDown() throws Exception {
        mWelcomeActivity = null;
    }
}