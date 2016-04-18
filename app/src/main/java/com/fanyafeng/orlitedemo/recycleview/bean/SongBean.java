package com.fanyafeng.orlitedemo.recycleview.bean;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 365rili on 16/4/18.
 */
public class SongBean implements Parcelable {
    private String name;
    private int duration;

    public SongBean(String name, int duration) {
        this.name = name;
        this.duration = duration;
    }

    protected SongBean(Parcel in) {
        name = in.readString();
        duration = in.readInt();
    }

    public static final Creator<SongBean> CREATOR = new Creator<SongBean>() {
        @Override
        public SongBean createFromParcel(Parcel in) {
            return new SongBean(in);
        }

        @Override
        public SongBean[] newArray(int size) {
            return new SongBean[size];
        }
    };

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

    @Override
    public String toString() {
        return "SongBean{" +
                "name='" + name + '\'' +
                ", duration=" + duration +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(duration);
    }
}
