package com.example.zhaoting.getapkandshare;

import android.graphics.drawable.Drawable;

/**
 * Created by zhaoting on 16/6/28.
 */
public class Bean {
    private String name;
    private long time;
    private int size;
    private Drawable icon;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }
}
