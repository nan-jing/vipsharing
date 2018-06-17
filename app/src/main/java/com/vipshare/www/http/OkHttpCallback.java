package com.vipshare.www.http;

import org.json.JSONObject;

import java.io.IOException;

public interface OkHttpCallback {
    /**
     * 响应成功
     */
    void onSuccess(JSONObject oriData);


    /**
     * 响应失败
     */
    void onFailure(IOException e);
}
