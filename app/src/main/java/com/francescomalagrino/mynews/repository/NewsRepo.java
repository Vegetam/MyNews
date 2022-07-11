package com.francescomalagrino.mynews.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.francescomalagrino.mynews.BuildConfig;
import com.francescomalagrino.mynews.Models.New_York_Times_Most_Popular.NYMostPopularResponse;
import com.francescomalagrino.mynews.Models.New_York_Times_Most_Popular.NYMostPopularResult;
import com.francescomalagrino.mynews.Models.New_York_Times_Top_Stories.TopStoriesResponse;
import com.francescomalagrino.mynews.Models.New_York_Times_Top_Stories.TopStoriesResultsItem;
import com.francescomalagrino.mynews.Models.Search.ArticleSearchResponse;
import com.francescomalagrino.mynews.Models.Search.DocsItem;
import com.francescomalagrino.mynews.api.Retrofit2Helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsRepo {
    private Retrofit2Helper mRetrofit2Helper = Retrofit2Helper.retrofit.create(Retrofit2Helper.class);

    public LiveData<List<TopStoriesResultsItem>> callTopStories(String section) {
        MutableLiveData<List<TopStoriesResultsItem>> result = new MutableLiveData<>();
        Call<TopStoriesResponse> topStoriesResponseCall = mRetrofit2Helper.getNYTopStories(section.toLowerCase());

           if(section == "Top Stories") {
               topStoriesResponseCall = mRetrofit2Helper.getNYTopStories("home");
           }


         topStoriesResponseCall.enqueue(new Callback<TopStoriesResponse>() {
            @Override
            public void onResponse(Call<TopStoriesResponse> call, Response<TopStoriesResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    result.postValue(response.body().getResults());
                }

            }

            @Override
            public void onFailure(Call<TopStoriesResponse> call, Throwable t) {
                Log.d("ERROR", t.getMessage());
                t.printStackTrace();
            }
        });

        return result;
    }

    public LiveData<List<DocsItem>> searchNY(String searchQuery, String theBeginDateString, String theEndDateString, ArrayList<String> categoriesSelected) {
        MutableLiveData<List<DocsItem>> result = new MutableLiveData<>();
        Call<ArticleSearchResponse> call = mRetrofit2Helper.getArticleSearch(searchQuery, Objects.requireNonNull(categoriesSelected).toString().replace("[", "").replace("]", ""), theBeginDateString, theEndDateString);


        call.enqueue(new Callback<ArticleSearchResponse>() {


            @Override
            public void onResponse(Call<ArticleSearchResponse> call, Response<ArticleSearchResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    result.postValue(response.body().getResponse().getDocs());
                }
            }

            @Override
            public void onFailure(Call<ArticleSearchResponse> call, Throwable t) {
                Log.d("ERROR", t.getMessage());
                t.printStackTrace();
            }
        });
        return result;
    }

    public LiveData<List<NYMostPopularResult>> mostPopular() {
        MutableLiveData<List<NYMostPopularResult>> result = new MutableLiveData<>();
        Call<NYMostPopularResponse> nyMostPopularResponseCall = mRetrofit2Helper.getNYMostPopular(7);



        nyMostPopularResponseCall.enqueue(new Callback<NYMostPopularResponse>() {
            @Override
            public void onResponse(Call<NYMostPopularResponse> call, Response<NYMostPopularResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    result.postValue(response.body().getResults());
                }

            }

            @Override
            public void onFailure(Call<NYMostPopularResponse> call, Throwable t) {
                Log.d("ERROR", t.getMessage());
                t.printStackTrace();
            }
        });

        return result;
    }



    }
