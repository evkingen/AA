package com.alohagoha.newsapp.data.network;

import java.io.IOException;

import androidx.annotation.NonNull;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AddApiKeyInterceptor implements Interceptor {
    private static final String API_KEY_NAME = "api-key";
    private static final String API_KEY_VALUE = "1d2921d0c2fd404f84084a4544230640";

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        final Request request = chain.request();

        final HttpUrl httpUrl = request
                .url()
                .newBuilder()
                .addQueryParameter(API_KEY_NAME, API_KEY_VALUE)
                .build();

        final Request requestWithApiKey = request
                .newBuilder()
                .url(httpUrl)
                .build();

        return chain.proceed(requestWithApiKey);
    }
}
