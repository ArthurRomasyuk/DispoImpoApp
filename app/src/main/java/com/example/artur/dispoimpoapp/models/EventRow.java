package com.example.artur.dispoimpoapp.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EventRow {

    @SerializedName("Event")
    @Expose
    private Event event;
    @SerializedName("Site")
    @Expose
    private Site site;
    @SerializedName("EventsManager")
    @Expose
    private EventsManager eventsManager;
    @SerializedName("EventsTime")
    @Expose
    private EventsTime eventsTime;
    @SerializedName("Manager")
    @Expose
    private List<Manager> manager = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public EventRow() {
    }

    /**
     *
     * @param eventsManager
     * @param site
     * @param manager
     * @param event
     * @param eventsTime
     */
    public EventRow(Event event, Site site, EventsManager eventsManager, EventsTime eventsTime, List<Manager> manager) {
        super();
        this.event = event;
        this.site = site;
        this.eventsManager = eventsManager;
        this.eventsTime = eventsTime;
        this.manager = manager;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public EventsManager getEventsManager() {
        return eventsManager;
    }

    public void setEventsManager(EventsManager eventsManager) {
        this.eventsManager = eventsManager;
    }

    public EventsTime getEventsTime() {
        return eventsTime;
    }

    public void setEventsTime(EventsTime eventsTime) {
        this.eventsTime = eventsTime;
    }

    public List<Manager> getManager() {
        return manager;
    }

    public void setManager(List<Manager> manager) {
        this.manager = manager;
    }

}