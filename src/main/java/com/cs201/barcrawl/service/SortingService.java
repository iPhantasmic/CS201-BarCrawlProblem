package com.cs201.barcrawl.service;

import com.cs201.barcrawl.models.Business;
import com.cs201.barcrawl.models.SortedDTO;
import com.cs201.barcrawl.util.HeapSortUtil;
import com.cs201.barcrawl.util.InsertionSortUtil;
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

    @Autowired
    private InsertionSortUtil insertionSortUtil;

    @Autowired
    private HeapSortUtil heapSortUtil;

    public Business[] wrap(List<Business> businesses) {
        return businesses.toArray(Business[]::new);
    }

    public SortedDTO mergeSort(List<Business> businesses) {
        Business[] businessArray = wrap(businesses);

        long before = System.currentTimeMillis();
        mergeSortUtil.mergeSort(businessArray, 0, businessArray.length - 1);
        long after = System.currentTimeMillis();

        SortedDTO sortedDTO = new SortedDTO();
        sortedDTO.setTime(after - before);
        sortedDTO.setDestinations(businessArray);

        return sortedDTO;
    }

    public SortedDTO quickSort(List<Business> businesses) {
        Business[] businessArray = wrap(businesses);

        long before = System.currentTimeMillis();
        quickSortUtil.quicksort(businessArray, 0, businessArray.length - 1);
        long after = System.currentTimeMillis();

        SortedDTO sortedDTO = new SortedDTO();
        sortedDTO.setTime(after - before);
        sortedDTO.setDestinations(businessArray);

        return sortedDTO;
    }

    public SortedDTO insertionSort(List<Business> businesses) {
        Business[] businessArray = wrap(businesses);

        long before = System.currentTimeMillis();
        insertionSortUtil.insertionSort(businessArray);
        long after = System.currentTimeMillis();

        SortedDTO sortedDTO = new SortedDTO();
        sortedDTO.setTime(after - before);
        sortedDTO.setDestinations(businessArray);

        return sortedDTO;
    }

    public SortedDTO heapSort(List<Business> businesses) {
        Business[] businessArray = wrap(businesses);

        long before = System.currentTimeMillis();
        heapSortUtil.heapSort(businessArray);
        long after = System.currentTimeMillis();

        SortedDTO sortedDTO = new SortedDTO();
        sortedDTO.setTime(after - before);
        sortedDTO.setDestinations(businessArray);

        return sortedDTO;
    }
}
