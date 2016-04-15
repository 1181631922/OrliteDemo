package com.fanyafeng.orlitedemo.dao;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

/**
 * Created by 365rili on 16/4/15.
 */
public class Song extends DataSupport{
    //name 不能为空
    @Column(nullable = false)
    private String name;
    private int duration;
    //不创建该字段
    @Column(ignore = true)
    private String uselessField;
    //索引album的id
    private Album album;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getUselessField() {
        return uselessField;
    }

    public void setUselessField(String uselessField) {
        this.uselessField = uselessField;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    @Override
    public String toString() {
        return "Song{" +
                "name='" + name + '\'' +
                ", duration=" + duration +
                ", uselessField='" + uselessField + '\'' +
                ", album=" + album +
                '}';
    }
}
