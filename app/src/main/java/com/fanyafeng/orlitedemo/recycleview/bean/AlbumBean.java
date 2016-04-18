package com.fanyafeng.orlitedemo.recycleview.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by 365rili on 16/4/18.
 */
public class AlbumBean implements Parcelable{

    private String name;
    private List<SongBean> songBeanList;

    public AlbumBean(String name) {
        this.name = name;
    }

    public AlbumBean(String name, List<SongBean> songBeanList) {
        this.name = name;
        this.songBeanList = songBeanList;
    }

    protected AlbumBean(Parcel in) {
        name = in.readString();
        songBeanList = in.createTypedArrayList(SongBean.CREATOR);
    }

    public static final Creator<AlbumBean> CREATOR = new Creator<AlbumBean>() {
        @Override
        public AlbumBean createFromParcel(Parcel in) {
            return new AlbumBean(in);
        }

        @Override
        public AlbumBean[] newArray(int size) {
            return new AlbumBean[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SongBean> getSongBeanList() {
        return songBeanList;
    }

    public void setSongBeanList(List<SongBean> songBeanList) {
        this.songBeanList = songBeanList;
    }

    @Override
    public String toString() {
        return "AlbumBean{" +
                "name='" + name + '\'' +
                ", songBeanList=" + songBeanList +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeTypedList(songBeanList);
    }
}
