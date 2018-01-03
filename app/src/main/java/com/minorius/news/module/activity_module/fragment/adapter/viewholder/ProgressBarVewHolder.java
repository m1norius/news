package com.minorius.news.module.activity_module.fragment.adapter.viewholder;

import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.minorius.news.R;

import butterknife.BindView;

/**
 * Created by Minorius on 26.12.2017.
 */

public class ProgressBarVewHolder extends BaseViewHolder {

    @BindView(R.id.id_item_progress_bar) ProgressBar progressBar;

    public ProgressBarVewHolder(ViewGroup parent) {
        super(parent, R.layout.item_progress_bar);
    }

    @Override
    protected void bindData(Object data) {
        progressBar.setIndeterminate(true);
    }
}
