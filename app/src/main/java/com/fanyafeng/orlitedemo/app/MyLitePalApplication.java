package com.fanyafeng.orlitedemo.app;

import android.content.Context;

import org.litepal.LitePalApplication;

/**
 * Created by 365rili on 16/4/15.
 */
public class MyLitePalApplication extends LitePalApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        LitePalApplication.initialize(this);
    }
}
