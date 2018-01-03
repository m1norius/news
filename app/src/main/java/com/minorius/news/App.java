package com.minorius.news;

import android.util.Log;
import com.minorius.news.di.component.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;


/**
 * Created by Minorius on 19.12.2017.
 */

public class App extends DaggerApplication {

    public static String baseUrl = "http://212.55.87.227:8080/";

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static void myLog(Object o){
        Log.d("myLog", "log ... "+o);
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().create(this);
    }

    public String getBaseUrl() {
        return baseUrl;
    }
}
