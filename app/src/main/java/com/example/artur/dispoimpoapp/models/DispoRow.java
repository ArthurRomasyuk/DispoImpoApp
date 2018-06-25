package com.example.artur.dispoimpoapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DispoRow {

    @SerializedName("Dispo")
    @Expose
    private Dispo dispo;

    /**
     * No args constructor for use in serialization
     *
     */
    public DispoRow() {
    }

    /**
     *
     * @param dispo
     */
    public DispoRow(Dispo dispo) {
        super();
        this.dispo = dispo;
    }

    public Dispo getDispo() {
        return dispo;
    }

    public void setDispo(Dispo dispo) {
        this.dispo = dispo;
    }

}