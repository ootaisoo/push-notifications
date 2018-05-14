package com.example.developer.pushnotificationdemoapp;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String LOG_TAG = MyFirebaseInstanceIDService.class.getSimpleName();

    public MyFirebaseInstanceIDService() {
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d(LOG_TAG, "token: " + token);
    }

    @Override
    public void onTokenRefresh() {
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d(LOG_TAG, "Refreshed token: " + token);
        sendRegistrationToServer(token);
    }


    private void sendRegistrationToServer(String token) {
        // TODO: Implement this method to send token to your app server.
    }
}
