package com.alohagoha.newsapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import com.alohagoha.newsapp.data.model.NewsItemDTO;

import androidx.appcompat.app.AppCompatActivity;

public class NewsDetailActivity extends AppCompatActivity {

    private static final String EXTRA_ITEM = "extra:item";

    public static Intent createIntent(Context context, NewsItemDTO newsItem) {
        Intent intent = new Intent(context, NewsDetailActivity.class);
        intent.putExtra(EXTRA_ITEM, newsItem);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        NewsItemDTO newsItem = (NewsItemDTO) getIntent().getSerializableExtra(EXTRA_ITEM);
        WebView webView = findViewById(R.id.webview_news_detail);
        webView.loadUrl(newsItem.getNewsUrl());

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
