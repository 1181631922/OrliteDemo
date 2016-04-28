package com.fanyafeng.orlitedemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.fanyafeng.orlitedemo.BaseActivity;
import com.fanyafeng.orlitedemo.R;
import com.fanyafeng.orlitedemo.recycleview.activity.MyRecycleViewActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    }

    private void initData() {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_ormlite:

                break;
            case R.id.btn_litepal:
                startActivity(new Intent(this, LitePalActivity.class));
                break;
            case R.id.btn_recycle:
                startActivity(new Intent(this, MyRecycleViewActivity.class));
                break;
            case R.id.btn_blur:
                startActivity(new Intent(this, BlurActivity.class));
                break;
            case R.id.btn_percent_progressbar:
                startActivity(new Intent(this, ProgressBarActivity.class));
                break;
            case R.id.btn_circle_loading:
                startActivity(new Intent(this, CircleLoadingActivity.class));
                break;
            case R.id.btn_speed_view:
                startActivity(new Intent(this, SpeedCircleViewActivity.class));
                break;
            case R.id.btn_cloud_view:
                startActivity(new Intent(this, CloudActivity.class));
                break;
            case R.id.btn_fer_modes:
                startActivity(new Intent(this, PorterDuffActivity.class));
                break;
            case R.id.btn_popup_window:
                startActivity(new Intent(this, PopupWindowActivity.class));
                break;
            case R.id.btn_zhima:
                startActivity(new Intent(this, PolygonActivity.class));
                break;
            case R.id.btn_bezier:
                startActivity(new Intent(this, BezierActivity.class));
                break;
        }
    }


}
