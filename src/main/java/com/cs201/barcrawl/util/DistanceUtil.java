package com.cs201.barcrawl.util;

import com.cs201.barcrawl.models.Business;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DistanceUtil {

    public Map<Business, Integer> distanceList(double originLat, double originLong, List<Business> destinations, Integer maxDist) {
        Map<Business, Integer> toReturn = new HashMap<>();

        for(Business business:destinations) {
            double destLat = business.getLatitude();
            double destLong = business.getLongitude();
            int distance = distanceInMeters(originLat, originLong, destLat, destLong);
            if (distance <= maxDist) {
                toReturn.put(business, distance);
            }
        }

        return toReturn;
    }

    public int distanceInMeters(double originLat, double originLong, double destLat, double destLong) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(destLat - originLat);
        double lonDistance = Math.toRadians(destLong - originLong);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(originLat)) * Math.cos(Math.toRadians(destLat))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        distance = Math.pow(distance, 2);

        return (int) Math.round(Math.sqrt(distance));
    }
}
