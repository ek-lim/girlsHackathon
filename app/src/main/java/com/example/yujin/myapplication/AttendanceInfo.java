package com.example.yujin.myapplication;

/**
 * Created by SSU on 2016-12-18.
 */
public class AttendanceInfo {
    private String name;
    private String time;

    public AttendanceInfo(String name, String time){
        this.name=name;
        this.time=time;
    }

    public String getName() {
        return name;
    }
}
