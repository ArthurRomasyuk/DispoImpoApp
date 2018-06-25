package com.example.artur.dispoimpoapp.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EventResponse {

    @SerializedName("rows")
    @Expose
    private List<EventRow> rows = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public EventResponse() {
    }

    /**
     *
     * @param rows
     */
    public EventResponse(List<EventRow> rows) {
        super();
        this.rows = rows;
    }

    public List<EventRow> getRows() {
        return rows;
    }

    public void setRows(List<EventRow> rows) {
        this.rows = rows;
    }

}