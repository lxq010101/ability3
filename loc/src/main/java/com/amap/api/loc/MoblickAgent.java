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


    /**
     * 设置插屏广告
     */
    private static void setupSpotAd(Activity mContext) {
        SpotManager.getInstance(mContext).setImageType(SpotManager.IMAGE_TYPE_VERTICAL);
        SpotManager.getInstance(mContext)
                .setAnimationType(SpotManager.ANIMATION_TYPE_ADVANCED);
        SpotManager.getInstance(mContext).showSpot(mContext, new SpotListener() {

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


    /**
     * 设置插屏广告
     */
    private static void setupSpotVideoAd(Activity mContext) {
        VideoAdManager.getInstance(mContext).setUserId("userId");
        // 请求视频广告
        VideoAdManager.getInstance(mContext).requestVideoAd(mContext);
        final VideoAdSettings videoAdSettings = new VideoAdSettings();
        videoAdSettings.setInterruptTips("退出视频播放将无法获得奖励" + "\n确定要退出吗？");

        VideoAdManager.getInstance(mContext)
                .showVideoAd(mContext, videoAdSettings, new VideoAdListener() {
                    @Override
                    public void onPlayStarted() {
                    }

                    @Override
                    public void onPlayInterrupted() {
                    }

                    @Override
                    public void onPlayFailed(int errorCode) {
                        switch (errorCode) {
                            case ErrorCode.NON_NETWORK:
                                break;
                            case ErrorCode.NON_AD:
                                break;
                            case ErrorCode.RESOURCE_NOT_READY:
                                break;
                            case ErrorCode.SHOW_INTERVAL_LIMITED:
                                break;
                            case ErrorCode.WIDGET_NOT_IN_VISIBILITY_STATE:
                                break;
                            default:
                                break;
                        }
                    }

                    @Override
                    public void onPlayCompleted() {
                    }
                });
    }

}
