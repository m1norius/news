package com.minorius.news.mvp.presenter;

import com.minorius.news.App;
import com.minorius.news.api.NewsApiService;
import com.minorius.news.mvp.model.response.NewsResponse;
import com.minorius.news.mvp.view.MainView;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Minorius on 21.12.2017.
 */

public class NewsPresenter extends BasePresenter<MainView> implements Observer<List<NewsResponse>> {

    @Inject NewsApiService newsApiService;

    private Disposable disposable;

    @Inject
    public NewsPresenter() {}

    public void getNews(String date){
        Observable<List<NewsResponse>> response = newsApiService.getNews(date);
        subscribe(response, this);
    }

    public void getNewsByRegion(String region, String date){
        Observable<List<NewsResponse>> response = newsApiService.getNewsByRegion(region, date);
        subscribe(response, this);
    }

    public void getNewsById(String id){
        Observable<List<NewsResponse>> response = newsApiService.getNewsById(id);
        subscribe(response, this);
    }

    public void unsubscribe(){
        this.disposable.dispose();
    }



    @Override
    public void onNext(List<NewsResponse> newsResponse) {
        getView().onDataLoaded(newsResponse);
    }

    @Override
    public void onError(Throwable e) {
        App.myLog(e);
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onSubscribe(Disposable d) {
        this.disposable = d;
    }
}