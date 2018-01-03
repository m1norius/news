package com.minorius.news.di.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by Minorius on 19.12.2017.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivityMain {
}
