package com.alohagoha.newsapp;

import com.alohagoha.newsapp.data.DataUtils;
import com.alohagoha.newsapp.data.NewsItem;

import java.util.List;

import io.reactivex.Observable;

public class RepositoryImpl implements IRepository {
    @Override
    public Observable<List<NewsItem>> getNews() {
        return Observable.fromCallable(DataUtils::generateNews);
    }
}
