package com.cs201.barcrawl.util;

import org.springframework.stereotype.Component;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;

// Taken from: https://big-o.io/examples/quicksort/java-generic/
@Component
public class QuickSortUtil<T extends Comparable<? super T>> {

    public void quickSort(T[] array, int startIndex, int endIndex) {

        // verify that the start and end index have not overlapped
        if (startIndex < endIndex) {
            // calculate the pivotIndex
            int pivotIndex = partition(array, startIndex, endIndex);
            // sort the left sub-array
            quickSort(array, startIndex, pivotIndex);
            // sort the right sub-array
            quickSort(array, pivotIndex + 1, endIndex);
        }
    }

    private int partition(T[] array, int startIndex, int endIndex) {
        int pivotIndex = (startIndex + endIndex) / 2;
        T pivotValue = array[pivotIndex];
        startIndex--;
        endIndex++;

        while (true) {
            // start at the FIRST index of the sub-array and increment
            // FORWARD until we find a value that is > pivotValue
            do startIndex++; while (array[startIndex].compareTo(pivotValue) < 0) ;

            // start at the LAST index of the sub-array and increment
            // BACKWARD until we find a value that is < pivotValue
            do endIndex--; while (array[endIndex].compareTo(pivotValue) > 0) ;

            if (startIndex >= endIndex) return endIndex;

            // swap values at the startIndex and endIndex
            T temp = array[startIndex];
            array[startIndex] = array[endIndex];
            array[endIndex] = temp;
        }
    }

    public void quickSortSpace(T[] array, int startIndex, int endIndex, ArrayList<Long> space) {
        // verify that the start and end index have not overlapped
        if (startIndex < endIndex) {
            // calculate the pivotIndex
            int pivotIndex = partitionSpace(array, startIndex, endIndex, space);
            // sort the left sub-array
            quickSortSpace(array, startIndex, pivotIndex, space);
            // sort the right sub-array
            quickSortSpace(array, pivotIndex + 1, endIndex, space);
        }
    }

    private int partitionSpace(T[] array, int startIndex, int endIndex, ArrayList<Long> space) {
        long usedMemoryBefore = getReallyUsedMemory();
//        System.out.println(usedMemoryBefore);

        int pivotIndex = (startIndex + endIndex) / 2;
        T pivotValue = array[pivotIndex];
        startIndex--;
        endIndex++;

        while (true) {
            // start at the FIRST index of the sub-array and increment
            // FORWARD until we find a value that is > pivotValue
            do startIndex++; while (array[startIndex].compareTo(pivotValue) < 0) ;

            // start at the LAST index of the sub-array and increment
            // BACKWARD until we find a value that is < pivotValue
            do endIndex--; while (array[endIndex].compareTo(pivotValue) > 0) ;

            if (startIndex >= endIndex) {
                long usedMemoryAfter = getReallyUsedMemory();
//                System.out.println(usedMemoryAfter);
                space.add(usedMemoryAfter - usedMemoryBefore);
                return endIndex;
            }

            // swap values at the startIndex and endIndex
            T temp = array[startIndex];
            array[startIndex] = array[endIndex];
            array[endIndex] = temp;
        }
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
