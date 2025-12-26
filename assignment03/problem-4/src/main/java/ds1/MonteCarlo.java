package ds1;

import java.util.Random;

public class MonteCarlo {

    private static double average(int[] arr) {
        long sum = 0;
        for (int i = 0; i < arr.length; i++) 
            sum += arr[i];
        return (double) sum / arr.length;
    }

    private static double log2(int n) {
        return Math.log(n) / Math.log(2.0);
    }    

    //DISTINCT value
    private static int[] createRandomSequence(int n, Random random) {
        int[] sequence = new int[n];
        for (int i = 0; i < n; i++) 
            sequence[i] = i;
        for (int i = n - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int temp = sequence[i];
            sequence[i] = sequence[j];
            sequence[j] = temp;
        }
        return sequence;
    }

    private static int[] createRandomPriorities(int n, Random random) {
        int[] priorities = new int[n];
        for (int i = 0; i < n; i++) 
            priorities[i] = random.nextInt(Integer.MAX_VALUE);
        return priorities;
    }

    // 1. Treap Height Test
    private static void test1(int[] sizes, int times, long seed) {
        Random random = new Random(seed);
        System.out.println("1. Treap height Test (O(log n)) ");
        System.out.println("n || times || treapHeightAverage / Log2(n)");
        //collect the data
        for(int i = 0; i < sizes.length; i++) {
            int n = sizes[i];
            int[] treapHeights = new int[times];
            for (int j = 0; j < times; j++) {
                int[] keys = createRandomSequence(n, random);
                int[] priorities = createRandomPriorities(n, random);
                Treap treap = new Treap();
                for(int k = 0; k < keys.length; k++)
                    treap.insertWithPriority(keys[k], priorities[k]);
                treapHeights[j] = treap.height();
            }
            //We calculate the statistics data and print it without outputing raw data
            double averageHeight = average(treapHeights);
            double averageHeightLog2n = averageHeight / log2(n);
            System.out.println(n + " || " + times + " || " + averageHeightLog2n);
        }
    }

    private static double calculateMedian(int[] arr) {
        int[] temp = new int[arr.length];
        for (int i = 0; i < arr.length; i++) 
            temp[i] = arr[i];
        sort(temp);
        int mid = temp.length / 2;
        if (temp.length % 2 == 1)
            return temp[mid];
        else
            return (temp[mid - 1] + temp[mid]) / 2.0;
    }
    //insert sort
    private static void sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < arr.length; j++)
                if (arr[j] < arr[minIdx]) 
                    minIdx = j;
            int temp = arr[i];
            arr[i] = arr[minIdx];
            arr[minIdx] = temp;
        }
    }

    // 2. Treap, BST Height Comparison
    private static void test2(int[] sizes, int times, long seed) {
        Random random = new Random(seed);
        System.out.println("2. Treap, BST Height Comparison(heightDifference = HD = BST.Height - Treap.Height)");
        System.out.println("n || times || HD_Min || HD_Max || HD_Average || HD_Median");
        //collect the data
        for(int i = 0; i < sizes.length; i++) {
            int n = sizes[i];
            int[] heightDifference = new int[times];
            for (int t = 0; t < times; t++) {
                int[] keys = createRandomSequence(n, random);
                int[] priorities = createRandomPriorities(n, random);
                Treap treap = new Treap();
                BinarySearchTree bst = new BinarySearchTree();
                for (int k = 0; k < keys.length; k++) {
                    treap.insertWithPriority(keys[k], priorities[k]);
                    bst.insert(keys[k]);
                }
                heightDifference[t] = bst.height() - treap.height();
            }
            //print the result
            int min = heightDifference[0];
            int max = heightDifference[0];
            long sum = 0;
            for (int m = 0; m < heightDifference.length; m++) {
                if (heightDifference[m] < min) 
                    min = heightDifference[m];
                if (heightDifference[m] > max) 
                    max = heightDifference[m];
                sum += heightDifference[m];
            }
            double average = (double) sum / heightDifference.length;
            double median = calculateMedian(heightDifference);
            System.out.println(n + " || " + times + " || " + min + " || " + max + " || " + average + " || " + median);
        }
    }

    //print the time for test3, this can be reused
    private static void printTime(long[] arr) {
        long min = arr[0];
        long max = arr[0];
        double sum = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < min) min = arr[i];
            if (arr[i] > max) max = arr[i];
            sum += arr[i];
        }
        double average = sum / arr.length;
        double median = calculateMedian(arr);
        System.out.print("("+min + ", " + max + ", " + average + ", " + median+")");
    }

    //we need this for long array since nanotime needs it
    private static double calculateMedian(long[] arr) {
        long[] temp = new long[arr.length];
        for (int i = 0; i < arr.length; i++) temp[i] = arr[i];
        sort(temp);
        int mid = temp.length / 2;
        if (temp.length % 2 == 1) {
            return (double) temp[mid];
        } else {
            return (temp[mid - 1] + temp[mid]) / 2.0;
        }
    }
    //we need this for long array since nanotime needs it
    private static void sort(long[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIdx]) minIdx = j;
            }
            long temp = arr[i];
            arr[i] = arr[minIdx];
            arr[minIdx] = temp;
        }
    }

    // 3. Execution Time Comparison
    private static void test3(int[] sizes, int times, long seed, int numberOfBlock) {
        Random random = new Random(seed);
        System.out.println("3: Execution Time Comparison");
        System.out.println("n || times || treap(min,max,average,median) || bst(min,max,average,median) || hash(min,max,average,median) || loadFactor for Hash");
        for (int i = 0; i < sizes.length; i++) {
            int n = sizes[i];
            long[] treapTimes = new long[times];
            long[] bstTimes = new long[times];
            long[] hashTimes = new long[times];

            for (int j = 0;j < times; j++) {
                int[] keys = createRandomSequence(n, random);
                int[] priorities = createRandomPriorities(n, random);
                //Treap
                Treap treap = new Treap();
                long start = System.nanoTime();
                for (int k = 0; k < keys.length; k++) {
                    treap.insertWithPriority(keys[k], priorities[k]);
                }
                treapTimes[j] = System.nanoTime() - start;

                //BinarySearchTree
                BinarySearchTree bst = new BinarySearchTree();
                start = System.nanoTime();
                for (int k = 0; k < keys.length; k++) 
                    bst.insert(keys[k]);
                bstTimes[j] = System.nanoTime() - start;

                //HashTable
                HashTableCA hash = new HashTableCA(numberOfBlock);
                start = System.nanoTime();
                for (int k = 0; k < keys.length; k++) 
                    hash.insert(keys[k]);
                hashTimes[j] = System.nanoTime() - start;
            }
            //print the rresult
            double loadFactor = (double) n / numberOfBlock;
            System.out.print(n + " || " + times + " || ");
            printTime(treapTimes);
            System.out.print(" || ");
            printTime(bstTimes);
            System.out.print(" || ");
            printTime(hashTimes);
            System.out.println(" || " + loadFactor);
        }
    }
    
    public static void main(String[] args) {
        final int maxNumberOfBlock = 300;
        int[] ArraySize = {10000, 20000, 30000};
        int times = 300;
        long seed = 1;
        test1(ArraySize, times, seed);
        System.out.println();
        test2(ArraySize, times, seed);
        System.out.println();
        test3(ArraySize, times, seed, maxNumberOfBlock);
    }
}