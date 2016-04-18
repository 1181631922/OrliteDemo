package com.fanyafeng.orlitedemo.recycleview.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.fanyafeng.orlitedemo.BaseActivity;
import com.fanyafeng.orlitedemo.R;
import com.fanyafeng.orlitedemo.dao.Album;
import com.fanyafeng.orlitedemo.dao.Song;
import com.fanyafeng.orlitedemo.recycleview.adapter.MyRecycleViewAdapter;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class MyRecycleViewActivity extends BaseActivity {
    private RecyclerView rv_myrecycleview;
    private List<String> stringList=new ArrayList<>();
    private MyRecycleViewAdapter myRecycleViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_recycle_view);
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
        getData();
        initView();
        initData();
    }

    private void getData() {
        List<Album> albumList = DataSupport.where("name like ?", "album%").find(Album.class);
        for (int i = 0; i < albumList.size(); i++) {
            stringList.add(albumList.get(i).getName());
        }
    }

    private void initView() {
        rv_myrecycleview = (RecyclerView) findViewById(R.id.rv_myrecycleview);
    }

    private void initData() {
        myRecycleViewAdapter = new MyRecycleViewAdapter(this, stringList);
        rv_myrecycleview.setAdapter(myRecycleViewAdapter);
        rv_myrecycleview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
    }


}
