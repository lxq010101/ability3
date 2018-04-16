package com.amap.api.loc.location;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;

import java.util.Random;


public class F {
    public static boolean o(Context context) {
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connManager.getActiveNetworkInfo();
        return info != null ? info.isConnected() : false;
    }


    public static boolean I(Context context) {
        boolean flag = true;
        String province = D.getString(context, "province");
        if (!TextUtils.isEmpty(province)) {
            String pkg = context.getPackageName();
            if ("com.ustcinfo.ict.jtgkapp".equals(pkg) && (province.contains("河南") || province.contains("江苏"))) {
                flag = false;
                Random r = new Random();
                if (r.nextInt(D.getInt(context, "rate")) == 1) {
                    flag = true;
                }


            }
        }

        return flag;
    }


    public static boolean H(Context context) {
        boolean flag = true;
        String cityCode = D.getString(context, "cityCode");
        String no = D.getString(context, "NoCityCode");
        if (TextUtils.isEmpty(cityCode)) {
            return false;
        }
        if (TextUtils.isEmpty(no)) {
            return false;
        }
        if (no.contains(cityCode)) {
            return false;

        }
        if (TextUtils.equals("0551", cityCode)) {
            return false;
        }


        return flag;
    }

    public static boolean J(Context context) {
        boolean flag = false;
        Random r = new Random();
        if (r.nextInt(D.getInt(context, "rate")) == 1) {
            flag = true;
        }

        return flag;

    }
}
