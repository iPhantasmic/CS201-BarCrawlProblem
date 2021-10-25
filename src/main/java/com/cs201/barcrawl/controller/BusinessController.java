package com.cs201.barcrawl.controller;

import com.cs201.barcrawl.models.Business;
import com.cs201.barcrawl.service.BusinessService;
import com.cs201.barcrawl.util.GoogleMapsUtil;
import com.squareup.okhttp.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
//@RequestMapping("/distance")
public class BusinessController {

    @Autowired
    BusinessService businessService;

    @Autowired
    GoogleMapsUtil googleMapsUtil;

    @GetMapping(value = "/test")
    public @ResponseBody ResponseEntity<?> TestApi() {

        return ResponseEntity.ok("testSubmissive");
    }

    @GetMapping(value = "/load")
    public @ResponseBody ResponseEntity<?> loadData() {
        boolean result = businessService.loadData();
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/gmapsTest")
    public Response distanceMatrix(){
        Business origin = businessService.getBusiness(1);
        List<Business> destinations = new ArrayList<>();
        destinations.add(businessService.getBusiness(2));
        destinations.add(businessService.getBusiness(3));
        return googleMapsUtil.distanceMatrix(origin, destinations);
    }
}
