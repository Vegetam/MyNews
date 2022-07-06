package com.francescomalagrino.mynews.repository;

import com.francescomalagrino.mynews.BuildConfig;
import com.francescomalagrino.mynews.Models.New_York_Times_Most_Popular.NYMostPopularResponse;
import com.francescomalagrino.mynews.Models.New_York_Times_Top_Stories.TopStoriesResponse;
import com.francescomalagrino.mynews.Models.Search.ArticleSearchResponse;
import com.francescomalagrino.mynews.api.Retrofit2Helper;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;

public class NewsRepo {
    private Retrofit2Helper mRetrofit2Helper = Retrofit2Helper.retrofit.create(Retrofit2Helper.class);

    public Call<TopStoriesResponse> callTopStories(String section) {
        Call<TopStoriesResponse> topStoriesResponseCall;
        switch (section) {
            case "Technology":
                topStoriesResponseCall = mRetrofit2Helper.getNYTopStories("technology", BuildConfig.MY_NYT_API_KEY);
                break;
            case "Business":
                topStoriesResponseCall = mRetrofit2Helper.getNYTopStories("business", BuildConfig.MY_NYT_API_KEY);
                break;
            case "Sports":
                topStoriesResponseCall = mRetrofit2Helper.getNYTopStories("sports", BuildConfig.MY_NYT_API_KEY);
                break;
            case "Travel":
                topStoriesResponseCall = mRetrofit2Helper.getNYTopStories("travel", BuildConfig.MY_NYT_API_KEY);
                break;
            case "Fashion":
                topStoriesResponseCall = mRetrofit2Helper.getNYTopStories("fashion", BuildConfig.MY_NYT_API_KEY);
                break;
            case "Science":
                topStoriesResponseCall = mRetrofit2Helper.getNYTopStories("science", BuildConfig.MY_NYT_API_KEY);
                break;
            case "Automobiles":
                topStoriesResponseCall = mRetrofit2Helper.getNYTopStories("automobiles", BuildConfig.MY_NYT_API_KEY);
                break;
            case "Politics":
                topStoriesResponseCall = mRetrofit2Helper.getNYTopStories("politics", BuildConfig.MY_NYT_API_KEY);
                break;
            case "Arts":
                topStoriesResponseCall = mRetrofit2Helper.getNYTopStories("arts", BuildConfig.MY_NYT_API_KEY);
                break;
            case "World":
                topStoriesResponseCall = mRetrofit2Helper.getNYTopStories("world", BuildConfig.MY_NYT_API_KEY);
                break;
            case "Health":
                  topStoriesResponseCall = mRetrofit2Helper.getNYTopStories("health", BuildConfig.MY_NYT_API_KEY);
                break;
            case "Food":
                  topStoriesResponseCall = mRetrofit2Helper.getNYTopStories("food", BuildConfig.MY_NYT_API_KEY);
                break;
            case "Movies":
                 topStoriesResponseCall = mRetrofit2Helper.getNYTopStories("movies", BuildConfig.MY_NYT_API_KEY);
                break;
            case "Books":
                 topStoriesResponseCall = mRetrofit2Helper.getNYTopStories("books", BuildConfig.MY_NYT_API_KEY);
                break;
            case "RealEstate":
                 topStoriesResponseCall = mRetrofit2Helper.getNYTopStories("realestate", BuildConfig.MY_NYT_API_KEY);
                break;

            default:
                 topStoriesResponseCall = mRetrofit2Helper.getNYTopStories("home", BuildConfig.MY_NYT_API_KEY);
        }


        return topStoriesResponseCall;
    }

    public Call<ArticleSearchResponse> searchNY() {


        String searchQuery = "";
        String theBeginDateString = null;
        String theEndDateString = null;
        ArrayList<String> categoriesSelected = null;

        return mRetrofit2Helper.getArticleSearch(searchQuery, Objects.requireNonNull(categoriesSelected).toString().replace("[", "").replace("]", ""), theBeginDateString, theEndDateString, BuildConfig.MY_NYT_API_KEY);

    }

    public  Call<NYMostPopularResponse> mostPopular(){
        return mRetrofit2Helper.getNYMostPopular(7, BuildConfig.MY_NYT_API_KEY);
    }

    }
