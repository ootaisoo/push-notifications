package com.example.developer.pushnotificationdemoapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    private BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView nickTextView = findViewById(R.id.nick);
        TextView bodyTextView = findViewById(R.id.body);
        TextView roomTextView = findViewById(R.id.room);

        Intent intent = getIntent();
        nickTextView.setText(intent.getStringExtra("Nick"));
        bodyTextView.setText(intent.getStringExtra("body"));
        roomTextView.setText(intent.getStringExtra("Room"));

//        broadcastReceiver = new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                Log.e(LOG_TAG, "onReceive()");
//                textView.setText(intent.getStringExtra("text"));
//            }
//        };
//
//        LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(this);
//        broadcastManager.registerReceiver(broadcastReceiver, new IntentFilter("custom-event-name"));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);
    }
}
