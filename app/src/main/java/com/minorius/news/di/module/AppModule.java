package com.minorius.news.di.module;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.minorius.news.App;
import com.minorius.news.di.scope.PerActivityDescription;
import com.minorius.news.di.scope.PerActivityMain;
import com.minorius.news.di.scope.PerFragment;
import com.minorius.news.module.activity_module.MainActivity;
import com.minorius.news.module.activity_module.fragment.NewsFragment;
import com.minorius.news.module.description_module.DescriptionActivity;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Minorius on 19.12.2017.
 */

@Module
public abstract class AppModule {

    @Singleton
    @Provides
    static String provideURL(App app){
        return app.getBaseUrl();
    }

    @Singleton
    @Provides
    static OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();
    }

    @Singleton
    @Provides
    static RxJava2CallAdapterFactory provideRxJava2CallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    @Singleton
    @Provides
    static Retrofit provideRetrofit(OkHttpClient client,
                             GsonConverterFactory converterFactory,
                             RxJava2CallAdapterFactory adapterFactory,
                             String baseUrl) {


        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(adapterFactory)
                .client(client)
                .build();
    }

    @Singleton
    @Provides
    static GsonConverterFactory provideGSONConverterFactory() {
        return GsonConverterFactory.create();
    }

    @Provides
    @Singleton
    static Context context(App app) {
        return app.getApplicationContext();
    }



        @ContributesAndroidInjector(modules = {ActivityMainModule.class})
        @PerActivityMain
        abstract MainActivity mainActivity();

        @ContributesAndroidInjector(modules = {ActivityDescriptionModule.class})
        @PerActivityDescription
        abstract DescriptionActivity descriptionActivity();

        @ContributesAndroidInjector(modules = {FragmentModule.class})
        @PerFragment
        abstract NewsFragment newsFragment();

}
