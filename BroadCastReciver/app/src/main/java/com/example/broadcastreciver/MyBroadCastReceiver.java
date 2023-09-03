package com.example.broadcastreciver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyBroadCastReceiver extends BroadcastReceiver {
    private static final String TAG = "MyBroadcastReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {

        boolean airplaneModeEnabled = intent.getBooleanExtra("state", false);

        if (airplaneModeEnabled){
            Toast.makeText(context, "Airplane mode enabled", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(context, "Airplane mode disabled", Toast.LENGTH_LONG).show();
        }
    }
}
