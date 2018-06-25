package com.example.artur.dispoimpoapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Dispo {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("user")
    @Expose
    private String user;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("shop")
    @Expose
    private String shop;
    @SerializedName("range")
    @Expose
    private String range;
    @SerializedName("number_of_hours")
    @Expose
    private String numberOfHours;
    @SerializedName("statut")
    @Expose
    private String statut;

    /**
     * No args constructor for use in serialization
     *
     */
    public Dispo() {
    }

    /**
     *
     * @param id
     * @param shop
     * @param statut
     * @param range
     * @param numberOfHours
     * @param date
     * @param user
     */
    public Dispo(String id, String user, String date, String shop, String range, String numberOfHours, String statut) {
        super();
        this.id = id;
        this.user = user;
        this.date = date;
        this.shop = shop;
        this.range = range;
        this.numberOfHours = numberOfHours;
        this.statut = statut;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public String getNumberOfHours() {
        return numberOfHours;
    }

    public void setNumberOfHours(String numberOfHours) {
        this.numberOfHours = numberOfHours;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

}