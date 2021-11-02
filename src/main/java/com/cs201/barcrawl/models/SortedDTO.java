package com.cs201.barcrawl.models;

public class SortedDTO {

    private long time;

    private long space;

    private Business[] destinations;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getSpace() { return space; }

    public void setSpace(long space) { this.space = space; }

    public Business[] getDestinations() {
        return destinations;
    }

    public void setDestinations(Business[] destinations) {
        this.destinations = destinations;
    }
}
