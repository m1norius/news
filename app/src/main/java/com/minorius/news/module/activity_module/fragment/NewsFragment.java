package com.minorius.news.module.activity_module.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.minorius.news.R;
import com.minorius.news.module.activity_module.Communicator;
import com.minorius.news.module.activity_module.MainActivity;
import com.minorius.news.module.activity_module.fragment.adapter.NewsAdapter;
import com.minorius.news.mvp.model.response.NewsResponse;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;
import dagger.android.support.DaggerFragment;

/**
 * Created by Minorius on 19.12.2017.
 */

public class NewsFragment extends DaggerFragment {

    @BindView(R.id.id_recycler) RecyclerView recyclerView;

    @Inject LinearLayoutManager layoutManager;
    @Inject NewsAdapter         adapter;

    private MainActivity activity;
    private OnDataLoader dataLoader;

    private boolean continueLoading = true;
    private int     visibleTrashHold = 5;
    private int     lastVisibleItem;
    private int     totalItemCount;

    public static NewsFragment getInstance() {
        return new NewsFragment();
    }

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
        activity = ((MainActivity) getActivity());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                totalItemCount  = layoutManager.getItemCount();
                lastVisibleItem = layoutManager.findLastVisibleItemPosition();

                if (continueLoading && totalItemCount <= (lastVisibleItem + visibleTrashHold)) {

                    NewsResponse lastItem = adapter.getLastItem();

                    if (lastItem != null) {
                        dataLoader.onLoadMore(lastItem);
                    }

                    adapter.addProgressBarItem();
                    continueLoading = false;
                }
            }
        });

        activity.setCommunicator(new Communicator() {
            @Override
            public void addToRecycler(List response) {
                if (response.size() > 0) {
                    adapter.removeProgressBarItem();
                    adapter.addToList(response);
                    continueLoading = true;
                } else {
                    adapter.removeProgressBarItem();
                }
            }

            @Override
            public void clearList() {
                adapter.clear();
            }

            @Override
            public void notifyAdapter() {
                adapter.notifyDataSetChanged();
            }
        });

    }

    public void setDataLoader(OnDataLoader dataLoader) {
        this.dataLoader = dataLoader;
    }
    
}
