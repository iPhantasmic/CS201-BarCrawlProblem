package com.cs201.barcrawl.service;

import com.cs201.barcrawl.models.Business;
import com.cs201.barcrawl.util.MergeSortUtil;
import com.cs201.barcrawl.util.QuickSortUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SortingService {

    @Autowired
    private MergeSortUtil mergeSortUtil;

    @Autowired
    private QuickSortUtil quickSortUtil;

    public Business[] wrap(List<Business> businesses) {
        return businesses.toArray(Business[]::new);
    }

    public Business[] mergeSort(List<Business> businesses) {
        Business[] businessArray = wrap(businesses);

        mergeSortUtil.mergeSort(businessArray, 0, businessArray.length - 1);

        return businessArray;
    }

    public Business[] quickSort(List<Business> businesses) {
        Business[] businessArray = wrap(businesses);

        quickSortUtil.quicksort(businessArray, 0, businessArray.length - 1);

        return businessArray;
    }


}
