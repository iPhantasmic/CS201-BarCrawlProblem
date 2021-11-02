package com.cs201.barcrawl.controller;

import com.cs201.barcrawl.models.Business;
import com.cs201.barcrawl.service.BudgetService;
import com.cs201.barcrawl.models.SortedDTO;
import com.cs201.barcrawl.service.BusinessService;
import com.cs201.barcrawl.service.SortingService;
import com.cs201.barcrawl.util.DistanceUtil;
import com.cs201.barcrawl.util.GoogleMapsUtil;
import com.cs201.barcrawl.util.routing.RouteBuilder;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


@RestController
public class BusinessController {

    @Autowired
    BusinessService businessService;

    @Autowired
    GoogleMapsUtil googleMapsUtil;

    @Autowired
    DistanceUtil distanceUtil;

    @Autowired
    RouteBuilder routeBuilder;

    @Autowired
    SortingService sortingService;

    @Autowired
    BudgetService budgetService;

    @GetMapping(value = "/test")
    public @ResponseBody ResponseEntity<?> TestApi() {
//        return ResponseEntity.ok(businessService.findAll());
        return ResponseEntity.ok("testSubmissive");
    }

    @GetMapping(value = "/load")
    public @ResponseBody ResponseEntity<?> loadData() {
        boolean result = businessService.loadData();
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/business/{id}")
    public @ResponseBody ResponseEntity<?> getBusiness(@PathVariable int id) {
        try {
            return ResponseEntity.ok(businessService.getBusiness(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping(value = "/filter")
    public Map<Business, Integer> distanceMatrix(@RequestParam Double originLat, @RequestParam Double originLong,
                                                 @RequestParam Integer maxDist) { // maxDist is in metres
        List<Business> destinations = businessService.findAll();
        return distanceUtil.distanceList(originLat, originLong, destinations, maxDist);
    }

    @GetMapping(value = "/filter-sort/merge-sort")
    public SortedDTO filterAndMergeSort(@RequestParam Double originLat, @RequestParam Double originLong,
                                                 @RequestParam Integer maxDist) { // maxDist is in metres
        List<Business> destinations = filter(originLat, originLong, maxDist);

        SortedDTO result = sortingService.mergeSort(destinations);

        return result;
    }

    @GetMapping(value = "/filter-sort/insertion-sort")
    public SortedDTO filterAndInsertionSort(@RequestParam Double originLat, @RequestParam Double originLong,
                                        @RequestParam Integer maxDist) { // maxDist is in metres
        List<Business> destinations = filter(originLat, originLong, maxDist);

        SortedDTO result = sortingService.insertionSort(destinations);

        return result;
    }

    @GetMapping(value = "/filter-sort/quick-sort")
    public SortedDTO filterAndQuickSort(@RequestParam Double originLat, @RequestParam Double originLong,
                                            @RequestParam Integer maxDist) { // maxDist is in metres
        List<Business> destinations = filter(originLat, originLong, maxDist);

        SortedDTO result = sortingService.quickSort(destinations);

        return result;
    }

    @GetMapping(value = "/filter-sort/heap-sort")
    public SortedDTO filterAndHeapSort(@RequestParam Double originLat, @RequestParam Double originLong,
                                        @RequestParam Integer maxDist) { // maxDist is in metres
        List<Business> destinations = filter(originLat, originLong, maxDist);

        SortedDTO result = sortingService.heapSort(destinations);

        return result;
    }

    public List<Business> filter(Double originLat, Double originLong, Integer maxDist) {
        // Consider changing to set
        List<Business> destinations = businessService.findAll();

        for (Iterator<Business> iterator = destinations.iterator(); iterator.hasNext();) {
            Business next = iterator.next();

            int distance =  distanceUtil.distanceInMeters(
                    originLat,
                    originLong,
                    next.getLatitude(),
                    next.getLongitude()
            );

            // if the distance exceeds the required distance, we do not want to consider this destination
            if (distance > maxDist) {
                iterator.remove();
                continue;
            }

            // otherwise we set the distance for comparison later
            next.setDistance(distance);
        }

        return destinations;
    }

    @PostMapping("/budget")
    @ResponseBody
    public List<Business> budgetController(@RequestBody JsonObject body) {
        JsonArray businessesJA = body.getAsJsonArray("businesses");
        ArrayList<Business> businessesAL = new ArrayList<>();
        for(int i = 0; i < businessesJA.size(); i++) {
            String id = businessesJA.get(i).getAsString();
            businessesAL.add(businessService.getBusinessByYelpId(id));
        }
        Business[] resultWithNull = budgetService.getOptimizedBudget(businessesAL,
                (int) body.get("budget").getAsDouble());
        try {
            ArrayList<Business> finalResult = new ArrayList<>();
            for (int i = 0; i < resultWithNull.length; i++){
                if (resultWithNull[i] != null) {
                    finalResult.add(resultWithNull[i]);
                }
            }
            return finalResult.subList(1,finalResult.size()-1);
        } catch (IllegalArgumentException e) {
            //No business met the budget
            return new ArrayList<Business>();
        }

    }

    @GetMapping(value = "/route")
    public List<Business> route(@RequestParam Double originLat, @RequestParam Double originLong,
                                @RequestParam Integer maxDist){
        List<Business> destinations = filter(originLat, originLong, maxDist);
        return routeBuilder.orderOfVisitation(originLat, originLong, destinations);
    }
}
