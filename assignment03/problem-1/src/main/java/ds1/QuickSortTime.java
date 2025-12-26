package ds1;

public class QuickSortTime {

    private static int[] copyArray(int[] array) {
        int[] copy = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            copy[i] = array[i];
        }
        return copy;
    }

    private static long timeLomuto(int[] array, int times) {
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

    private static long timeHoare(int[] array, int times) {
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

    private static void test(String str, int[] array, int times) {
        long lomutoTime = timeLomuto(array, times);
        long hoareTime = timeHoare(array, times);
        System.out.println("Lomuto time: " + lomutoTime);
        System.out.println("Hoare time:  " + hoareTime);
        System.out.println();
    }

    public static void main(String[] args) {
        int times = 40;
        int[] arr1 = {5,5,5,5,5};
        test("(a.1): ", arr1, times);
        int[] arr2 = {1,2,3,4,5,6,7,8,9,10};
        test("(a.2): ", arr2, times);
    }
}