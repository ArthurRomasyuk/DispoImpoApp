package com.example.artur.dispoimpoapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WeekBlockedRow {

    @SerializedName("WeekBlocked")
    @Expose
    private WeekBlocked weekBlocked;

    public WeekBlocked getWeekBlocked() {
        return weekBlocked;
    }

    public void setWeekBlocked(WeekBlocked weekBlocked) {
        this.weekBlocked = weekBlocked;
    }
}
