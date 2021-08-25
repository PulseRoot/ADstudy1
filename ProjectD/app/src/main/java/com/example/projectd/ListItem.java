package com.example.projectd;

import android.widget.RatingBar;

import androidx.annotation.NonNull;

public class ListItem {
    String name;
    String talk;
    float starnum;
    int redId;

    public ListItem(String name, String talk, float starnum,  int redId){
        this.name = name;
        this.talk = talk;
        this.starnum = starnum;
        this.redId =redId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTalk() {
        return talk;
    }

    public void setTalk(String talk) {
        this.talk = talk;
    }

    public float getStarnum() { return starnum; }

    public void setStarnum(float starnum) {
        this.starnum = starnum;
    }

    public int getRedId() {
        return redId;
    }


    @Override
    public String toString() {
        return "Listitem{" +
                "name='" + name + '\'' +
                ", talk='" + talk + '\'' +
                ", star='" + starnum + '\'' +
                '}';
    }
}
