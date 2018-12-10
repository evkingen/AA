package com.alohagoha.newsapp.data.network;

import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public final class RestApi {
    private static final String URL = "https://api.nytimes.com/";
    private static final int TIMEOUT_SECONDS = 2;
    private static RestApi restApi;
    private final NewsEndPoint endPoint;

    private RestApi() {
        final OkHttpClient client = createOkHttpClient();
        final Retrofit retrofit = createRetrofitBuilder(client);
        endPoint = retrofit.create(NewsEndPoint.class);
    }

    public static synchronized RestApi getInstance() {
        if (restApi == null)
            restApi = new RestApi();
        return restApi;
    }

    @NonNull
    private Retrofit createRetrofitBuilder(@NonNull OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(URL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public NewsEndPoint getEndPoint() {
        return endPoint;
    }

    @NonNull
    private OkHttpClient createOkHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(new AddApiKeyInterceptor())
                .connectTimeout(TIMEOUT_SECONDS,TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT_SECONDS,TimeUnit.SECONDS)
                .readTimeout(TIMEOUT_SECONDS,TimeUnit.SECONDS)
                .build();
    }
}
