package com.example.yflix;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MoviesInterface {

    String API_ROUT="popular?api_key=a836e9d662c1e554656d4cb03fe5d289&language=es-ES&page=";

    @GET(API_ROUT)
    Call<Pages> getMovies(@Query("page") int idPage);
}