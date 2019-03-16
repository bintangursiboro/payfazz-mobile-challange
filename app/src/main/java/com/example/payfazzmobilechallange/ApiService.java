package com.example.payfazzmobilechallange;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    @GET("topstories.json?print=pretty")
    Observable<List<Integer>> getAllId();

    @GET("item/{newsId}.json?print=pretty")
    Call<TopNews> getTopNews(@Path("newsId") int personId);

}
