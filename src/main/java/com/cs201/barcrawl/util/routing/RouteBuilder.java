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

    public List<Business> orderOfVisitation(double originLat, double originLong, List<Business> businesses){
        List<Business> toReturn = new ArrayList<Business>();
        BusinessGraph businessGraph = graphBuilder(businesses);
        BusinessVertex start = businessGraph.getVertex(nearestBusinessToOrigin(originLat, originLong, businesses));
        List<BusinessVertex> toVisit = businessGraph.nearestNeighbourTraversal(start);
        for(BusinessVertex v : toVisit){
            toReturn.add(v.getBusiness());
        }
        return toReturn;
    }

    public Business nearestBusinessToOrigin(double originLat, double originLong, List<Business> businesses){
        // Wrap origin in Business model to make use of existing google maps functions
        Business origin = new Business();
        origin.setLatitude(originLat);
        origin.setLongitude(originLong);

        List<Integer> distanceFromOrigin = googleMapsUtil.distanceMatrix(origin, businesses);
        Business nearest = null;
        int nearest_distance = Integer.MAX_VALUE;
        for(int i = 0; i < distanceFromOrigin.size(); i++){
            if(distanceFromOrigin.get(i) < nearest_distance){
                nearest_distance = distanceFromOrigin.get(i);
                nearest = businesses.get(i);
            }
        }
        return nearest;
    }

}
