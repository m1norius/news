package com.minorius.news.module.activity_module;

import com.minorius.news.App;
import com.minorius.news.R;

/**
 * Created by Minorius on 30.12.2017.
 */

class NewsNavigation {

    static final String SMILA = "smila";
    static final String CHERKASSY = "cherkassy";
    static final String UKRAINE = "ukraine";
    static final String WORLD = "world";

    static String getRegionById(int id){

        switch (id){
            case R.id.id_down_item_smila :
                return SMILA;
            case R.id.id_down_item_cherkassy :
                return CHERKASSY;
            case R.id.id_down_item_ukr :
                return UKRAINE;
            case R.id.id_down_item_world :
                return WORLD;
        }

        App.myLog("id chosen region not found");
        return "id not found";
    }

    static String getCheckedRegion(int id){
        switch (id){
            case R.id.id_check_box_smila_sub :
                return  SMILA;
            case R.id.id_check_box_cherkassy_sub :
                return  CHERKASSY;
            case R.id.id_check_box_ukraine_sub :
                return  UKRAINE;
            case R.id.id_check_box_world_sub :
                return  WORLD;
        }

        App.myLog("id checked region not found");
        return "id not found";
    }

}
