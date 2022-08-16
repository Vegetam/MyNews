package com.francescomalagrino.mynews.Activities;


import android.app.Activity;
import android.app.Instrumentation;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.francescomalagrino.mynews.Controllers.Activities.NYSearchResultActivity;
import com.francescomalagrino.mynews.Controllers.Activities.SearchActivity;
import com.francescomalagrino.mynews.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.PickerActions.setDate;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertNotNull;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class SearchActivityTest {


    @Rule
    public ActivityScenarioRule<SearchActivity> mSearchActivityActivityTestRule =
            new ActivityScenarioRule<SearchActivity>(SearchActivity.class);


    //reference to welcomeActivity
    //Activity monitor
    Instrumentation.ActivityMonitor mMonitor = getInstrumentation().addMonitor(NYSearchResultActivity.class.getName(), null, false);



    @Test
    public void testLaunchNYSearchResultActivityOnButtonClicked() {

    //    assertNotNull(mSearchActivity.findViewById(R.id.searchButton));
        //perform click on btn using espresso(UI)
        onView(withId(R.id.searchButton)).perform(click());

        //timeOut in milliseconds
        Activity nYSearchResultActivity = getInstrumentation().waitForMonitorWithTimeout(mMonitor, 5000);
        assertNotNull(nYSearchResultActivity);
        nYSearchResultActivity.finish();

    }

    @Test
    public void testViewsDisplayOnActivity() {
        onView(withId(R.id.editTextSearch)).check(matches(isDisplayed()));

        onView(withId(R.id.editTextBeginDate)).check(matches(isDisplayed()));
        onView(withId(R.id.editTextEndDate)).check(matches(isDisplayed()));

        onView(withId(R.id.artsCB)).perform(click()).check(matches(isDisplayed()));
        onView(withId(R.id.politicsCB)).perform(click()).check(matches(isDisplayed()));
        onView(withId(R.id.businessCB)).perform(click()).check(matches(isDisplayed()));
        onView(withId(R.id.sportsCB)).perform(click()).check(matches(isDisplayed()));
        onView(withId(R.id.entrepreneursCB)).perform(click()).check(matches(isDisplayed()));
        onView(withId(R.id.travelCB)).perform(click()).check(matches(isDisplayed()));
    }

    @Test
    public void testViewsCheckable() {
        // -------------------------------------------------------------
        // SearchEditText and Check and  un-checking the checkboxes
        // --------------------------------------------------------------

        onView(withId(R.id.editTextSearch)).perform(clearText());


        onView(withId(R.id.artsCB)).perform(click());
       onView(withId(R.id.businessCB)).perform(click());
        onView(withId(R.id.politicsCB)).perform(click());
         onView(withId(R.id.sportsCB)).perform(click());
        onView(withId(R.id.entrepreneursCB)).perform(click());
         onView(withId(R.id.travelCB)).perform(click());

        // ------------------------------------------------------------------------------------
        // SearchEditText Writing Hello Search Test from Belgium StayHome StaySafe on the EditText
        // -----------------------------------------------------------------------------------

        onView(withId(R.id.editTextSearch))
                .perform(typeText("Hello Search Test from Italy StayHome StaySafe"))
                .check(matches(isDisplayed()));


        // --------------------------------------------------------------
        // Checking the categoriesSelected are checked when clicked
        // --------------------------------------------------------------

        onView(withId(R.id.artsCB)).check(matches(isChecked()));
        onView(withId(R.id.businessCB)).check(matches(isDisplayed()));
        onView(withId(R.id.sportsCB)).check(matches(isDisplayed()));
        onView(withId(R.id.entrepreneursCB)).check(matches(isDisplayed()));
        onView(withId(R.id.travelCB)).check(matches(isDisplayed()));

        // --------------------------------------------------------------
        // unChecking the categoriesSelected are checked when clicked
        // --------------------------------------------------------------

         onView(withId(R.id.artsCB)).perform(click());
         onView(withId(R.id.politicsCB)).perform(click());
         onView(withId(R.id.businessCB)).perform(click());
        onView(withId(R.id.sportsCB)).perform(click());
       onView(withId(R.id.entrepreneursCB)).perform(click());
        onView(withId(R.id.travelCB)).perform(click());


    }

    @Test
    public void testBeginDateInDatePicker() {
        // Show the date picker
        onView(withId(R.id.editTextBeginDate)).perform(click());
        // Sets a date on the date picker widget
        onView(isAssignableFrom(DatePicker.class)).perform(setDate(2022, 11, 27));
    }

    @Test
    public void testEndDateInDatePicker() {
        // Show the date picker
        onView(withId(R.id.editTextEndDate)).perform(click());
        // Sets a date on the date picker widget
        onView(isAssignableFrom(DatePicker.class)).perform(setDate(2022, 11, 27));
    }

}
