package com.example.artur.dispoimpoapp.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TimeResponse {

    @SerializedName("rows")
    @Expose
    private List<TimeRow> rows = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public TimeResponse() {
    }

    /**
     *
     * @param rows
     */
    public TimeResponse(List<TimeRow> rows) {
        super();
        this.rows = rows;
    }

    public List<TimeRow> getRows() {
        return rows;
    }

    public void setRows(List<TimeRow> rows) {
        this.rows = rows;
    }

}