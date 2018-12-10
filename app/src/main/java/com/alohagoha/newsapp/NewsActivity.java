package com.alohagoha.newsapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.alohagoha.newsapp.data.model.NewsDTO;
import com.alohagoha.newsapp.data.NewsDataAdapter;
import com.alohagoha.newsapp.data.model.NewsItemDTO;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.alohagoha.newsapp.data.network.RestApi.getInstance;

public class NewsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static final String TAG = "NewsActivity TAG";
    private Disposable disposable;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private Spinner spinner;
    private Toolbar toolbar;
    private SwipeRefreshLayout swipeRefreshLayout;
    private NewsDataAdapter newsAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);
        initUi();
    }

    public void showNews(NewsDTO newsData) {
        List<NewsItemDTO> newsList = newsData.getResults();
        newsAdapter.replaceItems(newsList);
        progressBar.setVisibility(View.INVISIBLE);
    }

    private void completeLoad() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    private void requestLoadNews() {
        if (disposable != null) disposable.dispose();
        progressBar.setVisibility(View.VISIBLE);
        disposable = getInstance()
                .getEndPoint()
                .search(spinner.getSelectedItem().toString().toLowerCase())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::showNews, e -> Log.e(TAG, Log.getStackTraceString(e)));
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        requestLoadNews();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    protected void onStart() {
        super.onStart();
        initUx();
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindUx();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }

    private void initUi() {
        findViewsById();
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        initSpinner();
        initRecyclerView();
    }

    private void initUx() {
        swipeRefreshLayout.setOnRefreshListener(() -> {
            requestLoadNews();
            swipeRefreshLayout.setRefreshing(false);
        });
        requestLoadNews();
    }

    private void initSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.sections, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    private void initRecyclerView() {
        newsAdapter = new NewsDataAdapter();
        NewsDataAdapter.OnItemClickListener listener = item -> startActivity(NewsDetailActivity.createIntent(this, newsAdapter.getNews().get(item)));
        newsAdapter.setListener(listener);
        recyclerView.setAdapter(newsAdapter);
        GridLayoutManager layoutManager = new GridLayoutManager(this, getResources().getInteger(R.integer.span_count));
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new NewsItemDecoration());
    }

    private void unbindUx() {
        swipeRefreshLayout.setOnRefreshListener(null);
    }

    private void findViewsById() {
        toolbar = findViewById(R.id.main_toolbar);
        progressBar = findViewById(R.id.progress_bar);
        recyclerView = findViewById(R.id.recycler_news);
        spinner = findViewById(R.id.action_bar_spinner);
        swipeRefreshLayout = findViewById(R.id.lt_swipe_to_refresh);
    }
}
