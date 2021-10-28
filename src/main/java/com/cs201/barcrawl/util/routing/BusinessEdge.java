package com.cs201.barcrawl.util.routing;

import com.cs201.barcrawl.models.Business;

import java.util.List;

public class BusinessEdge {
    private BusinessVertex[] endpoints = new BusinessVertex[2] ;
    private int cost;

    public BusinessEdge(BusinessVertex v1, BusinessVertex v2, int cost){
        this.endpoints[0] = v1;
        this.endpoints[1] = v2;
        this.cost = cost;
    }

    public BusinessVertex[] getEndpoints() {
        return endpoints;
    }

    public void setEndpoints(BusinessVertex[] endpoints) {
        this.endpoints = endpoints;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public BusinessVertex opposite(BusinessVertex v) throws Exception{
        if(endpoints[0] == v){
            return endpoints[1];
        } else if(endpoints[1] == v){
            return endpoints[0];
        } else{
            throw new Exception("Vertex is not contained in this edge");
        }
    }
}
