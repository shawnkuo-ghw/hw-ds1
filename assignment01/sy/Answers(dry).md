### Problem 2
1. State the precondition required for correctness

    Pre-condition:
    
    1. array A is not null
    
    2. the range of elements in array A are in [0,9] otherwise the index of B will out of bound

        Thus this algorithm only can be used to sort the array whose elements are in [0,9]

2. Determine the worst-case time complexity. Provide a detailed asymptotic analysis

    Overall, the idea of algorithm is:

    1. Create an array B with size 10(from 0 to 9), this array B is used to count the times of same digit respect to the index

        For example if 4 appears 5 times in array A, then in array B, in the index 4, B[4] = 5

    2. Then we create the array A again according to the array B, that is if B[0] = 5, which means digit 
        
        0 appears 5 times. Then we modify array A: Let position 0 to position 4 be 0 and so on...

    Then let's anaylze step by step, assume the length of array A is `n`

    1. `int B[] = new int[]{0,0,0,0,0,0,0,0,0,0};` is cost $c_1$ with times $1$

        Thus this first item of $T(n)$ is $c_1\times 1$

    2.  ```java
        for(int i = 0; i < A.length; i++) {
            int index = A[i];
            B[index] = B[index] + 1;
        }
        ```
        This is a loop

        `for(int i = 0; i < A.length; i++) {` is cost $c_2$ with times $n+1$ since i needs to be n to check condition

        `int index = A[i];` is cost $c_3$ with times $n$

        `B[index] = B[index] + 1;` is cost $c_4$ with times $n$

        Thus this second item of $T(n)$ is $c_2\times (n+1)+c_3\times n+c_4\times n$

    3. `int k = 0;` is cost $c_5$ with times $1$

        Thus this third item of $T(n)$ is $c_5\times 1$

    4. ```java
        for(int i = 0; i <= 9; i++) {
            int j = 0;
            while(j < B[i]) {
                A[k] = i;
                k = k + 1;
                j = j + 1;
            }
        }
        ```
        This is a loop

        `for(int i = 0; i <= 9; i++) {` is cost $c_6$ with times $11$ since i needs to be 10 to check condition

        `int j = 0;` is cost $c_7$ with times $10$

        `while(j < B[i]) {` is cost $c_8$ with times $10\times n$ since it is in the for loop, then $10$ times. Also for this while loop, it ranges from 0 to `B[i]`

        And we know the elements of `B[i]` is the appearing times of the elements in array A

        Thus in the worst case: all element is in `B[i]`, then $\max(B[i])=n$  

        `A[k] = i;` is cost $c_9$ with times $n$

        `k = k + 1;` is cost $c_{10}$ with times $n$

        `j = j + 1;` is cost $c_{11}$ with times $n$

        Thus this fourth item of $T(n)$ is $c_6\times 11 +c_7\times 10+c_8 \times 10 \times n + c_9\times n+c_{10}\times n+c_{11}\times n$
    
    Thus $T(n)=c_1\times 1+c_2\times (n+1)+c_3\times n+c_4\times n+c_5\times 1+c_6\times 11 +c_7\times 10+c_8 \times 10 \times n + c_9\times n+c_{10}\times n+c_{11}\times n$

    Then $T(n)=(c_2 + c_3 + c_4 + 10c_8 + c_9 + c_{10} + c_{11})n + (c_1 + c_2 + c_5 + 11c_6 + 10c_7)$

    When n goes to infinity, $T(n)\approx n$ which is linear time: O(n) in worst case


3. Prove correctness using the loop invariant

4. Implement the algorithm in Java

    Just follow the pseudo code

5. Supply meaningful unit tests

    There are two precondition, so I create two negative test to test if the precondition works well

    Then I create a positive test to test if this algorithm can sort the array well