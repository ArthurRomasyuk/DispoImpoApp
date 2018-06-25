package com.example.artur.dispoimpoapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Time {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("timeframe")
    @Expose
    private String timeframe;
    @SerializedName("hours")
    @Expose
    private String hours;

    /**
     * No args constructor for use in serialization
     *
     */
    public Time() {
    }

    /**
     *
     * @param id
     * @param hours
     * @param name
     * @param timeframe
     */
    public Time(String id, String name, String timeframe, String hours) {
        super();
        this.id = id;
        this.name = name;
        this.timeframe = timeframe;
        this.hours = hours;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimeframe() {
        return timeframe;
    }

    public void setTimeframe(String timeframe) {
        this.timeframe = timeframe;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

}