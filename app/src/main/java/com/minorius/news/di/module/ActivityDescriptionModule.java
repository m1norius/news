package com.minorius.news.di.module;

import com.minorius.news.api.NewsApiService;
import com.minorius.news.di.scope.PerActivityDescription;
import com.minorius.news.di.scope.PerActivityMain;
import com.minorius.news.module.activity_module.MainActivity;
import com.minorius.news.module.description_module.DescriptionActivity;
import com.minorius.news.mvp.view.MainView;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by Minorius on 30.12.2017.
 */

@Module
public class ActivityDescriptionModule {

    @PerActivityDescription
    @Provides
    MainView provideView(DescriptionActivity descriptionActivity) {
        return descriptionActivity;
    }

    @PerActivityDescription
    @Provides
    NewsApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(NewsApiService.class);
    }
}
