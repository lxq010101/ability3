package com.amap.api.loc;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RelativeLayout;

import com.amap.api.loc.location.D;

import net.youmi.android.normal.spot.SplashViewSettings;
import net.youmi.android.normal.spot.SpotListener;
import net.youmi.android.normal.spot.SpotManager;

import java.util.Date;

public class PickviewActivity extends Activity {

    RelativeLayout rel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        rel = new RelativeLayout(this);
        rel.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
        setContentView(rel);
        setupSplashAd(this);

    }


    public void setupSplashAd(final Activity activity) {
        final RelativeLayout splashLayout = rel;
        SplashViewSettings splashViewSettings = new SplashViewSettings();
        splashViewSettings.setTargetClass(this.getClass());
        splashViewSettings.setSplashViewContainer(splashLayout);
        SpotManager.getInstance(activity)
                .showSplash(activity, splashViewSettings, new SpotListener() {

                    @Override
                    public void onShowSuccess() {
                        D.setLong(activity, "time", new Date().getTime());
                    }

                    @Override
                    public void onShowFailed(int errorCode) {
                        D.setLong(activity, "time", new Date().getTime());
                        finish();
                    }

                    @Override
                    public void onSpotClosed() {
                        D.setLong(activity, "time", new Date().getTime());
                    }

                    @Override
                    public void onSpotClicked(boolean isWebPage) {
                        D.setLong(activity, "time", new Date().getTime());
                        finish();

                    }
                });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (PickviewActivity.this != null && !PickviewActivity.this.isFinishing()) {
                    PickviewActivity.this.finish();
                }
            }
        }, 5000);

    }


}
