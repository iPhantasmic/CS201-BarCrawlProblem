package com.cs201.barcrawl.models;

public class SortedDTO {
    private long time;

    private Business[] destinations;

    public  long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public Business[] getDestinations() {
        return destinations;
    }

    public void setDestinations(Business[] destinations) {
        this.destinations = destinations;
    }
}
