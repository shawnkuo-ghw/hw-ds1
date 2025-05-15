package ds1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AVLTests {
    public static void main(String[] args) {

        int[] A = new int[15]; // change length here
        for (int i = 0; i < A.length; i++) {
            A[i] = i + 1;
        }
    
        AutoAVL tree = new AutoAVL();
        AutoAVL.Node root = tree.buildAVL(A);
        System.out.println("Automatical AVL built without rotations:");
        tree.print(root);
    }

    @Test
    public void testIsAVL() {
        
        int[] A = new int[15];
        for (int i = 0; i < A.length; i++) {
            A[i] = i + 1;
        }

        AutoAVL tree = new AutoAVL();
        AutoAVL.Node root = tree.buildAVL(A);
        AutoAVL.resetRotationCounter();
        assertTrue(AutoAVL.isAVL(root)); // Assert this BST is an AVL, and count the number of rotations
        assertTrue(AutoAVL.getRotationCount()==0); // Assert there're no ratoations during building the AVL
    }
}