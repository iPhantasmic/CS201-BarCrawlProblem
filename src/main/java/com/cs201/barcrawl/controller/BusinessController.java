package com.cs201.barcrawl.controller;

import com.cs201.barcrawl.models.Business;
import com.cs201.barcrawl.service.BusinessService;
import com.cs201.barcrawl.util.DistanceUtil;
import com.cs201.barcrawl.util.GoogleMapsUtil;
import com.cs201.barcrawl.util.routing.RouteBuilder;
import com.google.gson.JsonElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @GetMapping(value = "/routeTest")
    public List<Business> routing(){
        List<Business> destinations = new ArrayList<>();
        destinations.add(businessService.getBusiness(5));
        destinations.add(businessService.getBusiness(11));
        destinations.add(businessService.getBusiness(16));
        destinations.add(businessService.getBusiness(25));
        destinations.add(businessService.getBusiness(26));
        return routeBuilder.orderOfVisitation(destinations);
    }
}
