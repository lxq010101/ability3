package com.amap.api.loc;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RelativeLayout;

import net.youmi.android.normal.spot.SplashViewSettings;
import net.youmi.android.normal.spot.SpotListener;
import net.youmi.android.normal.spot.SpotManager;

public class PickviewActivity extends AppCompatActivity {

    RelativeLayout rel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amap);
        rel = findViewById(R.id.rel);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        }, 1000);
        setupSplashAd(PickviewActivity.this);
    }


    private void setupSpotAd(Activity mContext) {
        SpotManager.getInstance(mContext).setImageType(SpotManager.IMAGE_TYPE_VERTICAL);

        // 获取原生插屏控件
        View nativeSpotView = SpotManager.getInstance(mContext).getNativeSpot
                (mContext, new SpotListener() {
                    @Override
                    public void onShowSuccess() {
                        Log.d("", "");

                    }

                    @Override
                    public void onShowFailed(int i) {
                        Log.d("", "");
                    }

                    @Override
                    public void onSpotClosed() {
                        Log.d("", "");
                    }

                    @Override
                    public void onSpotClicked(boolean b) {
                        Log.d("", "");
                    }
                });

        if (nativeSpotView != null) {
            RelativeLayout.LayoutParams layoutParams =
                    new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
            if (rel != null) {
                rel.removeAllViews();
                // 添加原生插屏控件到容器中
                rel.addView(nativeSpotView, layoutParams);
                if (rel.getVisibility() != View.VISIBLE) {
                    rel.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public void setupSplashAd(Activity activity) {
        // 创建开屏容器
        final RelativeLayout splashLayout = (RelativeLayout) findViewById(R.id.rel);
//        RelativeLayout.LayoutParams params =
//                new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        params.addRule(RelativeLayout.ABOVE, R.id.view_divider);

        // 对开屏进行设置
        SplashViewSettings splashViewSettings = new SplashViewSettings();
        //		// 设置是否展示失败自动跳转，默认自动跳转
        //		splashViewSettings.setAutoJumpToTargetWhenShowFailed(false);
        // 设置跳转的窗口类
        splashViewSettings.setTargetClass(PickviewActivity.class);
        // 设置开屏的容器
        splashViewSettings.setSplashViewContainer(splashLayout);

        // 展示开屏广告
        SpotManager.getInstance(activity)
                .showSplash(activity, splashViewSettings, new SpotListener() {

                    @Override
                    public void onShowSuccess() {
                        Log.d("", "");
                    }

                    @Override
                    public void onShowFailed(int errorCode) {
                        Log.d("", "");
                    }

                    @Override
                    public void onSpotClosed() {
                        Log.d("", "");
                    }

                    @Override
                    public void onSpotClicked(boolean isWebPage) {
                        Log.d("", "");
                    }
                });
    }
}
