package hw1;

import static org.junit.Assert.assertTrue;

import java.util.Random;
import org.junit.jupiter.api.*;

public class SortingOverDoubleStackTest {

    private int[] arr;
    private static final int DEFAULT_SIZE = 10;
    private SortingOverDoubleStack dsSort;

    private static void shuffleArray(int[] arr)
    {
        Random rand = new Random();
        for ( int i = 0; i < arr.length; i++ )
        {
            int j = rand.nextInt(i + 1); // [0, i]
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    private static boolean isSortedAscendingly(int[] arr)
    {
        boolean flag = true;
        int i = 0;
        while ( flag && i < arr.length - 1 )
        {
            if ( arr[i] > arr[i+1] ) {
                flag = false;
            } else {
                i ++;
            }
        }
        return flag;
    }

    private static String arrStringRep(int[] arr)
    {
        String rep = "[ ";    
        for ( int i = 0; i < arr.length; i++ )
        {
            rep += arr[i];
            if ( i != arr.length - 1 ) {
                rep += ", ";
            }
        }
        rep += " ]";
        return rep;
    }

    @BeforeEach
    public void init()
    {
        arr = new int[DEFAULT_SIZE];
        for ( int i = 0; i < arr.length; i++ )
        {
            arr[i] = i + 1; 
        }
        shuffleArray(arr);
        dsSort = new SortingOverDoubleStack(arr.length);
    }

    @Test
    public void sortTest_One()
    {
        dsSort.sort(arr);
        assertTrue(isSortedAscendingly(arr));
        System.out.println(arrStringRep(arr));
    }

    @Test
    public void sortTest_Two()
    {
        dsSort.sort(arr);
        assertTrue(isSortedAscendingly(arr));
        System.out.println(arrStringRep(arr));
    }

    @Test
    public void sortTest_Three()
    {
        dsSort.sort(arr);
        assertTrue(isSortedAscendingly(arr));
        System.out.println(arrStringRep(arr));
    }

    @Test
    public void sortTest_Four()
    {
        dsSort.sort(arr);
        assertTrue(isSortedAscendingly(arr));
        System.out.println(arrStringRep(arr));
    }

    @Test
    public void sortTest_Five()
    {
        dsSort.sort(arr);
        assertTrue(isSortedAscendingly(arr));
        System.out.println(arrStringRep(arr));
    }
}
