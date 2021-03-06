package com.francescomalagrino.mynews.Controllers.Activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.francescomalagrino.mynews.Utils.ListItem;
import com.francescomalagrino.mynews.Models.Search.ArticleSearchResponse;
import com.francescomalagrino.mynews.repository.NewsRepo;
import com.francescomalagrino.mynews.views.NewsAdapter;
import com.francescomalagrino.mynews.R;
import com.francescomalagrino.mynews.api.Retrofit2Helper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NYSearchResultActivity extends AppCompatActivity {

    @BindView(R.id.noResultsTV)
    TextView noResultsTV;
    @BindView(R.id.search_recycler_view)
    RecyclerView searchRecyclerView;

    private NewsAdapter mAdapter;
    private List<ListItem> listItems;
    private NewsRepo newsRepo = new NewsRepo();


    //  private Retrofit2Helper mRetrofit2Helper = Retrofit2Helper.retrofit.create(Retrofit2Helper.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_n_y_search_result);
        ButterKnife.bind(this);

        String searchQuery = "";
        String theBeginDateString = null;
        String theEndDateString = null;
        ArrayList<String> categoriesSelected = null;
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            searchQuery = bundle.getString("searchQuery");
            theBeginDateString = bundle.getString("theBeginDateString");
            theEndDateString = bundle.getString("theEndDateString");
            categoriesSelected = bundle.getStringArrayList("categoriesSelected");
        }

        if (Objects.requireNonNull(searchQuery).equals("")) {
            noResultsTV.setText("Please enter a word in the search bar");
        } else {

            RecyclerView recyclerView = findViewById(R.id.search_recycler_view);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(NYSearchResultActivity.this));
            listItems = new ArrayList<>();
            mAdapter = new NewsAdapter(listItems, NYSearchResultActivity.this);
            Toast.makeText(this, searchQuery, Toast.LENGTH_SHORT).show();
            recyclerView.setAdapter(mAdapter);
            newsRepo.searchNY(searchQuery,theBeginDateString,theEndDateString,categoriesSelected).observe(this, docsItems -> {
                for (int i = 0; i < docsItems.size(); i++) {

                    @SuppressLint("SimpleDateFormat") DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                    @SuppressLint("SimpleDateFormat") DateFormat outputFormat = new SimpleDateFormat("MM/dd/yyyy");
                    String inputDateStr = docsItems.get(i).getPubDate();
                    if (inputDateStr == null) {
                        inputDateStr = "";
                    }

                    Date date = null;
                    try {
                        date = inputFormat.parse(inputDateStr);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    String outputDateStr = outputFormat.format(date);

                    if (docsItems.get(i).getMultimedia().size() != 0) {
                        ListItem listItem = new ListItem("",
                                "",
                                docsItems.get(i).getSnippet(),
                                outputDateStr,
                                docsItems.get(i).getMultimedia().get(0).getUrl(),
                                docsItems.get(i).getWebUrl(),
                                NYSearchResultActivity.this);

                        listItems.add(listItem);
                    } else {
                        ListItem listItem = new ListItem("",
                                "",
                                docsItems.get(i).getSnippet(),
                                outputDateStr,
                                "".replace("https://", "http://"),
                                docsItems.get(i).getWebUrl(),
                                NYSearchResultActivity.this);
                        listItems.add(listItem);

                    }
                }
                if (docsItems.size() == 0) {
                    noResultsTV.setText("No articles matching your search. Try being less specific");
                } else {
                    noResultsTV.setText("");
                }
                mAdapter.notifyDataSetChanged();
            });
        }
    }

}