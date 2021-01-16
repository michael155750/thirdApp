package com.example.thirdapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().matches("com.javacodegeeks.android.A_CUSTOM_INTENT"))
            Toast.makeText(context, "A_CUSTOM_INTENT", Toast.LENGTH_LONG).show();
    }
}
