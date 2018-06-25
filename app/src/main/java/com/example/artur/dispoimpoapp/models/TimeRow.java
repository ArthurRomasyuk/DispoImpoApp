package com.example.artur.dispoimpoapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TimeRow {

    @SerializedName("Time")
    @Expose
    private Time time;

    /**
     * No args constructor for use in serialization
     *
     */
    public TimeRow() {
    }

    /**
     *
     * @param time
     */
    public TimeRow(Time time) {
        super();
        this.time = time;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

}