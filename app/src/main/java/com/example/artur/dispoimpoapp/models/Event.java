package com.example.artur.dispoimpoapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Event {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("event_date")
    @Expose
    private String eventDate;
    @SerializedName("event_time")
    @Expose
    private String eventTime;
    @SerializedName("site_id")
    @Expose
    private String siteId;
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("notes")
    @Expose
    private String notes;
    @SerializedName("shop_id")
    @Expose
    private String shopId;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("modified")
    @Expose
    private String modified;

    /**
     * No args constructor for use in serialization
     *
     */
    public Event() {
    }

    /**
     *
     * @param id
     * @param eventDate
     * @param siteId
     * @param created
     * @param color
     * @param shopId
     * @param name
     * @param eventTime
     * @param notes
     * @param modified
     */
    public Event(String id, String name, String eventDate, String eventTime, String siteId, String color, String notes, String shopId, String created, String modified) {
        super();
        this.id = id;
        this.name = name;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.siteId = siteId;
        this.color = color;
        this.notes = notes;
        this.shopId = shopId;
        this.created = created;
        this.modified = modified;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }


    @Override
    public String toString() {
        return "Event{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", eventDate='" + eventDate + '\'' +
                ", eventTime='" + eventTime + '\'' +
                ", siteId='" + siteId + '\'' +
                ", color='" + color + '\'' +
                ", notes='" + notes + '\'' +
                ", shopId='" + shopId + '\'' +
                ", created='" + created + '\'' +
                ", modified='" + modified + '\'' +
                '}';
    }
}