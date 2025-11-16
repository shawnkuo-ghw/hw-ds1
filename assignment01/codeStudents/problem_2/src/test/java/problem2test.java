import static org.junit.Assert.*;
import org.junit.Test;

public class problem2test {

    //Test if this sort works when array A is a valid input
    @Test
    public void sortPositiveTest() {
        int[] A = new int[]{1,5,3,6,3,8,9,4,1,0,6,2,4,6,7};
        int[] expected = new int[]{0,1,1,2,3,3,4,4,5,6,6,6,7,8,9};
        problem2.sort(A);
        assertArrayEquals(expected, A);
    }


    //Test if this sort will throw exception when input is null
    @Test
    public void sortNegativeTest1() {
        int[] A = null;
        assertThrows(IllegalArgumentException.class, () -> { problem2.sort(A);});
    }


    //Tets if this sort will throw exception when input is out of range of [0,9]
    @Test
    public void sortNegativeTest2() {
        int[] A = new int[]{-1,5,3,6,3,8,10,4,1,0,6,2,4,6,7};
        assertThrows(IllegalArgumentException.class, () -> { problem2.sort(A);});
    }

}
