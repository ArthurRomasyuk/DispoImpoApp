package com.example.artur.dispoimpoapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EventsTime {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("event_id")
    @Expose
    private String eventId;
    @SerializedName("time_id")
    @Expose
    private String timeId;
    @SerializedName("SB")
    @Expose
    private String sB;

    /**
     * No args constructor for use in serialization
     *
     */
    public EventsTime() {
    }

    /**
     *
     * @param id
     * @param eventId
     * @param timeId
     */
    public EventsTime(String id, String eventId, String timeId) {
        super();
        this.id = id;
        this.eventId = eventId;
        this.timeId = timeId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getTimeId() {
        return timeId;
    }

    public void setTimeId(String timeId) {
        this.timeId = timeId;
    }

    public String getSB() {
        return sB;
    }

    public void setSB(String sB) {
        this.sB = sB;
    }


}