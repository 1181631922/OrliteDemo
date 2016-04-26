package com.fanyafeng.orlitedemo.activity;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import com.fanyafeng.orlitedemo.BaseActivity;
import com.fanyafeng.orlitedemo.R;
import com.fanyafeng.orlitedemo.util.MyUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PopupWindowActivity extends BaseActivity {
    private Button btn_bottom_pop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_window);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        initView();
        initData();
    }

    private void initView() {
        btn_bottom_pop = (Button) findViewById(R.id.btn_bottom_pop);
    }

    private void initData() {

    }

    private void downShowPopupWindow(View view) {
        PopupWindow popupWindow;
        View popView = getLayoutInflater().inflate(R.layout.layout_popwindow_recyclerview, null);
        popupWindow = new PopupWindow(popView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
        popupWindow.showAsDropDown(view);
    }

    private void bottomShowPopupWindow(View view) {
        PopupWindow popupWindow;
        View popView = getLayoutInflater().inflate(R.layout.layout_popwindow_recyclerview, null);
        popupWindow = new PopupWindow(popView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
        float popupWindowWidth = MyUtils.dip2px(this, 96);
        Log.d("TAG", "pop的宽度:" + popupWindowWidth);
        int offsetX = MyUtils.getScreenWidth(this) / 2;
        int[] location = new int[2];
        btn_bottom_pop.getLocationInWindow(location);
        int popX = location[0];
        int popY = location[1];


        //相对位置,子点击的控件和margin开始定为原点(0,0)
        popupWindow.showAsDropDown(view, offsetX - (int) popupWindowWidth / 2,
                MyUtils.getScreenHeight(this) - MyUtils.getNavigationBarHeight(this) - (int) MyUtils.dip2px(this, 80) - popY);
    }

    private void bottomCenterShowPopupWindow(View view) {
        PopupWindow popupWindow;
        View popView = getLayoutInflater().inflate(R.layout.layout_popwindow_recyclerview, null);
        popupWindow = new PopupWindow(popView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, MyUtils.getScreenHeight(this) - MyUtils.getNavigationBarHeight(this));
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_down_pop:
                downShowPopupWindow(v);
                break;
            case R.id.btn_bottom_pop:
                bottomShowPopupWindow(v);
                break;
            case R.id.btn_bottom_center_pop:
                bottomCenterShowPopupWindow(v);
                break;
        }
    }


}
