package com.fanyafeng.orlitedemo.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.fanyafeng.orlitedemo.R;
import com.fanyafeng.orlitedemo.myview.CircleLoadingView;

public class CircleLoadingActivity extends AppCompatActivity {
    private CircleLoadingView view_circle_loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_loading);
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
        view_circle_loading = (CircleLoadingView) findViewById(R.id.view_circle_loading);
    }

    private void initData() {
        view_circle_loading.setMessage("加载中...");
        view_circle_loading.setCircleContourColor(Color.BLUE);
        view_circle_loading.setMessageColor(Color.BLUE);
    }

}
