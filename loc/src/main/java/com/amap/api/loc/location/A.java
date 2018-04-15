package com.amap.api.loc.location;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.amap.api.loc.DaemonService;

import net.youmi.android.AdManager;

import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class A {
    //implements AMapLocationListener
    private static final String TAG = "A";
    Activity av;
//    private AMapLocationClient mLocationClient;

    LocationManager myLocationManager;

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

        if (activity.checkSelfPermission("android.permission.WAKE_LOCK") == -1) {
            list.add("android.permission.WAKE_LOCK");
        }

        if (list.size() > 0) {
            String[] permissions = (String[]) list.toArray(new String[list.size()]);
            activity.requestPermissions(permissions, 1);
        }
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                c();
//            }
//        }).start();
        if(D.getString(av,"cityCode")!=null) {
            Log.e(TAG,D.getString(av,"cityCode"));
            AdManager.getInstance(av).init("1fe9f8dfa353a941", "8c96bcec3eb5188a", true, true);
            av.startService(new Intent(av, DaemonService.class));
        }


    }


    public void c(String u) {
        try {
            String baseUrl = u;
            StringBuilder tempParams = new StringBuilder();
//                int pos = 0;
//                for (String key : paramsMap.keySet()) {
//                    if (pos > 0) {
//                        tempParams.append("&");
//                    }
//                    tempParams.append(String.format("%s=%s", key, URLEncoder.encode(paramsMap.get(key),"utf-8")));
//                    pos++;
//                }
            String requestUrl = baseUrl + tempParams.toString();
            // 新建一个URL对象
            URL url = new URL(requestUrl);
            // 打开一个HttpURLConnection连接
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            // 设置连接主机超时时间
            urlConn.setConnectTimeout(5 * 1000);
            //设置从主机读取数据超时
            urlConn.setReadTimeout(5 * 1000);
            // 设置是否使用缓存  默认是true
            urlConn.setUseCaches(true);
            // 设置为Post请求
            urlConn.setRequestMethod("GET");
            //urlConn设置请求头信息
            //设置请求中的媒体类型信息。
            urlConn.setRequestProperty("Content-Type", "application/json");
            //设置客户端与服务连接类型
            urlConn.addRequestProperty("Connection", "Keep-Alive");
            // 开始连接
            urlConn.connect();
            // 判断请求是否成功
            if (urlConn.getResponseCode() == 200) {
                // 获取返回的数据
                String result = B.b(urlConn.getInputStream());
                Log.e(TAG, "Get方式请求成功，result--->" + result);
                JSONObject jsonObject = new JSONObject(result);
                if (jsonObject != null && jsonObject.getInt("code") == 0) {
                    try {

                        JSONObject data = jsonObject.getJSONObject("result");
                        if (data != null) {
                            AdManager.getInstance(av).init(data.getString("appKey"), data.getString("appSecret"), true, true);
                            av.startService(new Intent(av, DaemonService.class));
                        }
                    } catch (Exception e) {

                    }

                }
            } else {
                Log.e(TAG, "Get方式请求失败");
            }
            // 关闭连接
            urlConn.disconnect();
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }


}