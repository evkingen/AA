package com.alohagoha.newsapp.data.network;

import com.alohagoha.newsapp.data.model.NewsDTO;

import androidx.annotation.NonNull;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NewsEndPoint {

    @GET("svc/topstories/v2/{section}.json")
    Single<NewsDTO> search(@Path("section") @NonNull String section);
}
