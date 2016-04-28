package com.fanyafeng.orlitedemo.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.fanyafeng.orlitedemo.BaseActivity;
import com.fanyafeng.orlitedemo.R;
import com.fanyafeng.orlitedemo.myview.MagicCircle;

public class BezierActivity extends BaseActivity {
    private MagicCircle view_bezier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bezier);
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
        view_bezier = (MagicCircle) findViewById(R.id.view_bezier);
        view_bezier.startAnimation();
    }

    private void initData() {

    }

}
