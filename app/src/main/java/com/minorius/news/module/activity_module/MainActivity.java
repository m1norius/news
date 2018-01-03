package com.minorius.news.module.activity_module;

import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.google.firebase.messaging.FirebaseMessaging;
import com.minorius.news.App;
import com.minorius.news.R;
import com.minorius.news.module.FragmentNavigator;
import com.minorius.news.module.activity_module.fragment.NewsFragment;
import com.minorius.news.module.activity_module.fragment.OnDataLoader;
import com.minorius.news.mvp.model.response.NewsResponse;
import com.minorius.news.mvp.presenter.NewsPresenter;
import com.minorius.news.mvp.view.MainView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;
import dagger.android.support.DaggerAppCompatActivity;
import retrofit2.Retrofit;


public class MainActivity extends DaggerAppCompatActivity implements MainView {

    @BindView(R.id.id_container)            FrameLayout frameLayout;

    @BindView(R.id.id_down_item_smila)      Button smilaItem;
    @BindView(R.id.id_down_item_cherkassy)  Button cherkassyItem;
    @BindView(R.id.id_down_item_ukr)        Button ukraineItem;
    @BindView(R.id.id_down_item_world)      Button worldItem;

    @BindView(R.id.id_button_group)         LinearLayout buttonGroup;

    @BindView(R.id.id_check_box_smila_sub)      AppCompatCheckBox checkBoxSmila;
    @BindView(R.id.id_check_box_cherkassy_sub)  AppCompatCheckBox checkBoxCherkassy;
    @BindView(R.id.id_check_box_ukraine_sub)    AppCompatCheckBox checkBoxUkraine;
    @BindView(R.id.id_check_box_world_sub)      AppCompatCheckBox checkBoxWorld;

    @Inject NewsFragment        newsFragment;
    @Inject NewsPresenter       newsPresenter;
    @Inject SharedPreferences   sharedPreferences;

    @Inject Retrofit retrofit;

    private Communicator                communicator;
    private boolean                     isRegionChosen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setupCheckBox();

        FragmentNavigator fragmentNavigator = new FragmentNavigator(frameLayout.getId(), getSupportFragmentManager());
        fragmentNavigator.addFragment(newsFragment, false);

        newsFragment.setDataLoader(new OnDataLoader() {
            @Override
            public void onLoadMore(NewsResponse newsResponse) {
                if (isRegionChosen){
                    newsPresenter.getNewsByRegion(newsResponse.getRegion(), newsResponse.getDate());
                }else {
                    newsPresenter.getNews(newsResponse.getDate());
                }

            }
        });

        newsPresenter.getNews("");

    }

    @OnClick({R.id.id_down_item_smila, R.id.id_down_item_cherkassy, R.id.id_down_item_ukr, R.id.id_down_item_world})
    public void onClick(View view){

        setBackgroundButtonByClick(view);

        isRegionChosen = true;

        newsPresenter.unsubscribe();
        getCommunicatorWithFragment().clearList();
        getCommunicatorWithFragment().notifyAdapter();

        String region = NewsNavigation.getRegionById(view.getId());
        newsPresenter.getNewsByRegion(region, "");

    }

    @Override
    public void onDataLoaded(List response) {
        if (getCommunicatorWithFragment() != null) {
            getCommunicatorWithFragment().addToRecycler(response);
            getCommunicatorWithFragment().notifyAdapter();
        }
    }

    private void setupCheckBox(){
        checkBoxSmila.setChecked(sharedPreferences.getBoolean(NewsNavigation.SMILA, false));
        checkBoxCherkassy.setChecked(sharedPreferences.getBoolean(NewsNavigation.CHERKASSY, false));
        checkBoxUkraine.setChecked(sharedPreferences.getBoolean(NewsNavigation.UKRAINE, false));
        checkBoxWorld.setChecked(sharedPreferences.getBoolean(NewsNavigation.WORLD, false));
    }


    @OnClick({R.id.id_check_box_smila_sub, R.id.id_check_box_cherkassy_sub, R.id.id_check_box_ukraine_sub, R.id.id_check_box_world_sub})
    public void onClickBox(CheckBox checkBox){
        String checkedRegion = NewsNavigation.getCheckedRegion(checkBox.getId());
        subscribeNotification(checkBox.isChecked(), checkedRegion);
    }

    private void subscribeNotification(boolean isChecked, String topic){
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if (isChecked){
            FirebaseMessaging.getInstance().subscribeToTopic(topic);
        }else {
            FirebaseMessaging.getInstance().unsubscribeFromTopic(topic);
        }

        editor.putBoolean(topic, isChecked);
        editor.apply();
    }

    private void setBackgroundButtonByClick(View view){

        LinearLayout radioGroup =((LinearLayout)view.getParent());
        int id = view.getId();

        for (int j = 0; j < radioGroup.getChildCount(); j++) {
            Button childView = (Button) radioGroup.getChildAt(j);
            if (childView.getId() != id){
                childView.getBackground().clearColorFilter();
            }else {
                childView.getBackground().setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.clickedButton), PorterDuff.Mode.MULTIPLY);
            }
        }
    }

    //GS

    public Communicator getCommunicatorWithFragment() {
        return communicator;
    }

    public void setCommunicator(Communicator communicator) {
        this.communicator = communicator;
    }

}
