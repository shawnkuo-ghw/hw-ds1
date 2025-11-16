package mygroup;

public class minHeapCheckRep {

    public static <T extends Comparable<T>> boolean minHeapRepOK(T[] elem, int start, int end){
        if(elem == null || start < 0 || end >= elem.length || start > end)
            return false;
        int i;
        for(i = start; i < (end - 1)/2; i++){
            int left = 2*i+1;
            int right = 2*i+2;
            if(elem[i].compareTo(elem[left]) > 0 && left <= end)
                return false;
            if(elem[i].compareTo(elem[right]) > 0 && right <= end)
                return false;
        }
        return true;
    }
}
