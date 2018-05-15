package com.example.developer.pushnotificationdemoapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FirstFragment extends Fragment {

    private static final String LOG_TAG = FirstFragment.class.getSimpleName();

    private BroadcastReceiver broadcastReceiver;

    public static FirstFragment newInstance() {
        FirstFragment firstFragment = new FirstFragment();
        return firstFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View firstFragmentView = inflater.inflate(R.layout.first_fragment, container, false);

        final TextView nickTextView = firstFragmentView.findViewById(R.id.nick);
        final TextView bodyTextView = firstFragmentView.findViewById(R.id.body);
        final TextView roomTextView = firstFragmentView.findViewById(R.id.room);
        TextView q = firstFragmentView.findViewById(R.id.q);
        q.setText("q");

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.e(LOG_TAG, "onReceive()");
                nickTextView.setText(intent.getStringExtra("Nick"));
                bodyTextView.setText(intent.getStringExtra("body"));
                roomTextView.setText(intent.getStringExtra("Room"));;
            }
        };

        LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(getActivity());
        broadcastManager.registerReceiver(broadcastReceiver, new IntentFilter("custom-event-name"));

        return firstFragmentView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(broadcastReceiver);
    }
}
