package com.cs201.barcrawl.models;

public class SortedDTO {
    private int time;

    private Business[] destinations;

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Business[] getDestinations() {
        return destinations;
    }

    public void setDestinations(Business[] destinations) {
        this.destinations = destinations;
    }
}
