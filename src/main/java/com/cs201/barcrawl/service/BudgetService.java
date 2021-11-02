package com.cs201.barcrawl.service;

import com.cs201.barcrawl.models.Business;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

@Service
public class BudgetService {
    public Business[] getOptimizedBudget(ArrayList<Business> businessesAL, int budget) {
        Business[][][] businesses = new Business[businessesAL.size()+1][budget+1][businessesAL.size()+1];
        for(int i = 0; i <= businessesAL.size(); i++) {
            for (int j = 0; j <= budget; j++) {
                if (i == 0 || j == 0) {
                    Business[] dummyBusinesses = new Business[businessesAL.size()];
                    Business dummyBusiness = new Business();
                    dummyBusiness.setStars(0);
                    dummyBusinesses[0] = dummyBusiness;
                    businesses[i][j] = dummyBusinesses;
                }
                else if (getPriceBasedOnAttribute(businessesAL.get(i-1)) <= j) {
                    double currentStars = getValues(businesses[i-1][j]);
                    double possibleNewStars = businessesAL.get(i-1).getStars()
                            + getValues(businesses[i-1][j - getPriceBasedOnAttribute(businessesAL.get(i-1))]);
                    if (currentStars > possibleNewStars) {
                        businesses[i][j] = businesses[i-1][j].clone();
                    } else {
                        Business[] toAdd = businesses[i-1][j - getPriceBasedOnAttribute(businessesAL.get(i-1))].clone();
                        int index = getEmptyIndex(toAdd);
                        toAdd[index] = businessesAL.get(i-1);
                        businesses[i][j] = toAdd;
                    }
                }
                else {
                    businesses[i][j] = businesses[i-1][j].clone();
                }
            }
        }
        return businesses[businessesAL.size()][budget];
    }

    private double  getValues(Business[] businesses) {
        double res = 0;
        for (int i =0; i < businesses.length; i++) {
            if (businesses[i] != null) {
                res += businesses[i].getStars();
            }
        }
        return res;
    }

    private int getEmptyIndex(Business[] businesses) {
        for(int i = 0; i < businesses.length; i++) {
            if (businesses[i] == null) { return i;}
        }
        return businesses.length -1;
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
