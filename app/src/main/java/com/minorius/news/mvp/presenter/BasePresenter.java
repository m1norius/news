package com.minorius.news.mvp.presenter;

/**
 * Created by Minorius on 21.12.2017.
 */

import javax.inject.Inject;

import com.minorius.news.App;
import com.minorius.news.mvp.view.*;

import org.reactivestreams.Subscription;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;

public class BasePresenter<V extends MainView> {

    @Inject
    protected V view;

    protected V getView() {
        return view;
    }

    protected <T> void subscribe(Observable<T> observable, Observer<T> observer) {
        observable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}

