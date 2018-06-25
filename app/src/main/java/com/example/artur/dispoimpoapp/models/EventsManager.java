package com.example.artur.dispoimpoapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EventsManager {

    @SerializedName("manager_id")
    @Expose
    private String managerId;

    /**
     * No args constructor for use in serialization
     *
     */
    public EventsManager() {
    }

    /**
     *
     * @param managerId
     */
    public EventsManager(String managerId) {
        super();
        this.managerId = managerId;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

}