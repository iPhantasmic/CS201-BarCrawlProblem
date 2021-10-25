package com.cs201.barcrawl.service;

import com.cs201.barcrawl.models.Business;
import com.cs201.barcrawl.repository.BusinessRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;


@Service
public class BusinessService {

    @Autowired
    private BusinessRepository businessRepository;

    public boolean loadData() {
        BufferedReader br;
        try {
            String sCurrentLine;
            br = new BufferedReader(new FileReader("target/result.json"));

            ObjectMapper objectMapper = new ObjectMapper();

            while ((sCurrentLine = br.readLine()) != null) {
                objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                Business business = objectMapper.readValue(sCurrentLine, Business.class);
                businessRepository.save(business);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public Business getBusiness(long id){
        return businessRepository.findById(id)
                                    .orElseThrow(()-> new IllegalArgumentException("Business does not exist"));
    }

    // TODO: sorting algorithms here
    // start measuring time taken only after receiving the object from businessRepository query

    // can use this below to ensure that the data returned from repository is always the same
    // List<Business> results = businessRepository.findAll(Sort.by(Sort.Direction.ASC, "name"))

}
