package com.cs201.barcrawl.util;

import org.springframework.stereotype.Component;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;

// Implementing merge sort for a generic type
// Taken from: https://big-o.io/examples/merge-sort/java-generic/
@Component
public class MergeSortUtil<T extends Comparable<? super T>> {

    public void mergeSort(T[] array, int start, int end) {
        if (start < end) {
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

        // initial indexes of first and second subarrays
        int leftIndex = 0, rightIndex = 0;

        // the index we will start at when adding the subarrays back into the main array
        int currentIndex = start;

        // compare each index of the subarrays adding the lowest value to the currentIndex
        while (leftIndex < leftArray.length && rightIndex < rightArray.length)
        {
            if (leftArray[leftIndex].compareTo(rightArray[rightIndex]) <= 0)
            {
                array[currentIndex] = leftArray[leftIndex];
                leftIndex++;
            }
            else
            {
                array[currentIndex] = rightArray[rightIndex];
                rightIndex++;
            }
            currentIndex++;
        }

        // copy remaining elements of leftArray[] if any
        while (leftIndex < leftArray.length) array[currentIndex++] = leftArray[leftIndex++];

        // copy remaining elements of rightArray[] if any
        while (rightIndex < rightArray.length) array[currentIndex++] = rightArray[rightIndex++];
    }

    // mergeSort takes in an array of object of type T and the start and the end
    public void mergeSortSpace(T[] array, int start, int end, ArrayList<Long> space) {
        if (start < end) {
            // find the middle index
            int middle = (start + end) / 2;

            // sort first half
            mergeSortSpace(array, start, middle, space);
            // sort second half
            mergeSortSpace(array, middle + 1, end, space);

            // merge the sorted halves
            space.add(mergeSpace(array, start, middle, end));
        }
    }

    long mergeSpace(T[] array, int start, int middle, int end) {
        long usedMemoryBefore = getReallyUsedMemory();
//        System.out.println(usedMemoryBefore);

        T[] leftArray  = (T[]) new Comparable[middle - start + 1];
        T[] rightArray = (T[]) new Comparable[end - middle];

        // fill in left array
        for (int i = 0; i < leftArray.length; ++i)
            leftArray[i] = array[start + i];

        // fill in right array
        for (int i = 0; i < rightArray.length; ++i)
            rightArray[i] = array[middle + 1 + i];

        // initial indexes of first and second subarrays
        int leftIndex = 0, rightIndex = 0;

        // the index we will start at when adding the subarrays back into the main array
        int currentIndex = start;

        // compare each index of the subarrays adding the lowest value to the currentIndex
        while (leftIndex < leftArray.length && rightIndex < rightArray.length)
        {
            if (leftArray[leftIndex].compareTo(rightArray[rightIndex]) <= 0)
            {
                array[currentIndex] = leftArray[leftIndex];
                leftIndex++;
            }
            else
            {
                array[currentIndex] = rightArray[rightIndex];
                rightIndex++;
            }
            currentIndex++;
        }

        // copy remaining elements of leftArray[] if any
        while (leftIndex < leftArray.length) array[currentIndex++] = leftArray[leftIndex++];

        // copy remaining elements of rightArray[] if any
        while (rightIndex < rightArray.length) array[currentIndex++] = rightArray[rightIndex++];

        long usedMemoryAfter = getReallyUsedMemory();
//        System.out.println(usedMemoryAfter);
        return usedMemoryAfter - usedMemoryBefore;
    }

    private long getGcCount() {
        long sum = 0;
        for (GarbageCollectorMXBean b : ManagementFactory.getGarbageCollectorMXBeans()) {
            long count = b.getCollectionCount();
            if (count != -1) { sum += count; }
        }
        return sum;
    }

    private long getCurrentlyAllocatedMemory() {
        final Runtime runtime = Runtime.getRuntime();
        return (runtime.totalMemory() - runtime.freeMemory());
    }

    private long getReallyUsedMemory() {
        long before = getGcCount();
        System.gc();
        while (getGcCount() == before);
        return getCurrentlyAllocatedMemory();
    }

}
