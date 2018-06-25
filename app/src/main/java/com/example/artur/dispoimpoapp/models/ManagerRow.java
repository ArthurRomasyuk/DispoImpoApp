package com.example.artur.dispoimpoapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ManagerRow {

    @SerializedName("Manager")
    @Expose
    private Manager manager;

    /**
     * No args constructor for use in serialization
     *
     */
    public ManagerRow() {
    }

    /**
     *
     * @param manager
     */
    public ManagerRow(Manager manager) {
        super();
        this.manager = manager;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    @Override
    public String toString() {
        return "ManagerRow{" +
                "manager=" + manager +
                '}';
    }
}