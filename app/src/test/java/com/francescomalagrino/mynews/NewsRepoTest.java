package com.francescomalagrino.mynews;

import com.francescomalagrino.mynews.Models.New_York_Times_Top_Stories.TopStoriesResponse;
import com.francescomalagrino.mynews.api.Retrofit2Helper;
import com.francescomalagrino.mynews.repository.NewsRepo;

import org.junit.Assert;
import org.junit.Test;


public class NewsRepoTest {
    NewsRepo mNewsRepo = new NewsRepo();

    @Test
    public void validateSection() throws Exception{
        Assert.assertNotNull(mNewsRepo.callTopStories(""));
    }

    @Test
    public void validateSectionTechnology() throws Exception{
        Assert.assertNotNull(mNewsRepo.callTopStories("Technology"));
    }


    @Test
    public void validatesMostPopular() throws Exception{
        Assert.assertNotNull(mNewsRepo.mostPopular());
    }

}
