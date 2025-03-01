package com.gastronomiepizza.model;

public class SellPoint {
    protected String name;
    private String id;
    private String location;
    private int treasury;

    public SellPoint(String id, String location, String name, int treasury) {
        this.id = id;
        this.location = location;
        this.name = name;
        this.treasury = treasury;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTreasury() {
        return treasury;
    }

    public void setTreasury(int treasury) {
        this.treasury = treasury;
    }
}
