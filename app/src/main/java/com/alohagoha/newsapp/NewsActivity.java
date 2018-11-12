package com.alohagoha.newsapp;

import android.os.Bundle;

import com.alohagoha.newsapp.data.DataUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class NewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);

        final RecyclerView recyclerView = findViewById(R.id.recycler_news);
        GridLayoutManager layoutManager = new GridLayoutManager(this, getResources().getInteger(R.integer.span_count));
        recyclerView.setAdapter(new NewsDataAdapter(DataUtils.generateNews(),
                item -> this.startActivity(NewsDetailActivity.start(this, item))));
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new NewsItemDecoration());
    }

}
