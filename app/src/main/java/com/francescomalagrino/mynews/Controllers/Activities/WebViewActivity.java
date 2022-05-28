package com.francescomalagrino.mynews.Controllers.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

import com.francescomalagrino.mynews.R;
import com.francescomalagrino.mynews.views.ArticleSearchAdapter;

public class WebViewActivity extends AppCompatActivity {

    public static String WebUrl = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.web_view);

        Intent intent = getIntent();
        String url = intent.getStringExtra(ArticleSearchAdapter.EXTRA_MESSAGE);

        WebView webView = findViewById(R.id.webView);
        webView.loadUrl(url);
    }
}
