package com.example.favepertemuan3.utils;

import com.example.favepertemuan3.model.TopCharactersResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {
    @GET("top/characters")
    Call<TopCharactersResponse> getTopCharacters(@Query("page") int currentPage);
}