package com.minorius.news.module.description_module;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.minorius.news.App;
import com.minorius.news.R;
import com.minorius.news.mvp.model.response.NewsResponse;
import com.minorius.news.mvp.presenter.NewsPresenter;
import com.minorius.news.mvp.view.MainView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import dagger.android.support.DaggerAppCompatActivity;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by Minorius on 27.12.2017.
 */

public class DescriptionActivity extends DaggerAppCompatActivity implements MainView {

    @BindView(R.id.id_img_description)  ImageView imageView;

    @BindView(R.id.id_txt_title)        TextView titleTextView;
    @BindView(R.id.id_txt_description)  TextView descriptionTextView;

    @Inject NewsPresenter newsPresenter;

    private String imageUrl = App.baseUrl+"files/";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        ButterKnife.bind(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            titleTextView.setTransitionName("newsTitle");
        }

        String title        = getIntent().getStringExtra("TITLE");
        String description  = getIntent().getStringExtra("DESCRIPTION");
        String img          = getIntent().getStringExtra("IMG");

        String id           = getIntent().getStringExtra("id");

        if (id != null){
            newsPresenter.getNewsById(id);
        }else {
            titleTextView.setText(title);
            descriptionTextView.setText(description);
            setImgWithGlide(img);
        }
    }

    private void setImgWithGlide(String img){
        Glide.with(getApplicationContext())
                .load(imageUrl +img)
                .apply(RequestOptions.bitmapTransform(new RoundedCornersTransformation(0, 0)))
                .into(imageView);
    }

    @Override
    public void onDataLoaded(List response) {
        if (response.size() > 0){
            NewsResponse myResponse = (NewsResponse) response.get(0);
            titleTextView.setText(myResponse.getTitle());
            descriptionTextView.setText(myResponse.getDescription());
            setImgWithGlide(myResponse.getImgUrl());
        }
    }
}
