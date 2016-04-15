package com.fanyafeng.orlitedemo.dao;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 365rili on 16/4/15.
 */
public class Album extends DataSupport{
    //唯一并且默认为unknown
    @Column(unique = true,defaultValue = "unknown")
    private String name;
    private float price;
    private byte[] cover;
    private List<Song> songList = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public byte[] getCover() {
        return cover;
    }

    public void setCover(byte[] cover) {
        this.cover = cover;
    }

    public List<Song> getSongList() {
        return songList;
    }

    public void setSongList(List<Song> songList) {
        this.songList = songList;
    }

    @Override
    public String toString() {
        return "Album{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", cover=" + Arrays.toString(cover) +
                ", songList=" + songList +
                '}';
    }
}
