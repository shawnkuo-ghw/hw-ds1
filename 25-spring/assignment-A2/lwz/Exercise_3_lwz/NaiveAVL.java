public class NaiveAVL{ 
    class AVLNode {
        int val;
        AVLNode left;
        AVLNode right;
        AVLNode parent;
        int height;

        AVLNode(int x) {
            val = x;
            height = 1;
        }
        int getValue() {
            return val;
        }

    }

    public class AVLTree {
        AVLNode root;

        static int rotationCounter = 0;

        // create an empty AVL tree
        public AVLTree() {
            root = null;
        }

        // get the root of the AVL tree
        public int getRootValue() {
            return root.val;
        }

        // Helpers for testing
        public Object getRoot() {
            return root;
        }

        // get the left child of the AVL tree
        public AVLNode getLeft() {
            return root.left;
        }

        public AVLNode getRight() {
            return root.right;
        }
        
        // computes the height of the AVL tree
        public int height() {
            return height(root);
        }

        // computes the height of the AVL tree rooted at curr
        private int height(AVLNode curr) {
            if (curr == null) {
                return 0;
            } else {
                return curr.height;
            }
        }

        // compute balance factor of the AVL tree
        public int balanceFactor(AVLNode curr) {
            if (curr == null) {
                return 0;
            } else {
                return height(curr.right) - height(curr.left);
            }
        }

        // update the height of the AVL tree rooted at curr
        private void updateHeight(AVLNode curr) {
            curr.height = 1 + Math.max(height(curr.left), height(curr.right));
        }

        //insert into an AVL tree
        public void insert(int val) {
            root = insert(root, val);
        }

        // insert into an AVL tree rooted at curr
        private AVLNode insert(AVLNode curr, int val) {
            if (curr == null) {
                return new AVLNode(val);
            }
            if (val < curr.val) {
                curr.left = insert(curr.left, val);
            } else if (val > curr.val) {
                curr.right = insert(curr.right, val);
            } else {
                return curr;
            }
            return rebalance(curr);
        }

        // rebalance the AVL tree rooted at curr
        private AVLNode rebalance(AVLNode curr) {
            
            if (balanceFactor(curr) < -1 && balanceFactor(curr.left) < 0) {
                rotationCounter++;
                return rightRotate(curr);
            } else if (balanceFactor(curr) > 1 && balanceFactor(curr.right) > 0) {
                rotationCounter++;
                return leftRotate(curr);
            } else if (balanceFactor(curr) < -1 && balanceFactor(curr.left) > 0) {
                rotationCounter += 2;
                return leftRightRotate(curr);
            } else if (balanceFactor(curr) > 1 && balanceFactor(curr.right) < 0) {
                rotationCounter += 2;
                return rightLeftRotate(curr);
            }
            updateHeight(curr);
            return curr;
        }

        // left rotate the subtree rooted at curr
        private AVLNode leftRotate(AVLNode p) {
            AVLNode q = p.right;
            p.right = q.left;
            if(p == root)
                root = q;

            // optional update parents
            updateParentsLeftRotate(p, q);
            q.left = p;
            // update the height of the current node
            updateHeight(p);
            updateHeight(q);
            return q;
        }

        private void updateParentsLeftRotate(AVLNode p, AVLNode q) {
            if (q.left != null) {
                q.left.parent = p;
            }
            q.parent = p.parent;
            if (p.parent != null) {
            if (p == p.parent.left) {
                p.parent.left = q;
                } else {
                p.parent.right = q;
                };
            }
            p.parent = q;

        }
        
        // right rotate the subtree rooted at curr
        private AVLNode rightRotate(AVLNode p) {
            AVLNode q = p.left;
            p.left = q.right;
            if(p == root)
                root = q;

            // optional update parents
            updateParentsRightRotate(p, q);
            // 
            q.right = p;
        
            // update the height of the current node
            updateHeight(p);
            updateHeight(q);
            return q;
        }

        private void updateParentsRightRotate(AVLNode p, AVLNode q) {
            if (q.right != null) {
                q.right.parent = p;
            }
            q.parent = p.parent;
            if (p.parent != null) {
                if (p == p.parent.right) {
                    p.parent.right = q;
                } else {
                    p.parent.left = q;
                }
            }
            p.parent = q;

        }

        // left-right rotate the subtree rooted at curr
        private AVLNode leftRightRotate(AVLNode curr) {
            curr.left = leftRotate(curr.left);
            return rightRotate(curr);
        }

        // right-left rotate the subtree rooted at curr
        private AVLNode rightLeftRotate(AVLNode curr) {
            curr.right = rightRotate(curr.right);
            return leftRotate(curr);
        }

        public int getRotationCount() {
            return rotationCounter;
        }

        public void build(int[] A) {
            for (int val : A) {
                insert(val);
            }
        }
        
    }

    public static void main(String[] args) {
        NaiveAVL outer = new NaiveAVL();
        AVLTree root = outer.new AVLTree();

        int[] A = new int[11];
        for (int i = 0; i < 11; i++){
            A[i] = i + 1;
        }
        root.build(A);
        System.out.println("Total number of rotations: " + root.getRotationCount());
    }
}
