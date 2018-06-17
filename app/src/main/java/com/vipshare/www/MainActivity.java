package com.vipshare.www;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.vipshare.www.http.OkHttpManager;
import com.vipshare.www.utils.Config;

import java.util.HashMap;

public class MainActivity extends Activity {
    private final static String TAG = "xiaolong-VipShareWelcome";

    private Button mShare = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mShare = findViewById(R.id.needShare);
        mShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        OkHttpManager httpManager = OkHttpManager.getInstance(getApplicationContext());
                        HashMap<String,Object> map = new HashMap<String, Object>();
                        map.put("xiaolong","wo shi ");
                        String reponse = httpManager.getBySyn(Config.SERVER_ADDRESS2,map);
                        Log.e(TAG, "respose string:" + reponse);
                    }


                }.start();
            }
        });
    }

}
