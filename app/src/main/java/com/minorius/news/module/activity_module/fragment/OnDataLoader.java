package com.minorius.news.module.activity_module.fragment;

import com.minorius.news.mvp.model.response.NewsResponse;

/**
 * Created by Minorius on 26.12.2017.
 */

public interface OnDataLoader {
    void onLoadMore(NewsResponse newsResponse);
}
