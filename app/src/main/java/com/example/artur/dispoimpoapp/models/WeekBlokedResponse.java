package com.example.artur.dispoimpoapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeekBlokedResponse {
    @SerializedName("rows")
    @Expose
    private List<WeekBlockedRow> rows = null;

    public List<WeekBlockedRow> getRows() {
        return rows;
    }

    public void setRows(List<WeekBlockedRow> rows) {
        this.rows = rows;
    }
}
