package hw1;

public class SortingOverDoubleStack {

    private DoubleStackOfInt s;

    public SortingOverDoubleStack()
    {
        s = new DoubleStackOfIntOnArray();
    }

    public SortingOverDoubleStack(int arrSize)
    {
        s = new DoubleStackOfIntOnArray(2 * arrSize);
    }
   
    /**
     *  Example: l = [3, 1, 5, 2, 4] 
     * 
     *  index:  -1  0   1   2   3   4   5   6   7   8   9  10
     *     s =     [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]     aux =
     *           h                                          t
     *     s =     [3] [1] [5] [2] [4] [ ] [ ] [ ] [ ] [ ]     aux =
     *                              h                       t
     * 
     *     s =     [3] [1] [5] [2] [ ] [ ] [ ] [ ] [ ] [4]     aux =
     *                          h                       t
     *     s =     [3] [1] [5] [ ] [ ] [ ] [ ] [ ] [ ] [4]     aux = 2
     *                      h                           t
     *     s =     [3] [1] [5] [4] [ ] [ ] [ ] [ ] [ ] [ ]     aux = 2
     *                          h                           t
     *     s =     [3] [1] [5] [4] [ ] [ ] [ ] [ ] [ ] [2]     aux =
     *                          h                       t
     *     s =     [3] [1] [5] [ ] [ ] [ ] [ ] [ ] [4] [2]     aux =
     *                      h                       t
     *     s =     [3] [1] [ ] [ ] [ ] [ ] [ ] [5] [4] [2]     aux =
     *                  h                       t
     *     s =     [3] [ ] [ ] [ ] [ ] [ ] [ ] [5] [4] [2]     aux = 1
     *              h                           t
     *     s =     [3] [5] [4] [2] [ ] [ ] [ ] [ ] [ ] [ ]     aux = 1
     *                          h                           t
     *     s =     [3] [5] [4] [2] [ ] [ ] [ ] [ ] [ ] [1]     aux =
     *                          h                       t
     *     s =     [3] [ ] [ ] [ ] [ ] [ ] [5] [4] [2] [1]     aux =
     *              h                       t
     *     s =     [ ] [ ] [ ] [ ] [ ] [ ] [5] [4] [2] [1]     aux = 3
     *           h                          t
     *     s =     [5] [4] [ ] [ ] [ ] [ ] [ ] [ ] [2] [1]     aux = 3
     *                  h                           t
     *     s =     [5] [4] [ ] [ ] [ ] [ ] [ ] [3] [2] [1]     aux =
     *                  h                       t
     *     s =     [ ] [ ] [ ] [ ] [ ] [5] [4] [3] [2] [1]     aux =
     *           h                      t
     *     s =     [5] [4] [3] [2] [1] [ ] [ ] [ ] [ ] [ ]     aux =
     *                              h                       t
     */

    public void sort(int[] arr)
    {
        int aux = 0;

        // 1. push all the element into the tail-side stack
        for (int elem: arr) {
            s.pushTail(elem);
        }

        // 2. sort
        System.out.println(s.toString());
        boolean ifContinue = true;
        while ( ifContinue )
        {
            if ( s.isEmptyHead() ) {
                while ( !s.isEmptyTail() ) {
                    s.pushHead(s.popTail());
                }
            } else if ( s.isEmptyTail() ) {
                s.pushTail(s.popHead());
            } else {
                if ( s.topHead() > s.topTail() ) {
                    s.pushTail(s.popHead());
                } else {
                    aux = s.popHead();
                    while ( !s.isEmptyTail() && s.topTail() > aux ) {
                        s.pushHead(s.popTail());
                    }
                    s.pushTail(aux);
                }
            }
            if ( s.headIdx() == arr.length - 1 && s.isSortedDescendinglyHead() )
            {
                ifContinue = false;
            }
            System.out.println(s.toString());
        }

        // 3. pop sorted elements back to array in ascending order
        int i = 0;
        while ( !s.isEmptyHead() )
        {
            arr[i] = s.popHead();
            i ++;
        }
    }
}
