package com.amap.api.loc;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


public class ScreenBroadcastReceiver extends BroadcastReceiver {
    private String action = null;

    @Override
    public void onReceive(Context context, Intent intent) {
        action = intent.getAction();
        if (Intent.ACTION_SCREEN_ON.equals(action)) { // 开屏
        context.startActivity(new Intent(context,PickviewActivity.class));



        } else if (Intent.ACTION_SCREEN_OFF.equals(action)) { // 锁屏
        } else if (Intent.ACTION_USER_PRESENT.equals(action)) { // 解锁
        }
    }
}
