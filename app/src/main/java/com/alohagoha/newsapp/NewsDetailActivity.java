package com.alohagoha.newsapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.alohagoha.newsapp.data.NewsItem;
import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class NewsDetailActivity extends AppCompatActivity {

    private static final String EXTRA_ITEM = "extra:item";

    public static Intent start(Context context, NewsItem newsItem) {
        Intent intent = new Intent(context, NewsDetailActivity.class);
        intent.putExtra(EXTRA_ITEM, newsItem);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        CollapsingToolbarLayout toolbarLayout = findViewById(R.id.toolbar_layout);

        Toolbar toolbar = toolbarLayout.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageView headerImage = toolbarLayout.findViewById(R.id.image_news);
        TextView title = findViewById(R.id.title_detail);
        TextView datePublish = findViewById(R.id.date_detail);
        TextView fullText = findViewById(R.id.full_text);

        NewsItem newsItem = (NewsItem) getIntent().getSerializableExtra(EXTRA_ITEM);
        toolbarLayout.setTitle(newsItem.getCategory().getName());
        Glide.with(this).load(newsItem.getImageUrl()).into(headerImage);
        title.setText(newsItem.getTitle());
        datePublish.setText(DateUtils.getRelativeDateTimeString(this,
                newsItem.getPublishDate().getTime(), DateUtils.MINUTE_IN_MILLIS,
                DateUtils.WEEK_IN_MILLIS, 0));
        fullText.setText(newsItem.getFullText());
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
