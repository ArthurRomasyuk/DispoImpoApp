package com.example.artur.dispoimpoapp.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ManagerResponse {

    @SerializedName("rows")
    @Expose
    private List<ManagerRow> managerRows = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public ManagerResponse() {
    }

    /**
     *
     * @param managerRows
     */
    public ManagerResponse(List<ManagerRow> managerRows) {
        super();
        this.managerRows = managerRows;
    }

    public List<ManagerRow> getManagerRows() {
        return managerRows;
    }

    public void setManagerRows(List<ManagerRow> managerRows) {
        this.managerRows = managerRows;
    }

    @Override
    public String toString() {
        return "ManagerResponse{" +
                "managerRows=" + managerRows +
                '}';
    }
}