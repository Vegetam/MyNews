package com.francescomalagrino.mynews.api;

import com.francescomalagrino.mynews.BuildConfig;
import com.francescomalagrino.mynews.Models.New_York_Times_Most_Popular.NYMostPopularResponse;
import com.francescomalagrino.mynews.Models.New_York_Times_Top_Stories.TopStoriesResponse;
import com.francescomalagrino.mynews.Models.Search.ArticleSearchResponse;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Retrofit2Helper {

    //Base URL from the NewYorkTimes APIs
    public static final String NEW_YORK_TIMES_URL = "https://api.nytimes.com/svc/";


    /*Ok HttpLoggingInterceptor*/

    HttpLoggingInterceptor logging = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);


    OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addInterceptor(logging).build();



    final static String API_KEY_IDENTIFIER = "api-key";

    static OkHttpClient getHttpClient(){
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                HttpUrl originalUrl = original.url();
                HttpUrl url = originalUrl.newBuilder()
                        .addQueryParameter(API_KEY_IDENTIFIER, BuildConfig.MY_NYT_API_KEY)
                        .build();
                Request.Builder requestBuilder = original.newBuilder().url(url);
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });
        httpClient.addInterceptor(logging);
        return httpClient.build();
    }


    
    //Retrofit Instance
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(NEW_YORK_TIMES_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getHttpClient())
            .build();

    @GET("mostpopular/v2/viewed/{period}.json")
    Call<NYMostPopularResponse> getNYMostPopular(
            @Path("period") int PERIOD
    );

    /*--------------------
    |NY Times Top Stories API
    |
    |---------------------*/

    @GET("topstories/v2/{section}.json")
    Call<TopStoriesResponse> getNYTopStories(
            @Path("section") String SECTION
    );


     /*--------------------
    |
    |Article Search API
    |---------------------*/


    @GET("search/v2/articlesearch.json")
    Call<ArticleSearchResponse> getArticleSearch(
            @Query("q") String QUERY,
            @Query("fq") String FILTER,
            @Query("begin_date") String BEGIN_DATE,
            @Query("end_date") String END_DATE
    );
}