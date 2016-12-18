package com.example.yujin.myapplication;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by sonaj on 2016-12-17.
 */
public class MoimItemData {

    String Title;
    String Date;
    String Time;
    String Location;
    Drawable Lock;
    String Secret;
    String Pwd;


    public void setTitle(String title){
        Title = title;
    }

    public void setDate(String date){
        Date = date;
    }

    public void setTime(String time){
        Time = time;
    }

    public void setLocation(String location){
        Location = location;
    }

    public void setIcom(Drawable icon){
        Lock = icon;
    }

    public void setSecret(String secret){
        Secret = secret;
    }
    public void setPwd(String pwd){Pwd = pwd; }

    public String getTitle(){
        return this.Title;
    }

    public String getDate(){
        return this.Date;
    }

    public String getTime(){
        return this.Time;
    }

    public String getLocation(){
        return this.Location;
    }

    public Drawable  getLock(){
        return this.Lock;
    }
    public String getSecret(){
        return this.Secret;
    }
    public String getPwd(){
        return this.Pwd;
    }
}



