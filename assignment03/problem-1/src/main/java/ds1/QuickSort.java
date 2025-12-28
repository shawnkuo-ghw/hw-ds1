package ds1;

public class QuickSort {

    public static int partitionLomuto(int[] A, int l, int r) {
        int i = l - 1;
        for (int j = l; j < r; j++) {
            if (A[j] < A[r]) {
                i = i + 1;
                swap(A, i, j);
            }
        }
        swap(A, i + 1, r);
        return i + 1;
    }

    public static void quickSortLomuto(int[] A) {
        quickSortLomuto(A, 0, A.length - 1);
    }

    private static void quickSortLomuto(int[] A, int l, int r) {
        if (l < r) {
            int p = partitionLomuto(A, l, r);
            quickSortLomuto(A, l, p - 1);
            quickSortLomuto(A, p + 1, r);
        }
    }

    public static int partitionHoare(int[] A, int l, int r) {
        int i = l;
        int j = r - 1;
        int pivot = A[r];

        while (i <= j) {
            while (i <= j && A[i] < pivot) {
                i = i + 1;
            }
            while (i <= j && A[j] > pivot) {
                j = j - 1;
            }
            if (i <= j) {
                swap(A, i, j);
                i = i + 1;
                j = j - 1;
            }
        }
        swap(A, i, r);
        return i;
    }

    public static void quickSortHoare(int[] A) {
        quickSortHoare(A, 0, A.length - 1);
    }

    private static void quickSortHoare(int[] A, int l, int r) {
        if (l < r) {
            int p = partitionHoare(A, l, r);
            quickSortHoare(A, l, p - 1);
            quickSortHoare(A, p + 1, r);
        }
    }

    private static void swap(int[] A, int i, int j) {
        int aux = A[i];
        A[i] = A[j];
        A[j] = aux;
    }
}
