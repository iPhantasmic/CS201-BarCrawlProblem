package com.cs201.barcrawl.util.routing;

import com.cs201.barcrawl.models.Business;
import com.cs201.barcrawl.util.GoogleMapsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RouteBuilder {
    @Autowired
    private GoogleMapsUtil googleMapsUtil;

    public BusinessGraph graphBuilder(List<Business> businesses){
        BusinessGraph businessGraph = new BusinessGraph();
        List<Business> businessCopy = new ArrayList<>(businesses);
        while(businessCopy.size() > 1){
            Business origin = businessCopy.remove(0);
            List<Integer> edgeDistances = googleMapsUtil.distanceMatrix(origin, businessCopy);
            for(int i = 0; i < businessCopy.size(); i++){
                Business destination = businessCopy.get(i);
                int cost = edgeDistances.get(i);
                businessGraph.addEdge(origin, destination, cost);
            }
        }
        return businessGraph;
    }

    public List<Business> orderOfVisitation(List<Business> businesses){
        List<Business> toReturn = new ArrayList<Business>();
        BusinessGraph businessGraph = graphBuilder(businesses);
        BusinessVertex start = businessGraph.getVertex(businesses.get(0));
        List<BusinessVertex> toVisit = businessGraph.nearestNeighbourTraversal(start);
        for(BusinessVertex v : toVisit){
            toReturn.add(v.getBusiness());
        }
        return toReturn;
    }

}
