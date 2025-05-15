package ds1;

public class MinHeapRep {
    
    public static <T extends Comparable<T>> boolean minHeapRepOK(T[] elems, int start, int end) {
        if (start < 1 || end >= elems.length || start > end) {
            return false;
        }
        for (int i = start; i <= end; i++) {
            int leftChild = 2 * i;
            int rightChild = 2 * i + 1;
            if (leftChild <= end && elems[i].compareTo(elems[leftChild]) > 0) {
                return false;
            } else if (rightChild <= end && elems[i].compareTo(elems[rightChild]) > 0) {
                return false;
            }
        }
        return true;
    }

}
