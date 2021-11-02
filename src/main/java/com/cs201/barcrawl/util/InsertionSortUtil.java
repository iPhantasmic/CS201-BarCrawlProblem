package com.cs201.barcrawl.util;

import org.springframework.stereotype.Component;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;

@Component
public class InsertionSortUtil<T extends Comparable<? super T>> {

    public long[] insertionSort(T[] array) {
        long usedMemoryBefore = getReallyUsedMemory();
        long before = System.currentTimeMillis();
        System.out.println(usedMemoryBefore);

        // start at the first index and iterate through to the end
        for (int i = 1; i < array.length; i++) {
            int currentIndex = i;
            /*
             * Check:
             *      1. that currentIndex is at least 1
             *      2. that the item directly before the currentIndex is greater than the item at currentIndex
             *
             * If both conditions are met, swap the indexes
             */
            while (currentIndex > 0 && array[currentIndex - 1].compareTo(array[currentIndex]) > 0)
            {
                T temp = array[currentIndex];
                array[currentIndex] = array[currentIndex - 1];
                array[currentIndex - 1] = temp;
                currentIndex--;
            }
        }

        long after = System.currentTimeMillis();
        long usedMemoryAfter = getReallyUsedMemory();
        System.out.println(usedMemoryAfter);
        long[] results = new long[2];
        results[0] = after - before;
        results[1] = usedMemoryAfter - usedMemoryBefore;
        return results;
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