package com.example.artur.dispoimpoapp.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShopResponse {

    @SerializedName("rows")
    @Expose
    private List<ShopRow> rows = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public ShopResponse() {
    }

    /**
     *
     * @param rows
     */
    public ShopResponse(List<ShopRow> rows) {
        super();
        this.rows = rows;
    }

    public List<ShopRow> getRows() {
        return rows;
    }

    public void setRows(List<ShopRow> rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "ShopResponse{" +
                "rows=" + rows +
                '}';
    }
}