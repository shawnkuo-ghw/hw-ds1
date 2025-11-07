/*
 * Pre-condition
 * 1. array A is not null
 * 2. the range of elements in array A are in [0,9] otherwise the index of B will out of bound
 */

public class problem2 {
    public static final int MaxValue = 9;
    public static final int MinValue = 0;

    public static void sort(int[] A) {
        //check Pre-condition 1
        if(A == null)
            throw new IllegalArgumentException("array A should not be null");

        //check Pre-condition 2
        for(int i = 0; i < A.length; i++) {
            if (A[i] < MinValue || A[i] > MaxValue) {
                throw new IllegalArgumentException("Elements in array A should be in [0,9]");
            }
        }

        //Algorithm starts
        //set B[i] := 0 for every i between 0 and 9 using the simplest way
        int B[] = new int[]{0,0,0,0,0,0,0,0,0,0};
        //The following code totally follow the pseudo-code
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
}
