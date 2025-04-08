public class sorting {
    public static void sorting(int[]A, int n, int h){
        int k;

        for(k = 0; k < n/h; k++){
            insertionSort(A, k*h, h);
        }
        insertionSort(A, k*(n-k*h), n-k*h);

        

       for(k = h; k < n; k *=2){
            for(int j = 0; j < n-k; j += 2*k){
                int high = Math.min(j + 2*k-1, n-1);
                Merge(A, j, j+k+1, high);
            }
       }
    }

    public static void insertionSort(int[]a, int low, int size){
        for(int i = low+1; i < low + size; i++){
            int key = a[i];
            int j = i-1;
            while(j >= low && a[j] > key){
                a[j + 1] = a[j];
                j--;
            }
            a[j+1] = key;
        }
    }

    public static void Merge(int[]b, int low, int mid, int high){
        int[]result = new int[high - low + 1];
        int i = 0;
        int l = low;
        int h = mid + 1;
        while(l <= mid && h <= high){
            if(b[l] <= b[h]){
                result[i] = b[l];
                i++;
                l++;
            }else{
                result[i] = b[h];
                h++;
                i++;
            }
        }
        while(l <= mid){
            result[i] = b[l];
            i++;
            l++;
        }
        while(h <= high){
            result[i] = b[h];
            h++;
            i++;
        }
    }

    public static void main(String[] args) {
        int[]arr = {5,2,9,1,4,6};
        int n = arr.length;
        int h = 4;
        sorting(arr, n, h);
        String rep = "";
        for(int num : arr){
            rep += num + "  ";
        }
        System.out.println(rep);
    }
}
