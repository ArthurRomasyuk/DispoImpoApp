package com.example.artur.dispoimpoapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WeekBlocked {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("week")
    @Expose
    private String week;
    @SerializedName("user")
    @Expose
    private String user;
    @SerializedName("statut")
    @Expose
    private String statut;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
}
