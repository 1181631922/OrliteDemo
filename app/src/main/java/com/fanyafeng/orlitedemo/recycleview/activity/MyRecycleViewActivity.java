package com.fanyafeng.orlitedemo.recycleview.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.fanyafeng.orlitedemo.BaseActivity;
import com.fanyafeng.orlitedemo.R;
import com.fanyafeng.orlitedemo.dao.Album;
import com.fanyafeng.orlitedemo.myview.NestedRecyclerView;
import com.fanyafeng.orlitedemo.recycleview.adapter.MyRecycleVIewAdapter;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class MyRecycleViewActivity extends BaseActivity {
    private NestedRecyclerView rv_myrecycleview;
    private List<String> stringList = new ArrayList<>();
    private MyRecycleVIewAdapter myRecycleViewAdapter;
    private SwipyRefreshLayout swipyrefreshlayout;
    private int count = 0;

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
        int remain = albumList.size() % 21;
        int myCount = (albumList.size() - remain) / 21;
        if (count > myCount) {
            Toast.makeText(this, "已经加载完毕", Toast.LENGTH_SHORT).show();
            return;
        } else {
            count += 1;
            for (int i = (count - 1) * 21; i < (count == (myCount + 1) ? (count - 1) * 21 + remain : count * 21); i++) {
                stringList.add(albumList.get(i).getName());

            }
        }
    }

    private void initView() {
        swipyrefreshlayout = (SwipyRefreshLayout) findViewById(R.id.swipyrefreshlayout);
        rv_myrecycleview = (NestedRecyclerView) findViewById(R.id.rv_myrecycleview);
    }

    private void initData() {
        myRecycleViewAdapter = new MyRecycleVIewAdapter(this, stringList);
        rv_myrecycleview.setAdapter(myRecycleViewAdapter);
        rv_myrecycleview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
//        rv_myrecycleview.setLayoutManager(new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false));

        swipyrefreshlayout.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction) {
                if (direction == SwipyRefreshLayoutDirection.TOP) {
                    stringList.clear();
                    count = 0;
                    getData();
                    myRecycleViewAdapter.notifyDataSetChanged();
                    swipyrefreshlayout.setRefreshing(false);
                } else if (direction == SwipyRefreshLayoutDirection.BOTTOM) {
                    getData();
                    myRecycleViewAdapter.notifyDataSetChanged();
                    swipyrefreshlayout.setRefreshing(false);
                }
            }
        });
        rv_myrecycleview.setOnScrollListener(new NestedRecyclerView.OnScrollListener() {
            @Override
            public void OnScroll(int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//                Log.d("nestrecycle","scrollX: " + scrollX + " scrollY: " + scrollY + " oldScrollX: " + oldScrollX + " oldScrollY: " + oldScrollY);
            }
        });
        rv_myrecycleview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
//                Log.d("nestrecycle","dx: " + dx + " dy: " + dy );
            }
        });
        rv_myrecycleview.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.d("nestrecycle", "distance: " + getScollYDistance());
            }
        });
    }

    public int getScollYDistance() {
        LinearLayoutManager layoutManager = (LinearLayoutManager) rv_myrecycleview.getLayoutManager();
        int position = layoutManager.findFirstVisibleItemPosition();
        View firstVisiableChildView = layoutManager.findViewByPosition(position);
        int itemHeight = firstVisiableChildView.getHeight();
        return (position) * itemHeight - firstVisiableChildView.getTop();
    }
}
