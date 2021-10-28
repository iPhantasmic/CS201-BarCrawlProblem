package com.cs201.barcrawl.util.routing;

import com.cs201.barcrawl.models.Business;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class BusinessVertex {
    private Business business;
    private HashMap<BusinessVertex, BusinessEdge> edges = new HashMap<BusinessVertex, BusinessEdge>();

    public BusinessVertex(Business business){
        this.business = business;
    }

    public Business getBusiness() {
        return business;
    }

    public void addEdge(BusinessVertex vertex, BusinessEdge edge){
        edges.put(vertex, edge);
    }

    public HashMap<BusinessVertex, BusinessEdge> getEdges(){
        return this.edges;
    }

    public ArrayList<BusinessVertex> orderByDistance(){
        ArrayList<BusinessEdge> orderedEdges = new ArrayList<BusinessEdge>(edges.values());
        orderedEdges.sort(new EdgeDistanceComparator());
        ArrayList<BusinessVertex> orderedVertices = new ArrayList<>();
        for(BusinessEdge edge: orderedEdges){
            try {
                orderedVertices.add(edge.opposite(this));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return orderedVertices;
    }

    class EdgeDistanceComparator implements Comparator<BusinessEdge> {
        public int compare(BusinessEdge edge1, BusinessEdge edge2){
            if(edge1.getCost() < edge2.getCost()){
                return -1;
            } else{
                return 1;
            }
        }
    }
}
