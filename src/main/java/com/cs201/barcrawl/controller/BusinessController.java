package com.cs201.barcrawl.controller;

import com.cs201.barcrawl.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
//@RequestMapping("/distance")
public class BusinessController {

    @Autowired
    BusinessService businessService;

    @GetMapping(value = "/test")
    public @ResponseBody ResponseEntity<?> TestApi() {

        return ResponseEntity.ok("testSubmissive");
    }

    @GetMapping(value = "/load")
    public @ResponseBody ResponseEntity<?> loadData() {
        boolean result = businessService.loadData();
        return ResponseEntity.ok(result);
    }
}
