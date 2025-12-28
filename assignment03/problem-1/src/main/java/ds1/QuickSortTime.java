package ds1;

public class QuickSortTime {

    private static int[] copyArray(int[] array) {
        int[] copy = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            copy[i] = array[i];
        }
        return copy;
    }

    private static long timeQuicksortLomuto(int[] array, int times) {
        long totalTime = 0;

        for (int i = 0; i < times; i++) {
            // Copy a new array to avoid modifying original array
            int[] copy = copyArray(array);
            //start timer
            long start = System.nanoTime();
            QuickSort.quickSortLomuto(copy);
            long end = System.nanoTime();
            totalTime += (end - start);
        }
        return totalTime;
    }

    private static long timeQuicksortHoare(int[] array, int times) {
        long totalTime = 0;
        for (int i = 0; i < times; i++) {
            //Copy a new array to avoid modifying original array
            int[] copy = copyArray(array);
            //start timer
            long start = System.nanoTime();
            QuickSort.quickSortHoare(copy);
            long end = System.nanoTime();
            totalTime += (end - start);
        }
        return totalTime;
    }

    private static long timePartitionLomuto(int[] array, int times) {
        long totalTime = 0;
        for (int i = 0; i < times; i++) {
            //Copy a new array to avoid modifying original array
            int[] copy = copyArray(array);
            long start = System.nanoTime();
            //start timer
            QuickSort.partitionLomuto(copy, 0, copy.length - 1);
            long end = System.nanoTime();
            totalTime += (end - start);
        }
        return totalTime;
    }

    private static long timePartitionHoare(int[] array, int times) {
        long totalTime = 0;
        for (int i = 0; i < times; i++) {
            //Copy a new array to avoid modifying original array
            int[] copy = copyArray(array);
            long start = System.nanoTime();
            //start timer
            QuickSort.partitionHoare(copy, 0, copy.length - 1);
            long end = System.nanoTime();
            totalTime += (end - start);
        }
        return totalTime;
    }

    private static void test(int[] array, int times) {
        long PartitionLomutoTime = timePartitionLomuto(array, times);
        long PartitionHoareTime = timePartitionHoare(array, times);
        System.out.println("Partition Lomuto time: " + PartitionLomutoTime);
        System.out.println("Partition Hoare time:  " + PartitionHoareTime);
        if (PartitionLomutoTime < PartitionHoareTime)
            System.out.println("Partition Lomuto Partition is faster");
        else
            System.out.println("Partition Hoare Partition is faster");
        System.out.println();

        long QuicksortLomutoTime = timeQuicksortLomuto(array, times);
        long QuicksortHoareTime = timeQuicksortHoare(array, times);
        System.out.println("Quicksort using Lomuto time: " + QuicksortLomutoTime);
        System.out.println("Quicksort using Hoare time:  " + QuicksortHoareTime);
        if (QuicksortLomutoTime < QuicksortHoareTime)
            System.out.println("Quicksort using Lomuto Partition is faster");
        else
            System.out.println("Quicksort using Hoare Partition is faster");
        System.out.println();
}

    public static void main(String[] args) {
        int times = 100; 
        int[] arr1 = new int[10000];
        for(int i=0; i<arr1.length; i++) arr1[i] = 5;
        // Expected: Partition Lomuto < Hoare, but Quicksort Lomuto > Hoare
        System.out.println("For 1.a:");
        test(arr1, times);
        // Expected: Partition Hoare < Lomuto, and Quicksort Hoare < Lomuto
        int[] arr2 = new int[10000];
        for(int i=0; i<arr2.length; i++) arr2[i] = i;
        System.out.println("For 1.b");
        test(arr2, times);
    }
}
