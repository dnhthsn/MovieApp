package com.example.movieapp.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class NetworkChangeListener extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (Common.isConnectedToInternet(context)) {
            Toast.makeText(context, Const.Success.connected, Toast.LENGTH_SHORT).show();
        }
    }
}
