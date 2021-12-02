package com.example.wallpaperapp;

public class UrlModel {

    //for fetching any value from api exact same var name should be given
    private String portrait;

    public UrlModel(String portrait) {
        this.portrait = portrait;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }
}
