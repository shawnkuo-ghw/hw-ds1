### Problem 2
1. **<u>*State the precondition required for correctness*</u>**

    Pre-condition:
    
    1. array A is not null
    
    2. the range of elements in array A are in [0,9] otherwise the index of B will out of bound

        Thus this algorithm only can be used to sort the array whose elements are in [0,9]

2. **<u>*Determine the worst-case time complexity. Provide a detailed asymptotic analysis*</u>**


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

    When n goes to infinity, $T(n)\approx (c_2 + c_3 + c_4 + 10c_8 + c_9 + c_{10} + c_{11})n\leq c'n$ for $c'=(c_2 + c_3 + c_4 + 10c_8 + c_9 + c_{10} + c_{11})$ 
    
    Thus it is linear time: O(n) in worst case


3. **<u>*Prove correctness using the loop invariant*</u>**

    Since there are two loops, then we check the loop invariant twice

    1. PC = `{A != null && 0 <= A[p] <= 9 for all p s.t. 0 <= p <= A.length - 1 && B[k] = 0 for all k s.t. 0 <= k <= 9 && i = 0}`

        Inv = `{0 <= i <= A.length && B[k] = |{p : A[p] = k && 0 <= p < i}| for all 0 <= k <= 9}`

        B = `{0 <= i < A.length}`

        QC = `{i = A.length && B[k] = |{p : A[p] = k && 0 <= p < A.length}| for all 0 <= k <= 9}`
    
        Then we prove correctness by loop invariant theorem

        1. Initialization: Pc $\implies$ Inv

            Suppose we have PC, then `i = 0` and `A != null` $\implies $ `i = 0` and `A.length >= 0`$\implies$ `0 <= i <= A.length`

            Since `i = 0`, then `{p : A[p] = k && 0 <= p < i}` is empty, then `B[k] = 0`

            Since `B[k] = 0 for all k s.t. 0 <= k <= 9 && i = 0` and `0 <= A[p] <= 9 for all p s.t. 0 <= p <= A.length - 1`, then `B[k] = |{p : A[p] = k && 0 <= p < i}|  for all 0 <= k <= 9`

            Thus we prove the invariant holds

        2. Maintenance: Assuming Inv && B holds, the execution of the loop body makes Inv true again

            Suppose we have Inv and B, NTP Inv is true again

            `Inv && B = {0 <= i0 < A.length && B[k] = |{p : A[p] = k && 0 <= p < i0}| for all 0 <= k <= 9}`

            Let `i = i0 + 1`, then `0 <= i0 + 1 <= A.length`, then `0 <= i <= A.length`

            Also since `B[k] = |{p : A[p] = k && 0 <= p < i0}| for all 0 <= k <= 9`, then `B[k] = |{p : A[p] = k && 0 <= p < i0 + 1}| for all 0 <= k <= 9`

            Then `B[k] = |{p : A[p] = k && 0 <= p < i}| for all 0 <= k <= 9`

            Thus Inv is true again

        3. Termination: Inv && not B $\implies$ Qc

            `Inv && not B = {0 <= i <= A.length && i >= A.length && B[k] = |{p : A[p] = k && 0 <= p < i}| for all 0 <= k <= 9}`

            Then `Inv && not B = {i = A.length && B[k] = |{p : A[p] = k && 0 <= p < A.length}| for all 0 <= k <= 9}`

            Thus Qc holds

    2. 1. For outer for loop
    
            PC = `{A != null && 0 <= B[v] for all v s.t. 0 <= v <= 9 && B[0] + B[1] + ... + B[9] = A.length && k = 0 && i = 0}`

            Inv = `{0 <= i <= 10 && k = B[0] + B[1] + ... + B[i-1] && |{p : 0 <= p < k && A[p] = v}| = B[v] for all v s.t. 0 <= v < i && |{p : 0 <= p < k && A[p] = v}| = 0 for all v s.t. i <= v <= 9}`

            B = `{i <= 9}`

            QC = `{k = A.length && |{p : 0 <= p < A.length && A[p] = v}| = B[v] for all v s.t. 0 <= v <= 9 && isSorted(A)}`

            Then we prove correctness by loop invariant theorem

            1. Initialization: PC $\implies$ Inv

                Since `i = 0` and `k = 0`, we have `k = B[0] + ... + B[-1] = 0` holds and `|{p : 0 <= p < 0 && A[p] = v}| = 0 for all v with 0 <= v <= 9`
                
                Thus Inv holds

            2. Maintenance: Assuming Inv && B1 holds, the execution of the loop body makes Inv true again

                Suppose we have Inv and B, NTP Inv is true again

                Inv && B = `{0 <= i0 <= 9 && k = B[0] + B[1] + ... + B[i0-1] && |{p : 0 <= p < k && A[p] = v}| = B[v] for all v s.t. 0 <= v < i0 && |{p : 0 <= p < k && A[p] = v}| = 0 for all v s.t. i0 <= v <= 9}`

                Let `i = i0 + 1`

                Since `0 <= i0 <= 9`, then `0 <= i <= 10`

                Since `k = B[0] + B[1] + ... + B[i0-1]`, `|{p : 0 <= p < k && A[p] = v}| = B[v] for all v s.t. 0 <= v < i0` and `|{p : 0 <= p < k && A[p] = v}| = 0 for all v s.t. i0 <= v <= 9`
                
                By the inner loop’s Qc(proved below), we have `k = B[0] + ... + B[i0-1] + B[i0]` and `|{p : 0 <= p < k && A[p] = i0}| = B[i0]`

                Then we have `k = B[0] + B[1] + ... + B[i-1] && |{p : 0 <= p < k && A[p] = v}| = B[v] for all v s.t. 0 <= v < i` and `|{p : 0 <= p < k && A[p] = v}| = 0 for all v s.t. i <= v <= 9`
                
                Thus Inv holds again

            3. Termination: Inv && not B1 $\implies$ QC

                Since not B, then `i = 10`, then:

                `Inv && not B = {i = 10 && k = B[0] + B[1] + ... + B[9] && |{p : 0 <= p < k && A[p] = v}| = B[v] for all v s.t. 0 <= v < 10 && |{p : 0 <= p < k && A[p] = v}| = 0 for all v s.t. 10 <= v <= 9}`
                
                Thus we have `{k = B[0] + B[1] + ... + B[9] = A.length && |{p : 0 <= p < k && A[p] = v}| = B[v] for all v s.t. 0 <= v <= 9}`

                Since the process of sorting is appending blocks of value v where v is increasing from 0 to 9, thus A is nondecreasing, hence isSorted(A) holds
                
                Thus QC holds
            
        2. For inner while loop (fixed i)

            PC2 = `{fixed i with 0 <= i <= 9 && j = 0 && k = B[0] + B[1] + ... + B[i-1] && |{p : 0 <= p < k && A[p] = v}| = B[v] for all v s.t. 0 <= v < i && |{p : 0 <= p < k && A[p] = v}| = 0 for all v s.t. i <= v <= 9}`

            Inv2 = `{0 <= j <= B[i] && k = B[0] + B[1] + ... + B[i-1] + j && |{p : B[0]+...+B[i-1] <= p < k && A[p] = i}| = j && |{p : 0 <= p < B[0]+...+B[i-1] && A[p] = v}| = B[v] for all v s.t. 0 <= v < i}`

            B2 = `{j < B[i]}`

            QC2 = `{j = B[i] && k = B[0] + B[1] + ... + B[i] && |{p : 0 <= p < k && A[p] = i}| = B[i] && |{p : 0 <= p < k && A[p] = v}| = B[v] for all v s.t. 0 <= v < i}`

            Then we prove correctness by loop invariant theorem

            1. Initialization: PC2 ⇒ Inv2

                Suppose PC2 holds, NTP Inv2
                
                Since `j = 0` , then `0 <= j <= B[i]`
                
                Since `k = B[0] + ... + B[i-1]`, we get `k = B[0] + ... + B[i-1] + j`
                
                Since `k = B[0] + B[1] + ... + B[i-1] && |{p : 0 <= p < k && A[p] = v}| = B[v] for all v s.t. 0 <= v < i`, then `|{p : 0 <= p < B[0] + B[1] + ... + B[i-1] && A[p] = v}| = B[v] for all v s.t. 0 <= v < i`

                Since `|{p : 0 <= p < k && A[p] = v}| = 0 for all v s.t. i <= v <= 9` and `j = 0`, then `|{p : 0 <= p < k && A[p] = i}| = 0`, then `|{p : k <= p < k && A[p] = i}| = 0` since this set is empty, then `|{p : B[0]+...+B[i-1] <= p < k && A[p] = i}| = 0` since `k = B[0]+...+B[i-1]`
                
                Thus Inv2 holds

            2. Maintenance: Assuming Inv2 && B2 holds, the body makes Inv2 true again

                Since B2, then `j0 < B[i]`

                Let `j = j0 + 1; k = k0 + 1`

                Then Inv2 && B2 = `{0 <= j0 < B[i] && k0 = B[0] + B[1] + ... + B[i-1] + j0 && |{p : B[0]+...+B[i-1] <= p < k0 && A[p] = i}| = j0 && |{p : 0 <= p < B[0]+...+B[i-1] && A[p] = v}| = B[v] for all v s.t. 0 <= v < i}`

                Then we have `0 <= j0 + 1 <= B[i] && k0 + 1= B[0] + B[1] + ... + B[i-1] + j0 + 1`

                Then `0 <= j <= B[i] && k= B[0] + B[1] + ... + B[i-1] + j`

                Since `|{p : B[0]+...+B[i-1] <= p < k0 && A[p] = i}| = j0`, then `|{p : B[0]+...+B[i-1]<= p < k0 + 1 && A[p] = i}| = j0 + |{p : A[k0 + 1] = i}|`

                Since we know `A[k0 + 1] = A[k] = i` happens in the while loop body, then `|{p : B[0]+...+B[i-1] <= p < k0 + 1 && A[p] = i}| = j0 + 1 = j`

                Since `|{p : 0 <= p < B[0]+...+B[i-1] && A[p] = v}| = B[v] for all v s.t. 0 <= v < i` and in the loop body we have `A[k] = i`, this doesn't change the value of B[0] to B[i - 1] because `A[k] = i` will only effect `B[i]`

                Thus this invarinat holds again

            3. Termination: Inv2 && not B2 ⇒ QC2

                Since `Inv2` we have `j <= B[i]`, and since `not B2` we have `j >= B[i]`, thus `j = B[i]`
                Also since `k = B[0] + B[1] + ... + B[i-1] + j`, then `k = B[0] + B[1] + ... + B[i]`

                In order to prove `|{p : 0 <= p < k && A[p] = i}| = B[i]`
                
                Let `s = B[0] + ... + B[i-1]`, then `|{p : 0 <= p < k && A[p] = i}| = |{p : 0 <= p < s && A[p] = i}| + |{p : s <= p < k && A[p] = i}|`

                Since the outer-loop precondition of the inner loop:|{p : 0 <= p < s && A[p] = i}| = 0, and from the inner-loop invariant:|{p : s <= p < k && A[p] = i}| = j = B[i]
                
                Then the total is 0 + B[i] = B[i]
                
                In order to prove `|{p : 0 <= p < k && A[p] = v}| = B[v] for all v s.t. 0 <= v < i`

                `|{p : 0 <= p < k && A[p] = v}| = |{p : 0 <= p < s && A[p] = v}| + |{p : s <= p < k && A[p] = v}|`

                Since Inv2:`|{p : 0 <= p < s && A[p] = v}| = B[v] for 0 <= v < i` and since the inner loop’s has `A[k] = i` and it fills the indices `p : s <= p < k` before terminating, then every`A[p] = i`, then for any v < i, no p : s <= p < k satisfies A[p] = v
                
                Thus the set `{p : s <= p < k && A[p] = v}` is empty, thus `|{p : s <= p < k && A[p] = v}| = 0`
                
                Thus the sum is B[v] + 0 = B[v]

                Thus Qc holds

4. **<u>*Implement the algorithm in Java*</u>**

    Just follow the pseudo code

5. **<u>*Supply meaningful unit tests*</u>**

    There are two precondition, so I create two negative test to test if the precondition works well

    Then I create a positive test to test if this algorithm can sort the array well