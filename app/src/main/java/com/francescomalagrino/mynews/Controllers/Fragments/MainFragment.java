package com.francescomalagrino.mynews.Controllers.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import com.francescomalagrino.mynews.views.MostPopularAdapter;
import com.francescomalagrino.mynews.views.TopStoriesAdapter;

import com.francescomalagrino.mynews.BuildConfig;
import com.francescomalagrino.mynews.Models.New_York_Times_Most_Popular.NYMostPopularResponse;
import com.francescomalagrino.mynews.Models.New_York_Times_Most_Popular.NYMostPopularResult;
import com.francescomalagrino.mynews.Models.New_York_Times_Top_Stories.TopStoriesResponse;
import com.francescomalagrino.mynews.Models.New_York_Times_Top_Stories.TopStoriesResultsItem;
import com.francescomalagrino.mynews.R;
import com.francescomalagrino.mynews.Utils.Constant;
import com.francescomalagrino.mynews.api.Retrofit2Helper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {


    @BindView(R.id.rv_news)
    RecyclerView rvNews;
    // 1 - Declare the SwipeRefreshLayout
    @BindView(R.id.fragment_main_swipe_container)
    SwipeRefreshLayout swipeRefreshLayout;
    //    @BindView(R.id.shimmer_view_container)
//    ShimmerFrameLayout mShimmerViewContainer;
    private RecyclerView myNewsRecyclerView;
    private TopStoriesAdapter mTopStoriesAdapter;
    private MostPopularAdapter mMostPopularAdapter;
    private List<NYMostPopularResult> mNYMostPopularResults = new ArrayList<>();
    private List<TopStoriesResultsItem> mTopStoriesResultsItems = new ArrayList<>();
    private Retrofit2Helper mRetrofit2Helper = Retrofit2Helper.retrofit.create(Retrofit2Helper.class);

    public MainFragment() {
        // Required empty public constructor
    }



    public static MainFragment newInstance(String sectionName) {
        Bundle bundle = new Bundle();
        bundle.putString(Constant.NYT_SECTION_NAME, sectionName);
        MainFragment mainFragment = new MainFragment();
        mainFragment.setArguments(bundle);
        return mainFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        //Initialize ButterKnife
        ButterKnife.bind(this, view);
        mTopStoriesAdapter = new TopStoriesAdapter(mTopStoriesResultsItems);
        mMostPopularAdapter = new MostPopularAdapter(mNYMostPopularResults);

        myNewsRecyclerView = view.findViewById(R.id.rv_news);
        myNewsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        myNewsRecyclerView.setItemAnimator(new DefaultItemAnimator());


        executeHttpRequestWithRetrofit();
        //  Configure the SwipeRefreshLayout
        this.configureSwipeRefreshLayout();

        return view;
    }

    private void configureSwipeRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener(this::executeHttpRequestWithRetrofit);
    }


    public void callTopStories(String section) {
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

        topStoriesResponseCall.enqueue(new Callback<TopStoriesResponse>() {
            @Override
            public void onResponse(Call<TopStoriesResponse> call, Response<TopStoriesResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // -------------------
                    // UPDATE UI
                    // -------------------
                    mTopStoriesResultsItems.addAll(response.body().getResults());
                    mTopStoriesAdapter.notifyDataSetChanged();
                    swipeRefreshLayout.setRefreshing(false);
                    // stop animating Shimmer and hide the layout
//                    mShimmerViewContainer.stopShimmer();
//                    mShimmerViewContainer.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailure(Call<TopStoriesResponse> call, Throwable t) {
                Log.d("ERROR", t.getMessage());
                t.printStackTrace();
            }
        });
    }

    public void callMostPopular() {
        Call<NYMostPopularResponse> nyMostPopularResponseCall = mRetrofit2Helper.getNYMostPopular(7, BuildConfig.MY_NYT_API_KEY);
        nyMostPopularResponseCall.enqueue(new Callback<NYMostPopularResponse>() {
            @Override
            public void onResponse(Call<NYMostPopularResponse> call, Response<NYMostPopularResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    mNYMostPopularResults.clear();
                    // -------------------
                    // UPDATE UI
                    // -------------------
                    mNYMostPopularResults.addAll(response.body().getResults());
                    mMostPopularAdapter.notifyDataSetChanged();
                    swipeRefreshLayout.setRefreshing(false);

                    // stop animating Shimmer and hide the layout
//                    mShimmerViewContainer.stopShimmer();
//                    mShimmerViewContainer.setVisibility(View.GONE);

                }

            }

            @Override
            public void onFailure(Call<NYMostPopularResponse> call, Throwable t) {
                Log.d("ERROR", t.getMessage());
                t.printStackTrace();
            }
        });


    }

    private void executeHttpRequestWithRetrofit() {

        if (getArguments() != null) {
            String selectedSection = getArguments().getString(Constant.NYT_SECTION_NAME, Constant.NEWS_SECTIONS[0]);
            switch (selectedSection) {
                case "Most Popular":
                    myNewsRecyclerView.setAdapter(mMostPopularAdapter);
                    callMostPopular();
                    break;
                default:
                    myNewsRecyclerView.setAdapter(mTopStoriesAdapter);
                    callTopStories(selectedSection);

            }
        }

    }

}