package com.cs201.barcrawl.service;

import com.cs201.barcrawl.models.Business;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BudgetService {
    public ArrayList<Business> getRestaurantsUnderBudget(ArrayList<Business> businessAL, double budget) {
        ArrayList<ArrayList<ArrayList<Business>>> table = new ArrayList<>();

        long budgetLong = (long) (budget * 100);

        for(int i = 0; i <= businessAL.size(); i++) {
            for(int j = 0; j <= budgetLong; j++) {
                if (i == 0 || j == 0) {
                    ArrayList<Business> dummyBusinessAL = new ArrayList<Business>();
                    Business dummyBusiness = new Business();
                    dummyBusiness.setStars(0);
                    dummyBusiness.setPrice(0);
                    dummyBusinessAL.add(dummyBusiness);
                    ArrayList<ArrayList<Business>> dummyVal = new ArrayList<>();
                    dummyVal.add(dummyBusinessAL);
                    table.add(dummyVal);
                }
                else if (businessAL.get(i - 1).getPrice() <= budgetLong) {
                    double prevStarVal = getTotalStarsCurrentBasket(table.get(i-1).get(j));
                    double addNewBusiness = businessAL.get(i-1).getStars()
                            + getTotalStarsCurrentBasket(table.get(i-1)
                            .get((int) (j - businessAL.get(i-1).getPrice())));
                    ArrayList<Business> newBusinessesAL = null;
                    if (prevStarVal > addNewBusiness) {
                        newBusinessesAL = table.get(i-1).get(j);
                    } else {
                        newBusinessesAL = table.get(i-1)
                                .get((int) (j - businessAL.get(i-1).getPrice()));
                        newBusinessesAL.add(businessAL.get(i-1));
                    }
                    ArrayList<ArrayList<Business>> valToAdd = new ArrayList<>();
                    valToAdd.add(newBusinessesAL);
                    table.add(valToAdd);
                }
                else {
                    ArrayList<Business> newBusinessesAL = table.get(i-1).get(j);
                    ArrayList<ArrayList<Business>> valToAdd = new ArrayList<>();
                    valToAdd.add(newBusinessesAL);
                    table.add(valToAdd);
                }
            }
        }

        return table.get(businessAL.size()).get((int) budgetLong);
    }

    private double getTotalPriceCurrentBasket(ArrayList<Business> businessesAL) {
        double total = 0;
        for(int i = 0; i < businessesAL.size(); i++) {
            total += businessesAL.get(i).getPrice();
        }
        return total;
    }

    private double getTotalStarsCurrentBasket(ArrayList<Business> businessesAL) {
        double total = 0;
        for(int i = 0; i < businessesAL.size(); i++) {
            total += businessesAL.get(i).getStars();
        }
        return total;
    }
}
