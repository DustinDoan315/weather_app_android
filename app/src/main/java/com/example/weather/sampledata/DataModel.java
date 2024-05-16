package com.example.weather.sampledata;

import android.util.Log;

public class DataModel {
    private String title;
    private String desc;

    public DataModel(String title, String desc) {
        Log.d("LOG", "DataModel: "+ title+ desc);

        this.title = title;
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }
}