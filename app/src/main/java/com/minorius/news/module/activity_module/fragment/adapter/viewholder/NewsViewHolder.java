package com.minorius.news.module.activity_module.fragment.adapter.viewholder;

import android.app.ActivityOptions;
import android.content.Intent;
import android.view.ViewGroup;
import android.widget.TextView;

import com.minorius.news.App;
import com.minorius.news.R;
import com.minorius.news.module.description_module.DescriptionActivity;
import com.minorius.news.module.activity_module.MainActivity;
import com.minorius.news.mvp.model.response.NewsResponse;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Minorius on 22.12.2017.
 */

public class NewsViewHolder extends BaseViewHolder<NewsResponse> {

    @BindView(R.id.id_txt_item_title)   TextView title;
    @BindView(R.id.id_txt_item_region)  TextView region;
    @BindView(R.id.id_txt_item_date)    TextView date;

    private NewsResponse newsResponse;

    public NewsViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_news);
    }

    @Override
    protected void bindData(NewsResponse data) {
        this.newsResponse = data;
        App.myLog(data.getImgUrl());
        title.setText(data.getTitle());
        region.setText(data.getRegion());
        date.setText(data.getDate());
    }

    @OnClick
    public void onClick(){
        showDescription();
    }

    private void showDescription(){
        Intent intent = new Intent(getContext(), DescriptionActivity.class);
        intent.putExtra("TITLE", newsResponse.getTitle());
        intent.putExtra("DESCRIPTION", newsResponse.getDescription());
        intent.putExtra("IMG", newsResponse.getImgUrl());

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {

            ActivityOptions options = ActivityOptions
                    .makeSceneTransitionAnimation(((MainActivity) getContext()), title, "newsTitle");

            getContext().startActivity(intent, options.toBundle());
        } else {
            getContext().startActivity(intent);
        }
    }
}
