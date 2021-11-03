package com.cs201.barcrawl.service;

import com.cs201.barcrawl.models.Business;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Service
public class BudgetService {
    public ArrayList<Business> getOptimizedBudget(List<Business> businessesAL, int budget) {
        ArrayList<Business>[][] businesses = new ArrayList[businessesAL.size()+1][budget+1];
        for(int i = 0; i <= businessesAL.size(); i++) {
            for (int j = 0; j <= budget; j++) {
                if (i == 0 || j == 0) {
                    businesses[i][j] = new ArrayList<Business>();
                } else if (getPriceBasedOnAttribute(businessesAL.get(i - 1)) <= j) {
                    double currentStars = getValues(businesses[i - 1][j]);
                    double possibleNewStars = businessesAL.get(i - 1).getStars()
                            + getValues(businesses[i - 1][j - getPriceBasedOnAttribute(businessesAL.get(i - 1))]);
                    if (currentStars > possibleNewStars) {
                        // If visiting this business does not increase stars, don't add this business to the list
                        businesses[i][j] = businesses[i - 1][j];
                    } else {
                        // If visiting this business does increase stars, add it to the list
                        ArrayList<Business> temp = new ArrayList<Business>(businesses[i - 1][j - getPriceBasedOnAttribute(businessesAL.get(i - 1))]);
                        temp.add(businessesAL.get(i - 1));
                        businesses[i][j] = temp;

                    }
                } else {
                    businesses[i][j] = businesses[i][j - 1];
                }
            }
        }
        return businesses[businessesAL.size()][budget];
    }

    private double  getValues(List<Business> businesses) {
        double res = 0;
        for (Business b: businesses) {
            res += b.getStars();
        }
        return res;
    }

    private int getPriceBasedOnAttribute(Business business) {
        if (business.getStars() == 0.0) {
            return 0;
        }
        if (business.getAttributes().get("RestaurantsPriceRange2") == null) {
            return 20;
        }
        int range = business.getAttributes()
                .get("RestaurantsPriceRange2").asInt();
        switch (range){
            case 4:
                return 80;
            case 3:
                return 45;
            case 1:
                return 10;
            case 2:
            default:
                return 20;
        }
    }
}
