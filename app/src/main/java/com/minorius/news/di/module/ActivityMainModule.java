package com.minorius.news.di.module;

import android.content.Context;
import android.content.SharedPreferences;

import com.minorius.news.api.NewsApiService;
import com.minorius.news.di.scope.PerActivityMain;
import com.minorius.news.module.activity_module.MainActivity;
import com.minorius.news.module.activity_module.fragment.NewsFragment;
import com.minorius.news.mvp.view.MainView;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by Minorius on 19.12.2017.
 */

@Module
public class ActivityMainModule {

    @Provides
    @PerActivityMain
    NewsFragment provideFragment(){
        return NewsFragment.getInstance();
    }

    @PerActivityMain
    @Provides
    MainView provideView(MainActivity mainActivity) {
        return mainActivity;
    }

    @PerActivityMain
    @Provides
    NewsApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(NewsApiService.class);
    }

    @PerActivityMain
    @Provides
    SharedPreferences provideSharedPreferences(MainActivity activity){
        return activity.getSharedPreferences("Settings", Context.MODE_PRIVATE);
    }

}
