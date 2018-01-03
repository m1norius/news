package com.minorius.news.api;

import com.minorius.news.App;
import com.minorius.news.mvp.model.response.NewsResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Minorius on 21.12.2017.
 */

public interface NewsApiService {

    @GET("news/all")
    Observable<List<NewsResponse>> getNews(@Query("date") String date);

    @GET("news/region")
    Observable<List<NewsResponse>> getNewsByRegion(@Query("region") String region, @Query("date") String date);

    @GET("news/id")
    Observable<List<NewsResponse>> getNewsById(@Query("id") String id);


}
