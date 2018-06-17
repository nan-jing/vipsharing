package com.vipshare.www;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import awty.enr.pweu.AdManager;
import awty.enr.pweu.nm.bn.BannerManager;
import awty.enr.pweu.nm.bn.BannerViewListener;
import awty.enr.pweu.nm.sp.SplashViewSettings;
import awty.enr.pweu.nm.sp.SpotListener;
import awty.enr.pweu.nm.sp.SpotManager;
import awty.enr.pweu.nm.sp.SpotRequestListener;

public class WelcomeActivity extends Activity {

    private static final String TAG = "WelcomeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SpotManager.getInstance(getApplicationContext()).requestSpot(new SpotRequestListener() {
            @Override
            public void onRequestSuccess() {
                Log.e(TAG, "SpotRequestListener-onRequestSuccess");
            }

            @Override
            public void onRequestFailed(int i) {
                Log.e(TAG, "SpotRequestListener-onRequestFailed");
            }
        });

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        /**标题是属于View的，所以窗口所有的修饰部分被隐藏后标题依然有效,需要去掉标题**/
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.welcome2);

//        handler.sendEmptyMessageDelayed(0,3000);
        SplashViewSettings splashViewSettings = new SplashViewSettings();
        splashViewSettings.setTargetClass(MainActivity.class);
        splashViewSettings.setAutoJumpToTargetWhenShowFailed(true);
        AdManager.getInstance(getApplicationContext()).init("f9b62fa75c3e713d", "f873f4ea5d7553cd", true);
        SpotManager.getInstance(getApplicationContext())
                .setAnimationType(SpotManager.ANIMATION_TYPE_ADVANCED);


        splashViewSettings.setSplashViewContainer((LinearLayout) findViewById(R.id.adlayout));
        SpotManager.getInstance(getApplicationContext()).showSplash(getApplicationContext(),
                splashViewSettings, new SpotListener() {
                    @Override
                    public void onShowSuccess() {
                        Log.e(TAG, "onShowSuccess");
               /*         Intent intent = new Intent();
                        intent.setClass(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                        finish();*/
                    }

                    @Override
                    public void onShowFailed(int i) {
                        Log.e(TAG, "onShowFailed");
                    }

                    @Override
                    public void onSpotClosed() {
                        Log.e(TAG, "onSpotClosed");
                        Intent intent = new Intent();
                        intent.setClass(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onSpotClicked(boolean b) {
                        Log.e(TAG, "onSpotClicked");
                    }
                });


    }
    @Override
    public void onBackPressed() {
        // 点击后退关闭插屏广告
        if (SpotManager.getInstance(getApplicationContext()).isSpotShowing()) {
            SpotManager.getInstance(getApplicationContext()).hideSpot();
            Log.e(TAG, "onBackPressed 准备跳转");
            Intent intent = new Intent();
            intent.setClass(this,MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            super.onBackPressed();
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        // 插屏广告
        SpotManager.getInstance(getApplicationContext()).onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        // 插屏广告
        SpotManager.getInstance(getApplicationContext()).onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SpotManager.getInstance(this).onDestroy();
        SpotManager.getInstance(this).onAppExit();
    }
}
