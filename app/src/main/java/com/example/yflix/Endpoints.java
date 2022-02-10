package com.example.yflix;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Endpoints {

    HttpLoggingInterceptor loggin;
    Retrofit retrofit;
    OkHttpClient.Builder httpClient;

    public Endpoints(){
        loggin =  new HttpLoggingInterceptor();
        httpClient = new OkHttpClient.Builder();
    }

    public static final String URL_BASE = "https://api.themoviedb.org/3/movie/";

    private static MoviesInterface API_SERVICE;

    public MoviesInterface getApiService(){
        loggin.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(loggin);
        if (API_SERVICE == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(URL_BASE)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
            API_SERVICE = retrofit.create(MoviesInterface.class);
        }
        return API_SERVICE;
    }
}
