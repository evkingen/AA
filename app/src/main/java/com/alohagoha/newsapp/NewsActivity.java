package com.alohagoha.newsapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.alohagoha.newsapp.data.NewsItem;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class NewsActivity extends AppCompatActivity {
    private Disposable disposable;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);
        progressBar = findViewById(R.id.progress_bar);
        recyclerView = findViewById(R.id.recycler_news);
        GridLayoutManager layoutManager = new GridLayoutManager(this, getResources().getInteger(R.integer.span_count));
        disposable = new RepositoryImpl().getNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::showNews, Throwable::printStackTrace, this::completeLoad);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new NewsItemDecoration());
    }

    private void showNews(List<NewsItem> newsList) {
        NewsDataAdapter.OnItemClickListener listener = item -> this.startActivity(NewsDetailActivity.start(this, item));
        recyclerView.setAdapter(new NewsDataAdapter(newsList, listener));
    }

    private void completeLoad() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
        disposable = null;
        recyclerView = null;
        progressBar = null;
    }
}
