package com.cs201.barcrawl.util;

// Implementing merge sort for a generic type
public class MergeSort<T extends Comparable<? super T>> {

    // mergeSort takes in an array of object of type T and the start and the end
    void mergeSort(T[] array, int start, int end) {
        if (start < end)
        {
            // find the middle index
            int middle = (start + end) / 2;

            // sort first half
            mergeSort(array, start, middle);
            // sort second half
            mergeSort(array, middle + 1, end);

            // merge the sorted halves
            merge(array, start, middle, end);
        }
    }

    
}
