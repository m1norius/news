package com.minorius.news.di.component;

import com.minorius.news.App;
import com.minorius.news.di.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by Minorius on 19.12.2017.
 */

@Component(modules = {
        AppModule.class,
        AndroidSupportInjectionModule.class
})
@Singleton
public interface AppComponent extends AndroidInjector<App> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<App> {}
}