package com.fanyafeng.orlitedemo.activity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.fanyafeng.orlitedemo.BaseActivity;
import com.fanyafeng.orlitedemo.R;
import com.fanyafeng.orlitedemo.dao.Album;
import com.fanyafeng.orlitedemo.dao.Song;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;
import java.util.Random;

public class LitePalActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lite_pal);
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
        findViewById(R.id.btn_pal_insert_album).setOnClickListener(this);
        findViewById(R.id.btn_pal_insert_songlist).setOnClickListener(this);
        findViewById(R.id.btn_pal_update_one).setOnClickListener(this);
        findViewById(R.id.btn_pal_update_two).setOnClickListener(this);
        findViewById(R.id.btn_pal_update_three).setOnClickListener(this);
        findViewById(R.id.btn_pal_update_four).setOnClickListener(this);
        findViewById(R.id.btn_pal_update_five).setOnClickListener(this);
        findViewById(R.id.btn_pal_query_one).setOnClickListener(this);
        findViewById(R.id.btn_pal_query_all).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_pal_insert_album:
                insertAlbum();
                break;
            case R.id.btn_pal_insert_songlist:
                insertSongList();
                break;
            case R.id.btn_pal_update_one:
                updateOne();
                break;
            case R.id.btn_pal_update_two:
                updateTwo();
                break;
            case R.id.btn_pal_update_three:
                updateThree();
                break;
            case R.id.btn_pal_update_four:
                updateFour();
                break;
            case R.id.btn_pal_update_five:
                updateFive();
                break;
            case R.id.btn_pal_del_one:
                delOne();
                break;
            case R.id.btn_pal_del_all:
                delAll();
                break;
            case R.id.btn_pal_query_one:
                queryOne();
                break;
            case R.id.btn_pal_query_all:
                queryAll();
                break;
        }
    }

    /**
     * 单个查询
     */
    private void queryOne() {
        Song song = DataSupport.find(Song.class, 2);
        Toast.makeText(this, song.toString(), Toast.LENGTH_LONG).show();
    }

    /**
     * 批量或模糊查询,order为排序
     */
    private void queryAll() {
        List<Song> songList = DataSupport.where("name like ?", "song%").order("duration").find(Song.class);
        Toast.makeText(this, songList.toString(), Toast.LENGTH_LONG).show();
    }

    /**
     * 删除单个item
     * <p>
     * 删除的查找方式和更新方式一样,可以批量,可以单个
     * 可以精准,可以模糊
     */
    private void delOne() {
        DataSupport.delete(Song.class, 10);
    }

    /**
     * 批量删除
     */
    private void delAll() {
        DataSupport.deleteAll(Song.class, "duration = ?", "306");
    }

    /**
     * 更新方式1,更新单个item
     */
    private void updateOne() {
        Album albumToUpdate = DataSupport.find(Album.class, 1);
        albumToUpdate.setPrice(20.99f);
        albumToUpdate.save();
    }

    /**
     * 更新方式2,同1,根据id进行更新
     */
    private void updateTwo() {
        Album albumToUpdate = new Album();
        albumToUpdate.setPrice(20.99f);
        albumToUpdate.update(2);
    }

    /**
     * 更新方式3,根据某个字段的匹配值进行更新,完全匹配
     */
    private void updateThree() {
        Album albumToUpdate = new Album();
        albumToUpdate.setPrice(10.99f);
        albumToUpdate.updateAll("name = ?", "album");
    }

    /**
     * 更新方式4,模糊匹配,
     */
    private void updateFour() {
        Song songToUpdate = new Song();
        songToUpdate.setDuration(306);
        songToUpdate.updateAll("duration > ?", "305");
    }

    /**
     * 更新方式5,模糊匹配,字符类型需要加上%
     */
    private void updateFive() {
        Album albumToUpdate = new Album();
        albumToUpdate.setPrice(10.99f);
        albumToUpdate.updateAll("name like ?", "album%");
    }

    /**
     * 插入album,name相同的话不会重复插入
     * 需要手动去判断,虽然重复不会crash,但控制台会报错
     */
    private void insertAlbum() {
        for (int i = 0; i < 100; i++) {
            Album album = new Album();
            album.setName("album" + i);
            album.setPrice(10.99f);
//        album.setCover(getCoverImageBytes());
            album.save();
        }
    }

    /**
     * 插入songlist,对应album2,一对多
     */
    private void insertSongList() {
        Album album1 = new Album();
        album1.setName("album1");
        album1.setPrice(10.99f);
//        album.setCover(getCoverImageBytes());
        album1.save();
        for (int i = 0; i < 10; i++) {
            Song song = new Song();
            song.setName("song" + i);
            song.setDuration(300 + i);
            song.setAlbum(album1);
            song.save();
        }
    }
}
