package com.fanyafeng.orlitedemo.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.fanyafeng.orlitedemo.R;
import com.fanyafeng.orlitedemo.myview.PercentProgressBarView;

import org.w3c.dom.Text;

public class ProgressBarActivity extends AppCompatActivity {
    private PercentProgressBarView my_progress;
    private Handler handler;
    private int count = 0;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar);
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
        my_progress = (PercentProgressBarView) findViewById(R.id.my_progress);
        my_progress.setInitText("开始下载");
        my_progress.setTextPaintColor(Color.BLACK);
    }

    private void initData() {

        handler = new Handler(Looper.getMainLooper());
        runnable = new Runnable() {
            @Override
            public void run() {
                if (count < 100) {
                    count++;
                    my_progress.setPercent(count,count+"%");
                    handler.postDelayed(this,100);
                    Log.d("TAG","百分比:"+count);
//                    if (count==100){
//                        my_progress.setFinalText("下载完毕");
//                    }
                }
            }
        };
        handler.postDelayed(runnable, 1000);

    }

    @Override
    protected void onStop() {
        super.onStop();
        handler.removeCallbacks(runnable);
    }
}
