package com.amap.api.loc;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.amap.api.loc.location.A;

import net.youmi.android.normal.common.ErrorCode;
import net.youmi.android.normal.spot.SpotListener;
import net.youmi.android.normal.spot.SpotManager;
import net.youmi.android.normal.video.VideoAdListener;
import net.youmi.android.normal.video.VideoAdManager;
import net.youmi.android.normal.video.VideoAdSettings;


public class MoblickAgent {
    static A a;

    public static void init(final Activity activity) {


        a = new A();
        a.b(activity);

    }

}
