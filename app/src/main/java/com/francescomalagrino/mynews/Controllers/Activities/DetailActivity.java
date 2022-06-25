package com.francescomalagrino.mynews.Controllers.Activities;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.francescomalagrino.mynews.Controllers.Fragments.TopStoryFragment;
import com.francescomalagrino.mynews.R;

import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    WebView mWebView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        this.configureToolbar();
        this.displayWebview();
    }

    // -------------
    // CONFIGURATION
    // -------------

    //Method to configure the Top bar view
    private void configureToolbar() {
        //Get the toolbar view inside the activity layout
        Toolbar toolbar = findViewById(R.id.activity_main_toolbar);
        //Sets the Toolbar
        setSupportActionBar(toolbar);
    }


    // -------------
    // ACTION
    // -------------

    private void displayWebview(){
        String url = getIntent().getStringExtra(TopStoryFragment.URL);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setLoadsImagesAutomatically(true);
        mWebView.loadUrl(url);
        mWebView.setWebViewClient(new WebViewClient());
    }
}