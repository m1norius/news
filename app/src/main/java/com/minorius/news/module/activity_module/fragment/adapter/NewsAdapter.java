package com.minorius.news.module.activity_module.fragment.adapter;

import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.minorius.news.module.activity_module.fragment.adapter.viewholder.BaseViewHolder;
import com.minorius.news.module.activity_module.fragment.adapter.viewholder.NewsViewHolder;
import com.minorius.news.module.activity_module.fragment.adapter.viewholder.ProgressBarVewHolder;
import com.minorius.news.mvp.model.response.NewsResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Minorius on 22.12.2017.
 */

public class NewsAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private final int VIEW_TYPE_NEWS = 0;
    private final int VIEW_TYPE_LOADING = 1;

    private List<NewsResponse> list = new ArrayList<>();

    @Override
    public int getItemViewType(int position) {
        return list.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_NEWS;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_NEWS){
            return new NewsViewHolder(parent);
        }
        return new ProgressBarVewHolder(parent);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addToList(List<NewsResponse> list) {
        this.list.addAll(list);
    }

    public void clear(){
        this.list.clear();
    }

    public NewsResponse getLastItem(){
        NewsResponse response = null;

        int lastItemIndex = getItemCount() - 1;
        if (list.size() != 0){
            response = list.get(lastItemIndex);
        }
        return response;
    }





    public void removeProgressBarItem(){
        this.list.remove(null);
        backgroundNotify();
    }

    public void addProgressBarItem(){
        this.list.add(null);
        backgroundNotify();
    }

    private void backgroundNotify(){
        Handler handler = new Handler();

        final Runnable r = new Runnable() {
            public void run() {
                notifyDataSetChanged();
            }
        };

        handler.post(r);
    }

}
