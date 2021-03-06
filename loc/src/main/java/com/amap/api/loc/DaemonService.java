package com.amap.api.loc;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.os.PowerManager;


public class DaemonService extends Service {

    private A a;
    private PowerManager pm;
    private PowerManager.WakeLock wakeLock;



    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        a = new A();
        pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "CPUKeepRunning");
        wakeLock.acquire();
        this.stopForeground(true);



        registerListener();
        return START_STICKY;

    }


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * 启动screen状态广播接收器
     */
    private void registerListener() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_USER_PRESENT);
        filter.setPriority(1000);
        getApplicationContext().registerReceiver(a, filter);
    }

}
