package com.francescomalagrino.mynews;

import androidx.lifecycle.MutableLiveData;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.francescomalagrino.mynews.Controllers.Activities.MainActivity;
import com.francescomalagrino.mynews.Controllers.Activities.SearchActivity;
import com.francescomalagrino.mynews.Controllers.Fragments.MainFragment;
import com.francescomalagrino.mynews.Models.New_York_Times_Top_Stories.TopStoriesResultsItem;
import com.francescomalagrino.mynews.repository.NewsRepo;
import com.francescomalagrino.mynews.views.ViewPagerAdapter;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.when;

public class NewsRepoTest {
    NewsRepo mNewsRepo = new NewsRepo();

    @Mock
    MainFragment mMainFragment;

    @Rule
    public ActivityScenarioRule<MainActivity> mMainActivityActivityTestRule =
            new ActivityScenarioRule<MainActivity>(MainActivity.class);



    @Test
    public void mockResponseWithCodeAndContent()  {
        MutableLiveData<List<TopStoriesResultsItem>> result = new MutableLiveData<>();
        List<TopStoriesResultsItem>  topStoriesResultsItems = new ArrayList();
        result.postValue(topStoriesResultsItems);
        String section = "Technology";
        mMainActivityActivityTestRule.getScenario().onActivity(activity -> {
           MainFragment mainFragment = ((ViewPagerAdapter) activity.mViewPager.getAdapter()).getItem(activity.mViewPager.getCurrentItem());
            when(mainFragment.newsRepo.callTopStories(section)).thenReturn(result);
            mainFragment.callTopStories(section);
            Assert.assertEquals(mainFragment.mTopStoriesResultsItems,topStoriesResultsItems);
        });


    }

}
