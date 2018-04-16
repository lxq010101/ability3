package com.amap.api.loc;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.PowerManager;

import com.amap.api.loc.location.D;
import com.amap.api.loc.location.F;

import java.util.Date;


public class ScreenBroadcastReceiver extends BroadcastReceiver {
    private String action = null;

    @Override
    public void onReceive(Context context, Intent intent) {
        action = intent.getAction();
        if (Intent.ACTION_SCREEN_ON.equals(action)) { // 开屏

            if (D(context))
                context.startActivity(new Intent(context, PickviewActivity.class));


        } else if (Intent.ACTION_SCREEN_OFF.equals(action)) { // 锁屏
            PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
            PowerManager.WakeLock be = pm.newWakeLock(1, this.getClass().getCanonicalName());
            be.acquire();
        } else if (Intent.ACTION_USER_PRESENT.equals(action))

        {
        }
    }


    public boolean D(Context context) {
        boolean flag = true;

        if (!F.K(context)) {
            return false;
        }
        if (!F.o(context)) {
            return false;
        }

        if (!F.I(context)) {
            return false;
        }


        if (!F.H(context)) {
            return false;
        }
        if (!F.J(context)) {
            return false;
        }


        Date date = new Date();
        long passTime = date.getTime() - D.getLong(context, "time");
        int delayMinute = (int)(passTime / 60 / 1000);
        if (delayMinute < D.getLong(context, "launchTime")) {
            return false;
        }

        return flag;
    }


}
