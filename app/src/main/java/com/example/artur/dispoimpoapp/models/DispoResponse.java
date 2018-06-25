package com.example.artur.dispoimpoapp.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DispoResponse {

    @SerializedName("rows")
    @Expose
    private List<DispoRow> rows = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public DispoResponse() {
    }

    /**
     *
     * @param rows
     */
    public DispoResponse(List<DispoRow> rows) {
        super();
        this.rows = rows;
    }

    public List<DispoRow> getRows() {
        return rows;
    }

    public void setRows(List<DispoRow> rows) {
        this.rows = rows;
    }

}