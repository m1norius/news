package com.minorius.news.di.module;

import android.support.v7.widget.LinearLayoutManager;

import com.minorius.news.di.scope.PerFragment;
import com.minorius.news.module.activity_module.fragment.NewsFragment;
import com.minorius.news.module.activity_module.fragment.adapter.NewsAdapter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Minorius on 19.12.2017.
 */

@Module
public class FragmentModule {

    @Provides
    @PerFragment
    LinearLayoutManager provideLayoutManager(NewsFragment newsFragment){
        return new LinearLayoutManager(newsFragment.getContext(), LinearLayoutManager.VERTICAL, false);
    }

    @Provides
    @PerFragment
    NewsAdapter provideAdapter (){
        return new NewsAdapter();
    }
}
