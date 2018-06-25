package com.example.artur.dispoimpoapp.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShopRow {

    @SerializedName("Shop")
    @Expose
    private Shop shop;

    /**
     * No args constructor for use in serialization
     *
     */
    public ShopRow() {
    }

    /**
     *
     * @param shop
     */
    public ShopRow(Shop shop) {
        super();
        this.shop = shop;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    @Override
    public String toString() {
        return "ShopRow{" +
                "shop=" + shop +
                '}';
    }
}