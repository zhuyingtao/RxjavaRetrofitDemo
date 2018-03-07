package com.example.rxjavaretrofitdemo;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieService {
    @GET("top250")
    Observable<HttpResult<List<Movie>>> getTopMovie(@Query("start") int start, @Query("count") int count);
}
