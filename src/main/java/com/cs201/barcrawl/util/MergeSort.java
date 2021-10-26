package com.cs201.barcrawl.util;

// Implementing merge sort for a generic type
// Taken from: https://big-o.io/examples/merge-sort/java-generic/
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

    void merge(T[] array, int start, int middle, int end) {
        T[] leftArray  = (T[]) new Comparable[middle - start + 1];
        T[] rightArray = (T[]) new Comparable[end - middle];

        // fill in left array
        for (int i = 0; i < leftArray.length; ++i)
            leftArray[i] = array[start + i];

        // fill in right array
        for (int i = 0; i < rightArray.length; ++i)
            rightArray[i] = array[middle + 1 + i];
    }
}
