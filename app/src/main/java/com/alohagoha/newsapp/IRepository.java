package com.alohagoha.newsapp;

import com.alohagoha.newsapp.data.NewsItem;

import java.util.List;

import io.reactivex.Observable;

public interface IRepository {
    Observable<List<NewsItem>> getNews();
}
