package com.minorius.news.notifications;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Minorius on 27.12.2017.
 */

public class NewsServiceInstanceIDService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        FirebaseInstanceId.getInstance().getToken();
    }
}
