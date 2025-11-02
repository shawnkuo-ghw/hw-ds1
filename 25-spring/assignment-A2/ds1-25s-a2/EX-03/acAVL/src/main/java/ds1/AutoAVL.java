package ds1;

public class AutoAVL {
   
    class Node {
        int val;
        Node left;
        Node right;
        Node parent;
        int height;
    
        Node(int x) {
            val = x;
            height = 1;
        }
        int getValue() {
            return val;
        }
    }

    static int rotationCounter = 0;

    public Node buildAVL(int[] A){
        return buildAVLrecursive(A);
    }

    private Node buildAVLrecursive(int[] A){
        int n = A.length;
        if (n == 0) {
            return null;
        }
        int m = n / 2;
        Node node = new Node(A[m]);
        int[] sub1 = new int[m];
        int[] sub2 = new int[n-m-1];
        System.arraycopy(A, 0, sub1, 0, m);
        System.arraycopy(A, m+1, sub2, 0, n-m-1);
        node.left = buildAVLrecursive(sub1);
        node.right = buildAVLrecursive(sub2);
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        
        return node;
    }

    private static int getHeight(Node node){
        if (node == null) {
            return 0;
        } else {
            return node.height;
        }
    }

    public static int balanceFactor(Node curr) {
        return getHeight(curr.right) - getHeight(curr.left);
    }

    public static boolean isAVL(Node node) {
        return AVLchecker(node) != -1;
    }

    private static int AVLchecker(Node node) {
        if (node == null) {
            return 0;
        }

        if (AVLchecker(node.left) == -1) {
            return -1;
        } else if (AVLchecker(node.right) == -1) {
            return -1;
        } else if (Math.abs(balanceFactor(node)) > 1) {
            rotationCounter++;
            return -1;
        }
        return node.height;
    }

    public static void resetRotationCounter() {
        rotationCounter = 0;
    }
    
    public static int getRotationCount() {
        return rotationCounter;
    }    

    public void print(Node node) {
        System.out.println("AVL Tree");
        print(node, "");

    }

    private void print(Node node, String prefix) {
        if (node != null) {
            print(node.right, prefix + "    ");
            System.out.println(prefix + node.val + "(h=" + node.height + ")");
            print(node.left, prefix + "    ");
        }
    }
}