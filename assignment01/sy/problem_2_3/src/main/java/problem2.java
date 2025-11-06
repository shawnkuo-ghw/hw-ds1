package main.java;
/*
 * Pre-condition
 * 1. the range of elements in array A should be in [0,9]
 */
public class problem2 {
    public static final int MaxValue = 9;
    public static final int MinValue = 0;

    public static void sort(int[] A) {
        int B[] = new int[]{0,0,0,0,0,0,0,0,0,0};
        for(int i = 0; i < A.length; i++) {
            int index = A[i];
            B[index] = B[index] + 1;
        }
        int k = 0;
        for(int i = 0; i <= 9; i++) {
            int j = 0;
            while(j < B[i]) {
                A[k] = i;
                k = k + 1;
                j = j + 1;
            }
        }
    }
    public static void main(String[] args) {
        int A[] = new int[]{1,5,3,6,3,8,9,4,1,0,6,2,4,6,7};
        sort(A);
        for(int i = 0; i < A.length; i++) {
            System.out.println(A[i]);
        }
        
    }
}
